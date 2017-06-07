package domain

class Dragon(
     velocidadBase : Int = 60,
     danio: Int,
     peso : Int,
     requisitos : List[RequisitoMontura]
){
  
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

case class FuriaNocturna(danio:Int,requisitos:List[RequisitoMontura])
    extends Dragon(180,danio,500,requisitos)

case class NadderMortifero(requisitos:List[RequisitoMontura])
  extends Dragon(???,150,???,requisitos)

case class Gronckle(requisitos:List[RequisitoMontura])
  extends Dragon(20,???,???,requisitos)