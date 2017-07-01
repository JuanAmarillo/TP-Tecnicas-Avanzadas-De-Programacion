package domain

  trait Posta{
	  
    def participar(participantes :List[Participante]) : List[Participante] =
      aplicarEfectos(empezarPosta(participantes))
	  
	  
	  def aplicarEfectos(participantes:List[Participante]) = 
	    for{participante <- participantes} yield aplicarEfecto(participante)
	  
	  
    def empezarPosta[T <: Participante](participantes: List[T]) =
      for {
        participante <- ordenarPorMejor(participantes)  if puedeParticipar(participante) 
      }
      yield participante
      
    def ordenarPorMejor[T <: Participante](participantes : List[T]) = 
       participantes.sortWith((unParticipante,otroParticipante) => unParticipante.esMejorQue(otroParticipante, this))
      
    
    def puedeParticipar[T <: Participante](participante : T  ) = 
      cumpleCondicion(participante) && noVaAEstarHambriento(participante)
      
    def noVaAEstarHambriento[T <: Participante](participante:T) =
      !aplicarEfecto(participante).estaHambriento
      
    def aplicarEfecto(participante: Participante) = participante.aplicarEfecto(hambreLuegoDePosta).terminarPosta
    
    def cumpleCondicion(participante: Participante)   : Boolean
	  def esMejorQue(mejor:Participante,peor:Participante) : Boolean
    def hambreLuegoDePosta : Int
      
}
  case class Pesca(pesoMin : Int)     extends Posta {
    
   def cumpleCondicion(participante: Participante ) = participante.peso > pesoMin
   
   def hambreLuegoDePosta = 5
   
   def esMejorQue(mejor: Participante, peor: Participante) = mejor.capacidadDeCarga > peor.capacidadDeCarga 
   
  }
  case class Combate(barbarosidadMin: Int = 0)      extends Posta {
    
    def cumpleCondicion(participante: Participante) = barbarosidadMayorAlMinimo(participante) || tieneUnArma(participante)
    
    def barbarosidadMayorAlMinimo(participante: Participante) = participante.barbarosidad > barbarosidadMin
    
    def tieneUnArma(participante: Participante) = participante.vikingo.item match {
      case Arma(_,_,_)   => true
      case _             => false
    }
    
    def hambreLuegoDePosta = 10
    
    def esMejorQue(mejor: Participante, peor: Participante) = mejor.danio > peor.danio
    
  }
  case class Carrera(kms: Int) extends Posta {
    
    def cumpleCondicion(participante: Participante) = participante match {
      case Jinete(_,_) => true
      case _           => false
    }
    
    def hambreLuegoDePosta = kms
    
    def esMejorQue(mejor: Participante, peor: Participante) = mejor.velocidad > peor.velocidad
    
  }