package domain

class Dragon(
     velocidadBase : Int = 60,
     danioBase: Int,
     peso : Int,
     requisitos : List[RequisitoMontura]
){
  def danio = danioBase
	def velocidad : Int = this.velocidadBase - this.peso
	
	def capacidadDeCarga =  0.2 * peso
	
  def puedeSerMontadoPor(vikingo : Vikingo) = requisitos.forall(requisito => 
    requisito match {
      case Basico                      => capacidadDeCarga >= vikingo.peso
      case Vanidoso                    => danio > vikingo.danio
      case Barbaroso(barbarosidadMin)  => vikingo.barbarosidad > barbarosidadMin
      case Tiene(item)                 => vikingo.item.equals(item)
      case MuyPesado(pesoMax)          => vikingo.peso < pesoMax
    }
    
  )
}

case class FuriaNocturna(danioBase:Int,requisitos:List[RequisitoMontura])
    extends Dragon(180,danioBase,500,requisitos)

case class NadderMortifero(requisitos:List[RequisitoMontura])
  extends Dragon(60,2500,500,requisitos)

case class Gronckle(peso:Int,requisitos:List[RequisitoMontura])
  extends Dragon(20,5*peso,peso,requisitos)