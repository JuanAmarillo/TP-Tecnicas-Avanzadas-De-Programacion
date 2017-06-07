package domain

  sealed trait Posta{
    def participar(participantes : List[Participante]) {
      participantes.filter(participante => cumpleCondicion(participante))
    }
    def cumpleCondicion(participante : Participante) : Boolean
}
  case class Pesca(pesoMin : Int)     extends Posta {
   def cumpleCondicion(participante: Participante) = participante.peso > pesoMin
  }
  case class Combate(barbarosidadMin: Int)      extends Posta {
    def cumpleCondicion(participante: Participante) = participante.barbarosidad > barbarosidadMin
  }
  case object Carrera extends Posta {
    def cumpleCondicion(participante: Participante) = ???
  }