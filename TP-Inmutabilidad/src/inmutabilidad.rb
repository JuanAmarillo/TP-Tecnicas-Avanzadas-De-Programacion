#aca van los metodos de instancia
module Comportamiento_case_class

  def initialize
    self.freeze
  end

end

#y aca los de clase
module OtroModulo

  def inherited(subclass)
      Object.send(:remove_const, subclass.name)
      raise "no se puede Heredar de una case_class"
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
      Object.const_set(@nombre, (Class.new(@parent).extend Comportamiento_case_class))
    end


  end

  class ::Object

    def self.const_missing (nombre)
      Builder_case_class.new(nombre)
    end

    def case_class (builder, &block)
      una_case_class = builder.new_case_class.include OtroModulo
      una_case_class.class_eval(&block)
    end

  end



end

include Entorno

case_class X do
  def m1
    'm1'
  end
end

