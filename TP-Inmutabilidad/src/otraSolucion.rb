

class A
  def chau
    'chau'
  end
end


module Entorno

  attr_accessor :clase, :clase_padre


  class BuilderCase_Class

    def initialize(const)
     @clase = const
    end

    def <(clase_padre)
      @clase_padre = clase_padre
      self
    end

  end

  def case_class(builder)
    Object.const_set(builder.clase, Class.new(builder.clase_padre))
  end

  class ::Object
    def self.const_missing (const)
      BuilderCase_Class.new(const)
    end

    def < (clase)

    end
  end

end

include Entorno
