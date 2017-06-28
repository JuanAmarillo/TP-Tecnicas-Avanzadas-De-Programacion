package domain



class Dragon(
     velocidadBase : Int = 60,
     danioBase: Int,
     peso : Int,
     requisitos : List[RequisitoMontura] = null
){
  def danio = danioBase
	def velocidad : Int = this.velocidadBase - this.peso
	def capacidadDeCarga =  0.2 * peso
	
	def cumpleRequisitoBasico(vikingo:Vikingo) = capacidadDeCarga >= vikingo.peso // o mejor lo meto en List[Requistos]??
	def cumpleRequisitos(vikingo: Vikingo) = requisitos.forall(requisito => requisito.apply(vikingo, this))
  def puedeSerMontadoPor(vikingo : Vikingo) = cumpleRequisitoBasico(vikingo) && cumpleRequisitos(vikingo)

}

case class FuriaNocturna(danioBase:Int,requisitos:List[RequisitoMontura])
    extends Dragon(180,danioBase,500,vanidoso +: requisitos)

case class NadderMortifero(requisitos:List[RequisitoMontura])
  extends Dragon(60,150,500,requisitos)

case class Gronckle(peso:Int,pesoMontura:Int,requisitos:List[RequisitoMontura])
  extends Dragon(30,5*peso,peso,pesado(pesoMontura) +: requisitos)