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
      expect{class Sarlonga
      end
      Sarlonga.<Case_class}.to raise_error('no se puede heredar de una case class')

    end

    it 'Funciona ahora la sintaxis bien?' do
      case_class X do end
      expect(X.class).to eq(Case_class)
    end

    it 'Funcioan el new' do
      case_class X do end
      x = X.new
      expect(x.Class).to eq(X)
    end

    it 'case_object' do
      case_class Alumno do
        attr_accessor :nombre, :estado
      end

      case_object Cursando do
      end

      alumno = Alumno("Jose", Cursando)

      expect(alumno.to_s).to eq("Alumno(Jose, Cursando)")
    end

    it 'el _ es una pija' do
      _ = Object.new
      _.instance_eval do
        def ===(a)
          true
        end
      end

      case_object Tobito do
      end

      valor = case Tobito
                when _
                  2
              end

      expect(valor).to eq(2)

    end

    it 'is_a' do
      a = is_a(Array)
      expect(a.clasesitaa).to eq(Array)

    end

  end