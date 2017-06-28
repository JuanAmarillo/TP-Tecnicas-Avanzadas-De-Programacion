package domain

import scala.util.Try


trait Participante{
  def peso : Int
  def barbarosidad : Int
  def capacidadDeCarga : Double
  def danio: Int
  def velocidad: Int
  def aumentarHambre(delta : Int) : Participante
  def estaHambriento() : Boolean
  def montar(dragon:Dragon) : Try[Jinete]
  def terminarPosta: Participante
  
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
  
  def aumentarHambre (delta : Int) = copy(vikingo = vikingo.aumentarHambre(5))
  
  def terminarPosta = copy(vikingo = vikingo.terminarPosta)
  
  def estaHambriento() = vikingo.estaHambriento()
  
  
  def montar(unDragon: Dragon) = Try(Jinete(this.vikingo,unDragon))
  
}


case class Vikingo(
      velocidadBase: Int = 1,
      barbarosidad: Int = 50, 
      pesoBase: Int = 60,
      nivelDeHambre: Int = 0,
      item: Item,
      efectos : EfectosPosta = EfectosPosta()
) extends Participante
{ 
  
  
  def danio = item.estadisticas(this).barbarosidad
  
  def velocidad = item.estadisticas(this).velocidadBase

  def peso = item.estadisticas(this).pesoBase
  
  def capacidadDeCarga = 0.5 * peso + 2 * barbarosidad
  
  def aumentarHambre(delta : Int) = copy(nivelDeHambre = hambreConEfectos(delta).min(100))
  
  def disminuirHambre(delta : Int) = copy(nivelDeHambre = (nivelDeHambre - delta).max(0))
  
  def hambreConEfectos(delta : Int) = nivelDeHambre + delta*efectos.aumentoDeHambre
  
  def terminarPosta = item.luegoDePosta(this)
  
  def estaHambriento = nivelDeHambre >= efectos.maxHambrePermitida
  
  def montar(unDragon:Dragon) = Try(Jinete(this,unDragon))
  
  def mejorMontura(dragones: List[Dragon], posta: Posta) : Participante =  { //  Jinete= 
     val participantes = posiblesJinetes(dragones) ++ List(this)
     posta.empezarPosta(participantes).head
  }
    
  def posiblesJinetes(dragones: List[Dragon]) = for {
      dragon <- dragones if dragon.puedeSerMontadoPor(this)    
    } yield montar(dragon).get

}

case class EfectosPosta(maxHambrePermitida: Int = 100, aumentoDeHambre: Int = 1)











