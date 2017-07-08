package domain 

case class Torneo(
		  postas:   List[Posta],
		  participantes: List[ParticipanteTorneo],
		  dragones: List[Dragon],
		  reglas : Reglas
		)
{
  
  def competir : Option[ParticipanteTorneo] = {
    reglas.elGanador(jugarPostas)
  }
  
  def jugarPostas : EstadoTorneo = {
    postas.foldLeft(EnJuego(participantes) : EstadoTorneo){(participantesEnJuego,posta) =>
      participantesEnJuego match {
        case EnJuego(participantes) => jugarPosta(participantes, posta)
        case Ganador(ganador)       => Ganador(ganador)
        case NoHayGanador           => NoHayGanador
      }
    }
  }
  
  def hayMasDeUnVikingo(participantes: List[ParticipanteTorneo]) = participantes.size > 1
  
  def jugarPosta(participantesEnJuego: List[ParticipanteTorneo],posta:Posta) : EstadoTorneo = {
    val vikingosListos = prepararParticipantes(participantesEnJuego).flatten
    val participantesListos = reglas.eleccionDeDragones(vikingosListos,posta,dragones)
    val ganadores = posta.participar(participantesListos)
    val vikingosGanadores = ganadores.map(_.vikingo)
    reglas.losQueAvanzan(vikingosGanadores)
    
  }
  
  def prepararParticipantes(participantes: List[ParticipanteTorneo]) : List[List[Vikingo]] = { participantes map {
    case vikingo: Vikingo => List() :+ vikingo
    case equipo:  Equipo => equipo.vikingos
    }
  }
}

sealed trait EstadoTorneo
case class  EnJuego(participantes : List[ParticipanteTorneo]) extends EstadoTorneo 
case class  Ganador(participante: ParticipanteTorneo) extends EstadoTorneo
case object NoHayGanador extends EstadoTorneo


