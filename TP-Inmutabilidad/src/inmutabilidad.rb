
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


module Entorno
  class BuilderCase_Class
    def initialize(parent)
      @case_class = Class.new(parent).include Comportamiento_case_class
    end

    def set_comportamiento(&block)
      @case_class.class_eval &block

    end

    def case_class
      obj = @case_class
    end

  end
  
  def case_class(nombre, parent = Object, &block)

    x = BuilderCase_Class.new(parent)
    x.set_comportamiento(&block)

    Object.const_set(nombre, x.case_class)

    #(Object.const_set(nombre, (Class.new(parent).include Comportamiento_case_class.class_eval)))



  end

  class ::Object
    def self.const_missing (const)
      const
     end
  end

end

include Entorno

