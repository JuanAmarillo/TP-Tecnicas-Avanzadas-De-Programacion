package domain 
 
sealed trait Regla

case object Estandar extends Regla
case object Eliminacion extends Regla

case class Torneo(
		  postas: List[Posta],
		  participantes: List[Participante],
		  dragones: List[Dragon],
		  reglas : Reglas
		)
{
  
  def competir(regla : Regla) {
    reglas.decidirGanador(jugarPostas())
  }
  
  def jugarPostas() : List[Participante] = {
    postas.foldLeft(participantes){(ParticipantesEnJuego,posta) =>
     ParticipantesEnJuego match {
        case participante  :: participantes => jugarPosta(ParticipantesEnJuego,posta)
        case _                              => ParticipantesEnJuego
      }
    }
  }
  def jugarPosta(participantesEnJuego: List[Participante],posta:Posta) : List[Participante] = {
    val participantesListos = reglas.eleccionDeDragones(participantesEnJuego)
    val ganadores = posta.participar(participantesListos)
    reglas.quienesAvanzan(ganadores)
    
  }
}

case class Reglas(){
  def quienesAvanzan(participantes: List[Participante]) = ???
  def decidirGanador(participantes: List[Participante]) = ???
  def eleccionDeDragones(participantes:List[Participante]) = ???
}
