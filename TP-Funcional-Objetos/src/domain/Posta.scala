package domain

  trait Posta{
	  def cumpleCondicion(participante: Participante )   : Boolean
	  def aplicarEfectos(participante: Participante) : Unit
	  
    def participar(participantes : List[Participante]) = 
      participantes.filter(participante => puedeParticipar(participante))
      .sortWith((unParticipante,otroParticipante) => unParticipante.esMejorQue(otroParticipante, this))
      //.map(participante => aplicarEfectos(participante))
      
    def puedeParticipar(participante : Participante  ) = 
      cumpleCondicion(participante) && suHambreNoLLegaACien(participante)
      
    def suHambreNoLLegaACien(participante:Participante) =
      participante.hambreLuegoDe(this) < 100
}
  case class Pesca(pesoMin : Int)     extends Posta {
   def cumpleCondicion(participante: Participante     ) = participante.peso > pesoMin 
   def aplicarEfectos(participante: Participante) = participante.nivelDeHambre(0.05)
  }
  case class Combate(barbarosidadMin: Int)      extends Posta {
    def cumpleCondicion(participante: Participante) = participante.barbarosidad > barbarosidadMin
    def aplicarEfectos(participante: Participante) = participante.nivelDeHambre(0.1)
  }
  case class Carrera(kms: Int) extends Posta {
    def cumpleCondicion(participante: Participante) = ???
    def aplicarEfectos(participante: Participante) = participante.nivelDeHambre(0.01*kms)
  }