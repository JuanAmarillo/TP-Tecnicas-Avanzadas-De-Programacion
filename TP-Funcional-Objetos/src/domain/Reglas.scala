package domain

import scala.util.Try

abstract class Reglas {
  
  var dragonesDisponibles: List[Dragon] = List()
  
  def eleccionDeDragones(vikingos:List[Vikingo],posta:Posta,dragones:List[Dragon]) : List[Participante] = {
    dragonesDisponibles = dragones
    elegirDragonesDisponibles(vikingos, posta)
  }
  
  def elegirDragonesDisponibles(vikingos: List[Vikingo],posta:Posta) : List[Participante] = {
    vikingos.map(vikingo => elegirFormaDeJugar(vikingo,posta))
  }
  
  def elegirFormaDeJugar(vikingo:Vikingo, posta:Posta) : Participante = {
    val mejorMontura = vikingo.mejorMontura(dragonesDisponibles,posta)
    if(esMejorSinMontura(vikingo,mejorMontura,posta)) 
      vikingo
    else
      usarJinete(mejorMontura.get)
  }
  
  def esMejorSinMontura(vikingo: Vikingo, jinete: Try[Jinete], posta: Posta) =
      jinete.isFailure || vikingo.esMejorQue(jinete.get, posta) 
  
   
  def usarJinete(jinete : Jinete): Jinete = {
    actualizarDragonesDisponibles(jinete.dragon)
    jinete
  }
  
  def actualizarDragonesDisponibles(dragonASacar: Dragon){
    dragonesDisponibles = dragonesDisponibles.filter(_ != dragonASacar)
  }
  
	def quienesAvanzan(vikingos: List[Vikingo]) : List[Vikingo]
  
	def decidirGanador(vikingos: List[Vikingo]) : Option[Vikingo]
}
class Estandar extends Reglas{
  
  def decidirGanador(vikingos: List[Vikingo]) : Option[Vikingo] = {
    vikingos.headOption
  }
  
  def quienesAvanzan(vikingos: List[Vikingo]) =
    vikingos.take(laMitad(vikingos))
    
  def laMitad(vikingos: List[Vikingo]) = vikingos.size/2
  
}
case class Eliminacion(siguen:Int) extends Estandar{
  override def quienesAvanzan(vikingos: List[Vikingo]) =
    vikingos.take(siguen)
    
}
case object Inverso extends Estandar{
  override def quienesAvanzan(vikingos: List[Vikingo]) =
    vikingos.takeRight(laMitad(vikingos))
    
  override def decidirGanador(vikingos:List[Vikingo]) =
    super.decidirGanador(vikingos.reverse)
  
}
case class Veto(condicion : RequisitoVeto ) extends Estandar{
  override def elegirDragonesDisponibles(vikingos : List[Vikingo], posta : Posta): List[Participante] = {
    dragonesDisponibles = restringirDragones(dragones)
    super.elegirDragonesDisponibles(vikingos,posta)
  }
  
  def restringirDragones(dragones: List[Dragon]) : List[Dragon] =
    dragones.filter(dragon => condicion.apply(dragon))
  
}
case object Handicap extends Estandar{
  override def elegirDragonesDisponibles(vikingos: List[Vikingo],posta:Posta) : List[Participante] = {
   super.elegirDragonesDisponibles(vikingos.reverse, posta).reverse
  }
}

//case class PorEquipos() extends Reglas








