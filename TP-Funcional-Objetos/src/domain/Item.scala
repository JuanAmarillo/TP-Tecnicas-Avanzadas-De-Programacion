package domain

 case class Item(
      danio: Int,
      incrementos:Option[Incrementos])
  
  case class Incrementos(
      velocidad : Double = 1,
      hambre: Double = 1
   )