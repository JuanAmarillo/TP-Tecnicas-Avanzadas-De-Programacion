package domain

  sealed trait Item
  case object Hacha extends Item
  case object Maza extends Item
  case object SistemaDeVuelo extends Item 
  case object ComestibleHambre extends Item