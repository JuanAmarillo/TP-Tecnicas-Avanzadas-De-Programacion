# attr_accessor se comporta como attr_reader
# freeze a nuevas instancias

class Case_class
  def initialize

    @cosa2=2
    self.freeze

  end

  def attr_accessor(sarlompa)
    attr_reader(sarlompa)
  end

  def trampita
    @cosa1 = 10
  end

  attr_accessor :cosa1, :cosa2
end