

package object domain {

  type RequisitoMontura = (Vikingo,Dragon) => Boolean
  val vanidoso: RequisitoMontura = (vikingo,dragon) => dragon.danio > vikingo.danio
  val tiene: Item => RequisitoMontura = item => (vikingo,_) => vikingo.item == item
  val pesado: Int => RequisitoMontura = peso => (vikingo,_) => vikingo.peso < peso
  
  val Hacha   = Arma(30,0,10)
  val Maza    = Arma(100,0,40)
  val SistemaDeVuelo   = Arma(0,30,5)
  val ComestibleHambre = Consumible(4)
  
  val Hipo    = Vikingo(item = SistemaDeVuelo)
  val Astrid  = Vikingo(item = Hacha)
  val Patan   = Vikingo(item = Maza)
  val Patapez = Vikingo(item = ComestibleHambre, efectos = EfectosPosta(50,2))
  
  
  val Chimuelo = FuriaNocturna(100,List(tiene(SistemaDeVuelo)))
  val Slifer   = NadderMortifero(List(vanidoso))
  
  val participantesInvierno = List(Hipo,Astrid,Patan,Patapez)
  val dragones = List(Chimuelo,Slifer)  
  
  //val FestivalDeInvierno = Torneo(List(Pesca,Combate,Carrera),participantesInvierno)
      
}