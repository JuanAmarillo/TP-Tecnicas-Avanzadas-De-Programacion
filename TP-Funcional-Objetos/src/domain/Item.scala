package domain

  class Item(
      danio: Int,
      incrementos:Option[Incrementos])
      
  case object Hacha extends Item(30,None)
  case object Maza extends Item(100,None)
  case object SistemaDeVuelo   extends Item(0,Some(Incrementos(velocidad = 1.3))) 
  case object ComestibleHambre extends Item(0,Some(Incrementos(hambre    = 0.4)))
  
  case class Incrementos(
      velocidad : Double = 1,
      hambre: Double = 1
      )