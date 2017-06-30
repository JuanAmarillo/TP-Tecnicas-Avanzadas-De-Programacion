package domain 

case class Torneo(
		  postas: List[Posta],
		  vikingos: List[Vikingo],
		  dragones: List[Dragon],
		  reglas : Reglas
		)
{
  
  def competir(regla : Reglas) {
    val ganadores = jugarPostas
    reglas.decidirGanador(ganadores)
  }
  
  def jugarPostas : List[Vikingo] = {
    postas.foldLeft(vikingos){(vikingosEnJuego,posta) =>
        jugarPosta(vikingosEnJuego, posta)
      
    }
  }
  def jugarPosta(vikingosEnJuego: List[Vikingo],posta:Posta) : List[Vikingo] = {
    val participantesListos = reglas.eleccionDeDragones(vikingosEnJuego,posta,dragones)
    val ganadores = posta.participar(participantesListos)
    val vikingosGanadores = ganadores.map(_.vikingo)
    reglas.quienesAvanzan(vikingosGanadores)
    
  }
}


