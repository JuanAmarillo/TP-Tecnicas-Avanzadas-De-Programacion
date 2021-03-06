package domain

import scala.util.Try


trait ParticipantePosta{
  def peso : Int
  def barbarosidad : Int
  def capacidadDeCarga : Double
  def danio: Int
  def velocidad: Int
  def aplicarEfecto(delta : Int) : ParticipantePosta
  def estaHambriento : Boolean
  def terminarPosta: ParticipantePosta
  def vikingo: Vikingo
  
  def esMejorQue(participante:ParticipantePosta, posta:Posta) = posta.esMejorQue(this,participante)
  def actualizarDragones(dragones: List[Dragon]) :(ParticipantePosta,List[Dragon])
  
}

trait ParticipanteTorneo{
  def cuantosSon : Int
  def miembros : List[Vikingo]
}

case class Equipo( nombre : String , vikingos: List[Vikingo] ) extends ParticipanteTorneo{
  def cuantosSon = vikingos.size
  def miembros   = vikingos
}

case class Jinete(
    vikingo: Vikingo,
    dragon : Dragon
) extends ParticipantePosta
{
  require(dragon.puedeSerMontadoPor(vikingo))
  
  def danio = vikingo.danio  + dragon.danio
  
  def velocidad = dragon.velocidad - vikingo.peso
  
  def capacidadDeCarga = dragon.capacidadDeCarga - vikingo.peso
  
  def peso = vikingo.peso //+ dragon.peso
  
  def barbarosidad = vikingo.barbarosidad
  
  def aplicarEfecto(hambreAAumetar : Int) = copy(vikingo = vikingo.aplicarEfecto(5))
  
  def terminarPosta = copy(vikingo = vikingo.terminarPosta)
  
  def estaHambriento = vikingo.estaHambriento
  
  def actualizarDragones(dragones: List[Dragon]) = (this, dragones.filter(_ != dragon))
  
}


case class Vikingo(
      velocidadBase: Int = 1,
      barbarosidad: Int = 50, 
      pesoBase: Int = 60,
      nivelDeHambre: Int = 0,
      item: Item,
      efectos : EfectosPosta = EfectosPosta(),
      equipo : Option[String] = None
) extends ParticipantePosta with ParticipanteTorneo
{ 
  
  def vikingo = this
  
  def miembros = List(this)
  
  def danio = item.estadisticas(this).barbarosidad
  
  def velocidad = item.estadisticas(this).velocidadBase

  def peso = item.estadisticas(this).pesoBase
  
  def capacidadDeCarga = 0.5 * peso + 2 * barbarosidad
  
  def aplicarEfecto(hambre: Int)  = aumentarHambre(hambre * efectos.aumentoDeHambre)
  
  def aumentarHambre(hambre : Int) = copy(nivelDeHambre =  (nivelDeHambre + hambre).min(100))
  
  def disminuirHambre(hambre : Int) = copy(nivelDeHambre = (nivelDeHambre - hambre).max(0))
  
  def terminarPosta = item.luegoDePosta(this)
  
  def estaHambriento = nivelDeHambre >= efectos.maxHambrePermitida
  
  def montar(unDragon:Dragon) = Try(Jinete(this,unDragon))
  
  def mejorForma(dragones: List[Dragon], posta: Posta) : (ParticipantePosta,List[Dragon])=  {  
     val participantes = posiblesJinetes(dragones):+ this
     posta.jugarSinAplicarEfectos(participantes).map(_.actualizarDragones(dragones)).head
  }
  
  def actualizarDragones(dragones:List[Dragon]) : (ParticipantePosta,List[Dragon]) =
    (this,dragones)
    
  def posiblesJinetes(dragones: List[Dragon]) = for {
      dragon <- dragones if dragon.puedeSerMontadoPor(this)    
    } yield montar(dragon).get
    
  def perteneceA(equipo: String) =
      this.equipo.get == equipo
    
  
  def cuantosSon = 1
  
}

case class EfectosPosta(maxHambrePermitida: Int = 100, aumentoDeHambre: Int = 1)











