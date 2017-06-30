package domain 

case class Torneo(
		  postas: List[Posta],
		  vikingos: List[Vikingo],
		  dragones: List[Dragon],
		  reglas : Reglas
		)
{
  
  def competir() {
    reglas.decidirGanador(jugarPostas)
  }
  
  def jugarPostas : List[Vikingo] = {
    postas.foldLeft(vikingos){(vikingosEnJuego,posta) =>
      if(hayMasDeUnVikingo(vikingosEnJuego) )
        jugarPosta(vikingosEnJuego, posta)
      else
        vikingosEnJuego
    }
  }
  
  def hayMasDeUnVikingo(vikingos: List[Vikingo]) = vikingos.size > 1
  
  def jugarPosta(vikingosEnJuego: List[Vikingo],posta:Posta) : List[Vikingo] = {
    val participantesListos = reglas.eleccionDeDragones(vikingosEnJuego,posta,dragones)
    val ganadores = posta.participar(participantesListos)
    val vikingosGanadores = ganadores.map(_.vikingo)
    reglas.quienesAvanzan(vikingosGanadores)
    
  }
}




