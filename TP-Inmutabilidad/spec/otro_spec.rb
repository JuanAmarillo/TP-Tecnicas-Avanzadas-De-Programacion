require 'rspec'
    require_relative '../src/merge_conflict'

    describe 'otroTest' do

      it 'es inmutable?' do

        expect{objeto=Case_class.new
        objeto.cosa2 = 10}.to raise_error('wrong number of arguments (given 1, expected 0)')
      end

      it 'que onda el ==' do
        objeto=Case_class.new
        objeto2=Case_class.new
        expect(objeto==objeto2).to eq true
        objeto.cosa2 = 10
        expect(objeto==objeto2).to eq false

      end

    end