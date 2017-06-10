package domain

trait Reglas {
  def quienesAvanzan(participantes: List[Participante]) : List[Participante]
  def decidirGanador(participantes: List[Participante]) : Option[Participante]
  def eleccionDeDragones(participantes:List[Participante]) = ???
}
trait Estandar extends Reglas{
  
  def decidirGanador(participantes: List[Participante]) : Option[Participante] = {
    if(participantes.isEmpty)
      None
    else
      Some(participantes.head)
  }
  
  def quienesAvanzan(participantes: List[Participante]) =
    participantes.take(participantes.size / 2)
  
}
case class Eliminacion(siguen:Int) extends Estandar{
  override def quienesAvanzan(participantes: List[Participante]) =
    participantes.take(siguen)
}
case object Inverso extends Estandar{
  override def quienesAvanzan(participantes:List[Participante]) =
    participantes.takeRight(participantes.size/2)
}
case object Veto extends Estandar
case object Handicap extends Estandar

//case class PorEquipos() extends Reglas