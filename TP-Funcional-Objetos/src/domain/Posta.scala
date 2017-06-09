package domain

  trait Posta{
	  def cumpleCondicion(participante: Participante )   : Boolean
	  
    def participar(participantes : List[Participante]) = 
      for {
        participante <- ordenarPorMejor(participantes)  if puedeParticipar(participante) 
      }
      yield aplicarEfectos(participante)

    def ordenarPorMejor(participantes : List[Participante]) = 
       participantes.sortWith((unParticipante,otroParticipante) => unParticipante.esMejorQue(otroParticipante, this))
      
    def aplicarEfectos(participante: Participante) = participante.nivelDeHambre(this)
      
    def puedeParticipar(participante : Participante  ) = 
      cumpleCondicion(participante) && suHambreNoLLegaACien(participante)
      
    def suHambreNoLLegaACien(participante:Participante) =
      participante.hambreLuegoDe(this) < 100
}
  case class Pesca(pesoMin : Int)     extends Posta {
   def cumpleCondicion(participante: Participante     ) = participante.peso > pesoMin 
  }
  case class Combate(barbarosidadMin: Int)      extends Posta {
    def cumpleCondicion(participante: Participante) = participante.barbarosidad > barbarosidadMin
  }
  case class Carrera(kms: Int) extends Posta {
    def cumpleCondicion(participante: Participante) = ???
  }