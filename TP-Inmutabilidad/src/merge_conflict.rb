# attr_accessor se comporta como attr_reader
# freeze a nuevas instancias

class Case_class
  def initialize
    @cosa2=2
 #   self.freeze
  end

  def attr_accessor(sarlompa)
    attr_reader(sarlompa)
  end

  def trampita
    @cosa1 = 10
  end

  def ==(otro)
    self.class == otro.class && self.instance_variables == otro.instance_variables && (self.instance_variables).map {|n| self.instance_variable_get(n)} == (otro.instance_variables).map {|n| otro.instance_variable_get(n)}
  end

  attr_accessor :cosa1, :cosa2
end