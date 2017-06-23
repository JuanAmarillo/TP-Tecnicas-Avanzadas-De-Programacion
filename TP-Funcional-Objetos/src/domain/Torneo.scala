package domain 

case class Torneo(
		  postas: List[Posta],
		  participantes: List[Participante],
		  dragones: List[Dragon],
		  reglas : Reglas
		)
{
  
  def competir(regla : Reglas) {
    val ganadores = jugarPostas()
    reglas.decidirGanador(jugarPostas())
  }
  
  def jugarPostas() : List[Participante] = {
    postas.foldLeft(participantes){(ParticipantesEnJuego,posta) =>
     ParticipantesEnJuego match {
        case participante :: Nil          => ParticipantesEnJuego
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


