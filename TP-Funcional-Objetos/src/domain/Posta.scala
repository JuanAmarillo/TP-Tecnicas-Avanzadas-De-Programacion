package domain

  trait Posta{
	  def cumpleCondicion(participante: Participante )   : Boolean
	  def aplicarEfectos(participante: Participante) : Participante
	  
    def participar(participantes : List[Participante]) = 
      for {
        participante <- ordenarPorMejor(participantes)  if puedeParticipar(participante) 
      }
      yield aplicarEfectos(participante)

    def ordenarPorMejor(participantes : List[Participante]) = 
       participantes.sortWith((unParticipante,otroParticipante) => unParticipante.esMejorQue(otroParticipante, this))
      
    
    def puedeParticipar(participante : Participante  ) = 
      cumpleCondicion(participante) && noVaAEstarHambriento(participante)
      
    def noVaAEstarHambriento(participante:Participante) =
      !aplicarEfectos(participante).estaHambriento()
}
  case class Pesca(pesoMin : Int)     extends Posta {
   def cumpleCondicion(participante: Participante ) = participante.peso > pesoMin
   def aplicarEfectos(participante:  Participante ) = participante.nivelDeHambre(0.5)
  }
  case class Combate(barbarosidadMin: Int)      extends Posta {
    def cumpleCondicion(participante: Participante) = participante.barbarosidad > barbarosidadMin
    def aplicarEfectos(participante:  Participante ) = participante.nivelDeHambre(0.1)
  }
  case class Carrera(kms: Int) extends Posta {
    def cumpleCondicion(participante: Participante) = ???
    def aplicarEfectos(participante:  Participante ) = participante.nivelDeHambre(0.1 * kms)
  }