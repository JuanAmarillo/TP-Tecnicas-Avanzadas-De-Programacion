package domain

trait Participante{
  def peso : Int
  def barbarosidad : Int
  def capacidadDeCarga : Double
  def danio: Int
  def velocidad: Double
  def hambreLuegoDe(posta:Posta) : Double
  def nivelDeHambre(valor:Double) : Unit
  
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
  def danio = vikingo.danio // + dragon.danio
  def velocidad = dragon.velocidad - vikingo.peso
  def capacidadDeCarga = dragon.capacidadDeCarga - vikingo.peso
  def peso = vikingo.peso //+ dragon.peso
  def barbarosidad = vikingo.barbarosidad
  def nivelDeHambre = vikingo.nivelDeHambre
  
  def nivelDeHambre (valor : Double) = copy(vikingo = vikingo.copy(nivelDeHambre=nivelDeHambre+nivelDeHambre*0.05))
  
  def hambreLuegoDe(posta:Posta) = (nivelDeHambre * 0.05).min(100)
  
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
  
  def nivelDeHambre (valor : Double) = copy(nivelDeHambre = nivelDeHambre + nivelDeHambre*valor)
  
  def montar(unDragon:Dragon) = {
    if(unDragon.puedeSerMontadoPor(this))
      Jinete(this,unDragon)
  }
  
  def hambreLuegoDe(posta:Posta) = posta match {
    case Pesca(_)         => (nivelDeHambre + 0.05).min(100)
    case Combate(_)       => (nivelDeHambre + 0.1).min(100)
    case Carrera(kms:Int) => (nivelDeHambre + 0.1 * kms).min(100)
  }
      
}











