

package object domain {

  type RequisitoMontura = (Vikingo,Dragon) => Boolean
  val vanidoso: RequisitoMontura = (vikingo,dragon) => dragon.danio > vikingo.danio
  val tiene: Item => RequisitoMontura = item => (vikingo,_) => vikingo.item == item
  val pesado: Int => RequisitoMontura = peso => (vikingo,_) => vikingo.peso < peso
  
  val Hacha   = Item(30,None)
  val Maza    = Item(100,None)
  val SistemaDeVuelo   = Item(0,Some(Incrementos(velocidad = 1.3)))
  val ComestibleHambre = Item(0,Some(Incrementos(hambre    = 0.4)))
  
  val Hipo    = Vikingo(item = SistemaDeVuelo)
  val Astrid  = Vikingo(item = Hacha)
  val Patan   = Vikingo(item = Maza)
  val Patapez = Vikingo(item = ComestibleHambre)
  
  val Chimuelo = FuriaNocturna(100,List(tiene(SistemaDeVuelo)))
  val Slifer   = NadderMortifero(List(vanidoso))
  
  val participantesInvierno = List(Hipo,Astrid,Patan,Patapez)
  val dragones = List(Chimuelo,Slifer)  
  
  //val FestivalDeInvierno = Torneo(List(Pesca,Combate,Carrera),participantesInvierno)
      
}