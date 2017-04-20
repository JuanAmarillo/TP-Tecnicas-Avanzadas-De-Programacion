require 'rspec'
require_relative '../src/inmutabilidad'

describe 'test' do


  it 'es una clase?' do

    expect(Case_class.class).to eq(Class)

  end
end