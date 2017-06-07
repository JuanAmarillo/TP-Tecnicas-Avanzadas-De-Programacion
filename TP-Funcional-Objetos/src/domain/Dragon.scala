package domain

class Dragon(
     velocidadBase : Int = 60,
     danio: Int,
     peso : Int,
     requisitos : List[RequisitoMontura]
){
  
	def velocidad : Int = this.velocidadBase - this.peso
	
  def puedeSerMontadoPor(vikingo : Vikingo) = requisitos.forall(requisito => 
    requisito match {
      case Basico                      => peso * 0.2 >= vikingo.peso
      case Vanidoso                    => danio > vikingo.danio
      case Barbaroso(barbarosidadMin)  => vikingo.barbarosidad > barbarosidadMin
      case tiene(item)                 => vikingo.item.equals(item)
      case MuyPesado(pesoMax)          => vikingo.peso < pesoMax
    }
    
  )
}

case class FuriaNocturna(danio:Int,requisitos:List[RequisitoMontura])
    extends Dragon(180,danio,???,requisitos)

case class NadderMortifero(requisitos:List[RequisitoMontura])
  extends Dragon(???,150,???,requisitos)

case class Gronckle(requisitos:List[RequisitoMontura])
  extends Dragon(20,???,???,requisitos)