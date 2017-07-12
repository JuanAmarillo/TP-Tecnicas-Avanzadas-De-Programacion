package domain

import scala.util.Try

abstract class Reglas { 

  def eleccionDeDragones(vikingos:List[Vikingo],posta:Posta,dragones:List[Dragon]) = {
    participantesListosYDragonesDisponibles(vikingos, posta, dragones)._1
  }
  
  def participantesListosYDragonesDisponibles(vikingos:List[Vikingo],posta:Posta,dragones:List[Dragon]) = {
      vikingos.foldLeft((List(): List[ParticipantePosta],dragones)){ (participantesYDragones,vikingo) =>
       elegirFormaDeJugar(participantesYDragones._1,participantesYDragones._2,vikingo,posta)
    }
  }
  
  def elegirFormaDeJugar(participantesEnJuego:List[ParticipantePosta],dragonesDisponibles:List[Dragon],vikingo:Vikingo,posta:Posta) = {
    val mejorforma = vikingo.mejorForma(dragonesDisponibles, posta)
    (participantesEnJuego:+ mejorforma._1,mejorforma._2)
  }
    
  def laMitad(vikingos: List[Vikingo]) = vikingos.size/2
    
	def losQueAvanzan(vikingos: List[Vikingo]) = {
    darUnEstadoA(quienesAvanzan(vikingos))
	}
  
  def darUnEstadoA(participantes:List[ParticipanteTorneo]) = participantes.size match {
    	case 0 => NoHayGanador
	    case 1 => Ganador(participantes.head)
	    case _ => EnJuego(participantes)
  }
  
  
	def elGanador(participantes: EstadoTorneo) : Option[ParticipanteTorneo] = participantes match {
	  case Ganador(vikingo) =>  Some(vikingo)
	  case NoHayGanador     =>  None
	  case EnJuego(participanteEnJuego) => decidirGanador(participanteEnJuego)
	}
  
 def quienesAvanzan(vikingos: List[Vikingo]) : List[ParticipanteTorneo]
 def decidirGanador(participantes: List[ParticipanteTorneo]) : Option[ParticipanteTorneo]
}
 
class Estandar extends Reglas{
  
  def decidirGanador(participantes: List[ParticipanteTorneo]) : Option[ParticipanteTorneo] = 
    participantes.headOption
    
  def quienesAvanzan(vikingos: List[Vikingo]) : List[Vikingo] =
    vikingos.take(laMitad(vikingos))
    
  
}
case class Eliminacion(siguen:Int) extends Estandar{
  override def quienesAvanzan(vikingos: List[Vikingo]) =
    vikingos.take(siguen)
    
}
case object Inverso extends Estandar{
  
  override def quienesAvanzan(vikingos: List[Vikingo]) =
    vikingos.takeRight(laMitad(vikingos))
    
  override def decidirGanador(participantes:List[ParticipanteTorneo]) =
    super.decidirGanador(participantes.reverse)
  
}
case class Veto(condicion : RequisitoVeto ) extends Estandar{
  
  override def eleccionDeDragones(vikingos : List[Vikingo], posta : Posta, dragones: List[Dragon]): List[ParticipantePosta] = 
    super.eleccionDeDragones(vikingos,posta,restringirDragones(dragones))
  
  def restringirDragones(dragones: List[Dragon]) : List[Dragon] =
    dragones.filter(dragon => condicion.apply(dragon))
  
}
case object Handicap extends Estandar{
  
  override def eleccionDeDragones(vikingos : List[Vikingo], posta : Posta, dragones: List[Dragon]): List[ParticipantePosta] = 
   super.eleccionDeDragones(vikingos.reverse, posta,dragones).reverse
  
}

case object Equipos extends Reglas{
  
 override def decidirGanador(participantes:List[ParticipanteTorneo]) : Option[ParticipanteTorneo] = 
     participantes.sortWith((unParticipante, otroParticipante) => unParticipante.cuantosSon >= otroParticipante.cuantosSon)
       .headOption
       
 override def quienesAvanzan(vikingos: List[Vikingo]) : List[Equipo] =
   reOrganizarEnEquipos(avanzan(vikingos))
 
 def avanzan(vikingos: List[Vikingo]) = 
   vikingos.take(laMitad(vikingos))
   
 def reOrganizarEnEquipos(vikingos: List[Vikingo]) =
   equipos(vikingos).map(equipo => Equipo(equipo,vikingosDelEquipo(equipo,vikingos)))
   
 def vikingosDelEquipo(equipo : String,vikingos : List[Vikingo]) = 
   vikingos.filter(_.perteneceA(equipo))
   
 def equipos(vikingos : List[Vikingo]) =
   vikingos.map(_.equipo.get).distinct
   
}
  








