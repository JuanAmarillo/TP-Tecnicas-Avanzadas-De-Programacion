
class Case_class
  def hola
    1
  end
end


class Class
  def < aClass
    if(aClass== Case_class)
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