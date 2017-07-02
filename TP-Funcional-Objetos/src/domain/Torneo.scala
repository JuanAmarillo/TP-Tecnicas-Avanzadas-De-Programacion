package domain 

case class Torneo(
		  postas:   List[Posta],
		  participantes: List[ParticipanteTorneo],
		  dragones: List[Dragon],
		  reglas : Reglas
		)
{
  
  def competir : Option[ParticipanteTorneo] = {
    reglas.decidirGanador(jugarPostas)
  }
  
  def jugarPostas : List[ParticipanteTorneo] = {
    postas.foldLeft(participantes){(participantesEnJuego,posta) =>
      if(hayMasDeUnVikingo(participantesEnJuego) )
        jugarPosta(participantesEnJuego, posta)
      else
        participantesEnJuego
    }
  }
  
  def hayMasDeUnVikingo(participantes: List[ParticipanteTorneo]) = participantes.size > 1
  
  def jugarPosta(participantesEnJuego: List[ParticipanteTorneo],posta:Posta) : List[ParticipanteTorneo] = {
    val vikingosListos = ???
    val participantesListos = reglas.eleccionDeDragones(vikingosListos,posta,dragones)
    val ganadores = posta.participar(participantesListos)
    val vikingosReOrganizados = ??? // ganadores.map(_.vikingo)
    reglas.quienesAvanzan(vikingosReOrganizados)
    
  }
}




