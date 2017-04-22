require 'rspec'
require_relative '../src/inmutabilidad'

describe 'test' do


  it 'funca' do
    expect(2+2).to eq(4)
  end

  it 'es una clase?' do


    expect(Class.<Fixnum).to eq('saraza')

  end
  it 'una clase no puede heredar de una case clase' do

    expect{class Sarlonga
    end
    Sarlonga.<Case_class}.to raise_error('no se puede heredar de una case class')

  end


    it 'es inmutable?' do

      expect{objeto=Case_class.new
      objeto.coso = 10}.to raise_error('wrong number of arguments (given 1, expected 0)')
    end

  it 'freeze funciona correctamente' do
    expect{case_class Alumno do
      attr_accessor :nombre, :nota
    end

    }
  end

end