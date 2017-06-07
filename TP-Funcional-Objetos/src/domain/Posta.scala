package domain

  sealed trait Posta
  case object Pesca extends Posta 
  case object Combate extends Posta
  case object Carrera extends Posta