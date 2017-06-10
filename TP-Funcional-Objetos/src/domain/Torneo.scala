package domain 
 
sealed trait Regla

case object Estandar extends Regla
case object Eliminacion extends Regla

case class Torneo(
		  postas: List[Posta],
		  participantes: List[Participante],
		)
{
  def competir(regla : Regla) : Option[Participante] = {
      regla match {
      case Estandar        => 
      case Eliminacion     =>
    }
  }
}
