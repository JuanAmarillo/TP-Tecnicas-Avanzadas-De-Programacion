module Comportamiento_case_class
  def prueba
    "El modulo esta bien"
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

    def new_case_class (&block)
      Object.const_set(@nombre, (Class.new(@parent, &block).include Comportamiento_case_class))
    end

  end

  class ::Object
    def self.const_missing (nombre)
      nombre=Builder_case_class.new(nombre)
    end
  end

  def case_class (builder, &block)
    builder.new_case_class(&block)
  end

end

include Entorno
