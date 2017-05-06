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
        end}.to raise_error()

    end

    it 'Instanciar una case_class con atributo' do
      case_class Sarlompa do
        attr_accessor :holi
      end

      expect(Sarlompa.new(1).class).to eq(Sarlompa)
    end

    it 'Funcioan sin el new' do
      x = Sarlompa(1)
      expect(x.class).to eq(Sarlompa)
    end

    it 'Freeze' do
      x = Sarlompa(1)
      expect(x.frozen?).to eq(true)
    end

    it 'to_s' do
      x = Sarlompa(1)
      expect(x.to_s).to eq("Sarlompa(1)")
    end

    it '==' do
      x = Sarlompa(1)
      y = Sarlompa(2)
      z = Sarlompa(1)
      expect(x == y).to eq(false)
      expect(x == z).to eq(true)
    end

    it 'copy con lambda' do
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

    it 'patron _' do
   
      case_object Tobito do
      end

      valor = case Tobito
                when _
                  2
              end

      expect(valor).to eq(2)

    end

    it 'patron is_a' do
      alumno = Alumno("J",9)

      valor = case alumno
                when is_a(Alumno)
                  5
              end

      expect(valor).to eq(5)

    end

    it 'patron has' do
      alumno = Alumno("J",9)

      valor = case alumno
                when has(:nombre, "J")
                  5
              end

      expect(valor).to eq(5)

    end


    it 'case con Alumno'do
            
     case_class Termino do
       attr_accessor :nota
     end

      alumno = Alumno("Jose", Termino(9))

      valor = case alumno
                when Alumno("Jo", _)
                  2
                when Alumno("Jose",Termino(8))
                  3
                when Alumno(_,has(:nota, 9))
                  "todo piola"
              end

      expect(valor).to eq("todo piola")

      end


  end
