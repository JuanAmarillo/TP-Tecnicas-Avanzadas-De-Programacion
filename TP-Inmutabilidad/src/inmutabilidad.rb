module Comportamiento_de_clase_case_class

  def inherited(subclass)
    Object.send(:remove_const, subclass.name)
    raise ('No se puede heredar de una case_class')
  end

  def attr_accessor(*symbols)
    attr_reader(*symbols)
    symbols.each do |symbol|
      self.instance_variable_set("@#{symbol}",'inicializada')
    end
  end

  def new(*args)
    if args.length == (self.instance_variables).length
      super(self.instance_variables.zip args)
    else
      raise "wrong number of arguments (given #{args.length}, expected #{(self.instance_variables).length})"
    end
  end

end

module Comportamiento_de_instancias_case_class

  def initialize(args)
    args.each do |variable,valor|
      self.instance_variable_set(variable,valor)
    end
    self.freeze
  end

  def to_s
    if super_tiene_el_metodo(:to_s)
      super
    else
      self.class.name + "(" + (aplicar_a_variables do |var| "#{var}" end).join(", ") + ")"
    end
  end

  def ==(instancia)
    if super_tiene_el_metodo(:==)
      super
    else
      self.to_s == instancia.to_s
    end
  end

  def hash
    if super_tiene_el_metodo(:hash)
      super
    else
      7 + (aplicar_a_variables do |var| var.hash end).inject(0,:+)
    end
  end

  def super_tiene_el_metodo(metodo)
    self.class.superclass.instance_methods(false).include? metodo
  end

  def aplicar_a_variables(&block)
    self.instance_variables.collect do |var|
        aplicar_a_variable(var,&block)
    end
  end

  def aplicar_a_variable(var,&block)
    if(block_given?)
      self.instance_variable_get(var).instance_eval(&block)
    else
      self.instance_variable_get var
    end
  end

  def copy(*lambdas)
    self.class.new(*aplicar_lambdas_a_parametros(lambdas))
  end

  def aplicar_lambdas_a_parametros(lambdas)
    self.instance_variables.collect do |var|
      aplicar_a_variable(var,&buscar_lambda_correspondiente(var, lambdas))
    end
  end

  def buscar_lambda_correspondiente(var,lambdas)
    lambdas.find do |lambda| "#{var}" == "@#{lambda.parameters.flatten.last}" end
  end

  def ===(otra_instancia)
    self.class == otra_instancia.class && aplicar_a_variables.zip(otra_instancia.aplicar_a_variables).all? {|varA,varB| varA === varB}
  end

end


module Entorno

  class Builder_case
    attr_accessor :nombre, :parent

    def initialize(nombreCC)
      @nombre = nombreCC
      @parent = Object
    end


    def < parentcc
      @parent = parentcc
      self
    end

    def new_case_class(&block)
      una_case_class = Object.const_set(@nombre, (Class.new(@parent).extend Comportamiento_de_clase_case_class))
      una_case_class.class_eval(&block)
      Object.send(:define_method,una_case_class.name) do |*args|
        una_case_class.new(*args) end
      una_case_class.include Comportamiento_de_instancias_case_class
      una_case_class
    end

    def new_case_object(nombre,&block)
      un_case_object = Object.const_set(@nombre, (Object.new.extend Comportamiento_de_instancias_case_class))
      un_case_object.instance_eval(&block)
      un_case_object.define_singleton_method(:to_s) do "#{nombre}" end
      un_case_object
    end


  end

  class ::Object

    def self.const_missing (nombre)
      Builder_case.new(nombre)
    end

    def case_class (builder, &block)
      builder.new_case_class &block
    end

    def case_object (builder, &block)
      builder.new_case_object(builder.nombre,&block)
    end

  end

  def is_a tipo
    Triple_igual.new(tipo) do |tipo| self.is_a?(tipo) end
  end

  def has (atributo, valor, &block)
    Triple_igual.new(atributo,valor,block) do |atributo,valor,block2|
      cumpleLoDelBloque(atributo, block2) || cumpleValor(atributo, valor)
    end
  end

  def cumpleValor(atributo, valor)
    self.instance_variable_get("@#{atributo}") == valor
  end

  def cumpleLoDelBloque(atributo, block2)
    self.instance_variable_get("@#{atributo}").instance_eval &block2
  end

  def _
    Triple_igual.new() do true end
  end

  class Triple_igual
    def initialize(*args,&block)
      self.define_singleton_method(:===) do |instancia_case|
        instancia_case.instance_exec(*args,&block)
      end
    end
  end


end

include Entorno

case_class Alumno do
  attr_accessor :nombre, :estado
end



