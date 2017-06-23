package domain
case class Item(danio: Int,incrementos:Option[Incrementos])
case class Incrementos(velocidad : Double = 0,hambre: Double = 0)

trait Participante{
  def peso : Int
  def barbarosidad : Int
  def capacidadDeCarga : Double
  def danio: Int
  def velocidad: Double
  def nivelDeHambre(delta : Double) : Participante
  def estaHambriento() : Boolean
  def montar(dragon:Dragon) : Jinete
  
  def esMejorQue(participante:Participante, posta:Posta) = posta.esMejorQue(this,participante)
  
}
case class Jinete(
    vikingo: Vikingo,
    dragon : Dragon
) extends Participante
{
  require(dragon.puedeSerMontadoPor(vikingo))
  
  def danio = vikingo.danio  + dragon.danio
  def velocidad = dragon.velocidad - vikingo.peso
  def capacidadDeCarga = dragon.capacidadDeCarga - vikingo.peso
  def peso = vikingo.peso //+ dragon.peso
  def barbarosidad = vikingo.barbarosidad
  
  def nivelDeHambre (delta : Double) = copy(vikingo = vikingo.nivelDeHambre(0.5))
  def estaHambriento() = vikingo.estaHambriento()
  def montar(unDragon: Dragon) = Jinete(this.vikingo,unDragon)
  
}


case class Vikingo(
      peso: Int = 60,
      velocidadBase: Double = 1.0,
      barbarosidad: Int = 50, 
      nivelDeHambre: Double = 0.0,
      item: Item 
) extends Participante
{ 
  def danio = barbarosidad + item.danio
  def capacidadDeCarga = 0.5 * peso + 2 * barbarosidad
  def velocidad = velocidadBase //*item.incrementos.velocidad
  
  def nivelDeHambre (delta : Double) = copy(nivelDeHambre = nivelDeHambre + delta)
  def subirHambre(delta : Double) = (nivelDeHambre + delta).min(100.0)
  def estaHambriento() = nivelDeHambre == 100
  
  def montar(unDragon:Dragon) = Jinete(this,unDragon)
  
  def mejorMontura(dragones: List[Dragon], posta: Posta) : Participante =  { //  Jinete= 
     val participantes = posiblesJinetes(dragones) ++ List(this)
     posta.empezarPosta(participantes).head
  }
    
  def posiblesJinetes(dragones: List[Dragon]) = for {
      dragon <- dragones if dragon.puedeSerMontadoPor(this)    
    } yield montar(dragon)

}











