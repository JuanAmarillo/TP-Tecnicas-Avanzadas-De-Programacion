package domain

sealed trait RequisitoMontura 

case object Basico    extends RequisitoMontura
case object Vanidoso  extends RequisitoMontura
case class  Barbaroso(barbarosidadMin:Int) extends RequisitoMontura
case class  Tiene(item:Item)               extends RequisitoMontura
case class  MuyPesado(pesoMax:Int)         extends RequisitoMontura