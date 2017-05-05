module Comportamiento_de_clase_case_class

  def inherited(subclass)
    Object.send(:remove_const, subclass.name)
    raise "no se puede Heredar de una case_class"
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

  def ===(otro)
   self.==(otro)
  end

  def to_s
    self.class.name + "(" + (aplicar_a_variables do |var| "#{var}" end).join(", ") + ")"
  end

  def ==(instancia)
    self.to_s == instancia.to_s

  end

  def hash
    7 + (aplicar_a_variables do |var| var.hash end).inject(0,:+)
  end

  def aplicar_a_variables(&block)
    self.instance_variables.collect do |var|
        aplicar_a_variable(var,&block)
    end
  end

  def aplicar_a_variable(var,&block)
    if(block_given?)
      self.instance_exec(var, &block)
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

end

module Comportamiento_de_instancias_case_object
  include Comportamiento_de_instancias_case_class

  def to_s
    self.name
  end

end


module Entorno

  class Builder_case_class
    attr_accessor :nombre, :parent

    def initialize(nombreCC)
      @nombre = nombreCC
      @parent = Object
    end


    def < parentcc
      @parent = parentcc
      self
    end

    def new_case_class
      Object.const_set(@nombre, (Class.new(@parent).extend Comportamiento_de_clase_case_class))
    end

    def new_case_object
      Object.const_set(@nombre, (Object.new.extend Comportamiento_de_instancias_case_object))
    end


  end

  class ::Object

    def self.const_missing (nombre)
      Builder_case_class.new(nombre)
    end

    def case_class (builder, &block)
      una_case_class = builder.new_case_class.include Comportamiento_de_instancias_case_class
      una_case_class.class_eval(&block)
      Object.send(:define_method,una_case_class.name) do |*args|
        una_case_class.new(*args)
      end
    end

    def case_object (builder, &block)
      un_case_object = builder.new_case_object
      un_case_object.instance_eval(&block)
      un_case_object.define_singleton_method(:name) do "#{builder.nombre}" end
    end

  end

  def is_a tipo
   @@tipoVar = tipo
    Object.new.instance_eval do
      def ===(otro)
        otro.is_a?(@@tipoVar)
      end
      self
    end
  end

  def has (atributo, valor)
    @@atr=atributo
    @@val=valor
    Object.new.instance_eval do
      def === otro
        otro.instance_variable_get("@#{@@atr}") == @@val
      end
      self
    end
  end

end

include Entorno

case_class X do
  attr_accessor :a, :b, :c
  def m1
    'm1'
  end
  def trampa
    @a = 2
  end
end


case_class Y do
  attr_accessor :a, :b
  def m1
    'm1'
  end
  def trampa
    @a = 2
  end
end

case_class Alumno do
  attr_accessor :nombre, :estado
end

case_object D do
  def m3
    'm3'
  end
end

_ = Object.new
_.instance_eval do
  def ===(a)
    true
  end
end




