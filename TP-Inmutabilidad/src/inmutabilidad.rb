
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
    if aClass== Case_class
      raise 'no se puede heredar de una case class'
    else
      super
    end
  end
end

module Entorno

  def case_class (nombre,&block)
    Object.const_set(nombre, Case_class.new(&block))
    # nombre.to_sym=Case_class.new(&block)
  end
end

include Entorno