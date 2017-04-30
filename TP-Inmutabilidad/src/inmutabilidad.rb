
module Comportamiento_case_class

  def initialize
    self.freeze
  end

  def attr_accessor(sarlompa)
    attr_reader(sarlompa)
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
      Builder_case_class.new(nombre)
    end

    def self.inherited(subclass)
      if(self.include? Comportamiento_case_class)
        raise "no se puede Heredar de una case_class"
      end
    end

    def case_class (builder, &block)
      builder.new_case_class(&block)
    end

  end



end

include Entorno

case_class X do
end

