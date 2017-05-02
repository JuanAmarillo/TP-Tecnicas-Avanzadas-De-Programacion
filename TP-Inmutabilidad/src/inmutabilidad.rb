#aca van los metodos de instancia
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
    if(args.length == (self.instance_variables).length)
      super(self.instance_variables.zip args)
    else
      raise "esto se va a descontrolaaaaaaarrrrrrr"
    end
  end


end

#y aca los de clase
module Comportamiento_de_instancias_case_class

  def initialize(args)
    args.each do |variable,valor|
      self.instance_variable_set(variable,valor)
    end
    self.freeze
  end

  def ==(otro)
    self.class == otro.class && self.instance_variables == otro.instance_variables && (self.instance_variables).map {|n| self.instance_variable_get(n)} == (otro.instance_variables).map {|n| otro.instance_variable_get(n)}
  end

  def to_s#Esto esta como el orto mal
    "#{self.class}#{(self.instance_variables).map {|i| self.instance_variable_get(i)}}"
  end

  def hash
    7 + ((self.instance_variables).map {|i| self.instance_variable_get(i).hash}).inject(0, :+)
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

    def new_case_class#(&block)
      Object.const_set(@nombre, (Class.new(@parent).extend Comportamiento_de_clase_case_class))
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


