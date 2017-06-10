package domain

trait Participante{
  def peso : Int
  def barbarosidad : Int
  def capacidadDeCarga : Double
  def danio: Int
  def velocidad: Double
  def nivelDeHambre(delta : Double) : Participante
  def estaHambriento() : Boolean
  
  def esMejorQue(participante:Participante, posta:Posta) = posta match {
    case Pesca(_)   => this.capacidadDeCarga > participante.capacidadDeCarga
    case Combate(_) => this.danio > participante.danio
    case Carrera(_) => this.velocidad > participante.velocidad
  }
}

case class Jinete(
    vikingo: Vikingo,
    dragon : Dragon
) extends Participante
{
  require(dragon.puedeSerMontadoPor(vikingo))
  
  def danio = vikingo.danio // + dragon.danio
  def velocidad = dragon.velocidad - vikingo.peso
  def capacidadDeCarga = dragon.capacidadDeCarga - vikingo.peso
  def peso = vikingo.peso //+ dragon.peso
  def barbarosidad = vikingo.barbarosidad
  
  def nivelDeHambre (delta : Double) = copy(vikingo = vikingo.nivelDeHambre(0.5))
  def estaHambriento() = vikingo.estaHambriento()
  
}


case class Vikingo(
      peso: Int = 60,
      velocidadBase: Double = 1,
      barbarosidad: Int = 50, 
      nivelDeHambre: Double = 0,
      item: Item 
) extends Participante
{ 
  def danio = barbarosidad + item.danio
  def capacidadDeCarga = 0.5 * peso + 2 * barbarosidad
  def velocidad = velocidadBase //*item.incrementos.velocidad
  
  def nivelDeHambre (delta : Double) = copy(nivelDeHambre = nivelDeHambre + delta)
  def subirHambre(delta : Double) = (nivelDeHambre + delta).min(100)
  def estaHambriento() = nivelDeHambre == 100
  
  def montar(unDragon:Dragon) = Jinete(this,unDragon)
  
      
}











