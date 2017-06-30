package domain

trait Reglas {
  
  var dragonesDisponibles : List[Dragon]
  
  def eleccionDeDragones(vikingos:List[Vikingo],posta:Posta,dragones:List[Dragon]) : List[Participante] = {
    dragonesDisponibles = dragones
    vikingos.map(vikingo => elegirFormaDeJugar(vikingo,dragonesDisponibles,posta))
  }
  
  def elegirFormaDeJugar(vikingo:Vikingo,dragones:List[Dragon], posta:Posta) : Participante = {
    val mejorMontura = vikingo.mejorMontura(dragones,posta)
    if(esMejorSinMontura(vikingo,mejorMontura,posta)) 
      vikingo
    else
      usarJinete(mejorMontura)
  }
  
  def esMejorSinMontura(vikingo: Vikingo, jinete: Jinete, posta: Posta) = vikingo.esMejorQue(jinete, posta)
   
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
trait Estandar extends Reglas{
  
  def decidirGanador(vikingos: List[Vikingo]) : Option[Vikingo] = {
    if(vikingos.isEmpty)
      None
    else
      Some(vikingos.head)
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
  
}
case object Veto extends Estandar
case object Handicap extends Estandar

//case class PorEquipos() extends Reglas