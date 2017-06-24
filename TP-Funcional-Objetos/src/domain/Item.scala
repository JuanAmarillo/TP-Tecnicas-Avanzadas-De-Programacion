package domain

trait Item {
  def danio: Int
  def luegoDePosta(vikingo:Vikingo) : Vikingo
}

case class Arma(aumentarDanio:Int) extends Item{
  def danio = aumentarDanio
  def luegoDePosta(vikingo: Vikingo) = vikingo
}

case class Consumible(porcentajeHambre : Int) extends Item{
  def danio = 0
  def luegoDePosta(vikingo: Vikingo) = vikingo.nivelDeHambre((-1)*porcentajeHambre)
}