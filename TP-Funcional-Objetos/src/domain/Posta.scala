package domain

  trait Posta{
	  
    def participar(participantes :List[Participante]) : List[Participante] =
      aplicarEfectos(empezarPosta(participantes))
	  
	  
	  def aplicarEfectos(participantes:List[Participante]) = 
	    for{participante <- participantes} yield aplicarEfecto(participante)
	  
	  
    def empezarPosta(participantes: List[Participante]) =
      for {
        participante <- ordenarPorMejor(participantes)  if puedeParticipar(participante) 
      }
      yield participante
      
    def ordenarPorMejor(participantes : List[Participante]) = 
       participantes.sortWith((unParticipante,otroParticipante) => unParticipante.esMejorQue(otroParticipante, this))
      
    
    def puedeParticipar(participante : Participante  ) = 
      cumpleCondicion(participante) && noVaAEstarHambriento(participante)
      
    def noVaAEstarHambriento(participante:Participante) =
      !aplicarEfecto(participante).estaHambriento
      
    def aplicarEfecto(participante: Participante) = participante.aplicarEfecto(hambreLuegoDePosta).terminarPosta
    
    def cumpleCondicion(participante: Participante )   : Boolean
	  def esMejorQue(mejor:Participante,peor:Participante) : Boolean
    def hambreLuegoDePosta : Int
      
}
  case class Pesca(pesoMin : Int)     extends Posta {
    
   def cumpleCondicion(participante: Participante ) = participante.peso > pesoMin
   
   def hambreLuegoDePosta = 5
   
   def esMejorQue(mejor: Participante, peor: Participante) = mejor.capacidadDeCarga > peor.capacidadDeCarga 
   
  }
  case class Combate(barbarosidadMin: Int)      extends Posta {
    
    def cumpleCondicion(participante: Participante) = participante.barbarosidad > barbarosidadMin
    
    def hambreLuegoDePosta = 10
    
    def esMejorQue(mejor: Participante, peor: Participante) = mejor.danio > peor.danio
    
  }
  case class Carrera(kms: Int) extends Posta {
    
    def cumpleCondicion(participante: Participante) = ???
    
    def hambreLuegoDePosta = kms
    
    def esMejorQue(mejor: Participante, peor: Participante) = mejor.velocidad > peor.velocidad
    
  }