package domain

  trait Posta{
	  def cumpleCondicion(participante: Participante )   : Boolean
	  
    def participar(participantes : List[Participante]) = 
      participantes.filter(participante => puedeParticipar(participante))
          .sortWith((unParticipante,otroParticipante) => unParticipante.esMejorQue(otroParticipante, this))
    
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