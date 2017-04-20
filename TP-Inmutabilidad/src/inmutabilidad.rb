=begin
class Module
  def case_class(name, superclass = ::Object, &blk)
    const_set(name, Class.new(superclass, &blk))
  end
end

def case_class(name, superclass = ::Object, &blk)
  self.class.case_class(name, superclass, &blk)
end

=end

class Case_class

end
