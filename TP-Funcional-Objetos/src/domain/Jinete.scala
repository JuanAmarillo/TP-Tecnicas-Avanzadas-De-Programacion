package domain


case class Jinete(
    vikingo: Vikingo,
    dragon : Dragon
)
{
  def danio = vikingo.danio // + dragon.danio
  def velocidad = dragon.velocidad - vikingo.danio
}


case class Vikingo(
      peso: Int = 60,
      velocidad: Double = 1,
      barbarosidad: Int = 1, 
      nivelDeHambre: Int = 0,
      danioBase : Int = 1,
      item: Item 
){
  def danio = this.danioBase + item.danio
  
  def montar(unDragon:Dragon) = {
    if(unDragon.puedeSerMontadoPor(this))
      Jinete(this,unDragon)
  }
      
}











