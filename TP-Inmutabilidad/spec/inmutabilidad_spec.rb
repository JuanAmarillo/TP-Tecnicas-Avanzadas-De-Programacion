require 'rspec'
  require_relative '../src/inmutabilidad'

  describe 'test' do

    it 'hay test' do
      expect(2+2).to eq 4
    end

    it 'es una clase?' do
      expect(Case_class.class).to eq(Class)
    end

    it 'una clase no puede heredar de una case clase' do
      expect{class Sarlonga end
      Sarlonga.<Case_class}.to raise_error('no se puede heredar de una case class')

    end

  end