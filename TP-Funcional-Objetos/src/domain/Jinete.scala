package domain

sealed trait Participante{
  def peso : Int
  def barbarosidad : Int
}

case class Jinete(
    vikingo: Vikingo,
    dragon : Dragon
) extends Participante
{
  def danio = vikingo.danio // + dragon.danio
  def velocidad = dragon.velocidad - vikingo.danio
  def capacidadDeCarga = dragon.capacidadDeCarga - vikingo.peso
  def peso = vikingo.peso //+ dragon.peso
  def barbarosidad = vikingo.barbarosidad
  
}


case class Vikingo(
      peso: Int = 60,
      velocidad: Double = 1,
      barbarosidad: Int = 1, 
      nivelDeHambre: Int = 0,
      danioBase : Int = 1,
      item: Item 
) extends Participante
{
  def danio = this.danioBase + item.danio
  
  def montar(unDragon:Dragon) = {
    if(unDragon.puedeSerMontadoPor(this))
      Jinete(this,unDragon)
  }
      
}











