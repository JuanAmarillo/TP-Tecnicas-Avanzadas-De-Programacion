require 'rspec'
  require_relative '../src/inmutabilidad'

  describe 'test' do

    it 'Crear case_class' do
      case_class Hey do
      end

      expect(Hey.class).to eq(Class)
    end

    it 'una clase no puede heredar de una case clase' do

        case_class Lol do
        end

        expect{class Holi < Lol
        end}.to raise_error('no se puede heredar de una case class')

    end

    it 'Constructor' do
      case_class Sarlompa do
        attr_accessor :Holi
      end

      expect(Sarlompa.new(1).class).to eq(Sarlompa)
    end

    it 'Funcioansin el new' do
      case_class Sarlompa do
        attr_accessor :Holi
      end

      x = Sarlompa(1)
      expect(x.class).to eq(Sarlompa)
    end

    it 'Freeze' do
      case_class Sarlompa do
        attr_accessor :Holi
      end

      x = Sarlompa(1)

      expect(x.frozen?).to eq(true)
    end

    it 'to_s' do
      case_class Sarlompa do
        attr_accessor :Holi
      end

      x = Sarlompa(1)

      expect(x.to_s).to eq("Sarlompa(1)")
    end

    it '==' do
      case_class Sarlompa do
        attr_accessor :Holi
      end

      x = Sarlompa(1)
      y = Sarlompa(2)

      expect(x == y).to eq(false)
    end

    it 'copy con lambda' do
      case_class Sarlompa do
        attr_accessor :holi
      end

      x = Sarlompa(1)
      otro_x = x.copy -> (holi){holi + 1}

      expect(otro_x.holi).to eq(2)
    end

    it 'case_object' do
      case_class Alumno2 do
        attr_accessor :nombre, :estado
      end

      case_object Cursando2 do
      end

      alumno = Alumno2("Jose", Cursando2)

      expect(alumno.to_s).to eq("Alumno2(Jose, Cursando2)")
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
      alumno = Alumno("J",9)

      valor = case alumno
                when is_a(Alumno)
                  5
              end

      expect(valor).to eq(5)

    end

    it 'has' do
      alumno = Alumno("J",9)

      valor = case alumno
                when has(:nombre, "J")
                  5
              end

      expect(valor).to eq(5)

    end


    it 'case con Alumno'do
      _ = Object.new
      _.instance_eval do
        def ===(a)
          true
        end

        def ==(a)
          true
        end
      end

      alumno = Alumno("Jose", 9)


      valor = case alumno
                when Alumno("Jose", _)
                  2
              end

      expect(valor).to eq(2)
    end


  end