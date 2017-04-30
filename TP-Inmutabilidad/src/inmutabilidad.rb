
# attr_accessor se comporta como attr_reader
# freeze a nuevas instancias

class Case_class

  def initialize
    self.freeze
  end

  def attr_accessor(sarlompa)
    attr_reader(sarlompa)
  end

end


class Class < Module
  def < aClass
    if aClass == Case_class
      raise 'no se puede heredar de una case class'
    else
      super
    end
  end
end

module Comportamiento_case_class

  def initialize
    self.freeze
  end

  def attr_accessor(sarlompa)
    attr_reader(sarlompa)
  end

 # def ==(otro)
 #   self.class == otro.class && self.instance_variables == otro.instance_variables && (self.instance_variables).map {|n| self.instance_variable_get(n)} == (otro.instance_variables).map {|n| otro.instance_variable_get(n)}
 # end

  def prueba
    2
  end

end


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

  def new_case_class (&block)
    Object.const_set(@nombre, (Class.new(@parent, &block).include Comportamiento_case_class))
  end

end

class ::Object
  def self.const_missing (nombre)
    Builder_case_class.new(nombre)
  end
end

def case_class (builder, &block)
  builder.new_case_class(&block)
end

include Entorno

