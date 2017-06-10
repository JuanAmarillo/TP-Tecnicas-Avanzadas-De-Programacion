package domain 
 
sealed trait Regla

case object Estandar extends Regla
case object Eliminacion extends Regla

case class Torneo(
		  postas: List[Posta],
		  participantesIniciales: List[Participante],
		  reglas : Reglas
		)
{
  
  def competir(regla : Regla) {
    reglas.decidirGanador(jugarPostas())
  }
  
  def jugarPostas() : List[Participante] = {
    postas.foldLeft(participantesIniciales){(participantes,posta) =>
     participantes match {
        case participante  :: participantes => jugarPosta(participantes,posta)
        case _                              => participantes
      }
    }
  }
  def jugarPosta(participantes: List[Participante],posta:Posta) : List[Participante] = {
    val jugadores = posta.participar(participantes)
    reglas.quienesAvanzan(jugadores)
    
  }
}

case class Reglas(){
  def quienesAvanzan(participantes: List[Participante]) = ???
  def decidirGanador(participantes: List[Participante]) = ???
}
