package domain

import scala.util.Try


trait Participante{
  def peso : Int
  def barbarosidad : Int
  def capacidadDeCarga : Double
  def danio: Int
  def velocidad: Int
  def nivelDeHambre(delta : Int) : Participante
  def estaHambriento() : Boolean
  def montar(dragon:Dragon) : Try[Jinete]
  
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
  
  def nivelDeHambre (delta : Int) = copy(vikingo = vikingo.nivelDeHambre(5))
  def estaHambriento() = vikingo.estaHambriento()
  def montar(unDragon: Dragon) = Try(Jinete(this.vikingo,unDragon))
  
}


case class Vikingo(
      velocidadBase: Int = 1,
      barbarosidad: Int = 50, 
      pesoBase: Int = 60,
      nivelDeHambre: Int = 0,
      item: Item 
) extends Participante
{ 
  
  def luegoDePosta = item.luegoDePosta(this)
  
  def danio = item.estadisticas(this).barbarosidad
  
  def velocidad = item.estadisticas(this).velocidadBase

  def peso = item.estadisticas(this).pesoBase
  
  def capacidadDeCarga = 0.5 * peso + 2 * barbarosidad
  
  
  def nivelDeHambre (delta : Int) = copy(nivelDeHambre = subirHambre(delta))
  
  def subirHambre(delta : Int) = (nivelDeHambre + delta).min(100)
  
  def estaHambriento() = nivelDeHambre == 100
  
  def montar(unDragon:Dragon) = Try(Jinete(this,unDragon))
  
  def mejorMontura(dragones: List[Dragon], posta: Posta) : Participante =  { //  Jinete= 
     val participantes = posiblesJinetes(dragones) ++ List(this)
     posta.empezarPosta(participantes).head
  }
    
  def posiblesJinetes(dragones: List[Dragon]) = for {
      dragon <- dragones if dragon.puedeSerMontadoPor(this)    
    } yield montar(dragon).get

}











