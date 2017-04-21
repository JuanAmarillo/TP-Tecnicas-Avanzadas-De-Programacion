require 'rspec'
require_relative '../src/merge_conflict'

describe 'otroTest' do

    it 'es inmutable?' do

    expect{objeto=Case_class.new
      objeto.coso = 10}.to raise_error('wrong number of arguments (given 1, expected 0)')
    end

end