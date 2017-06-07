

package object domain {
  
  val Hacha   = Item(30,None)
  val Maza    = Item(100,None)
  val SistemaDeVuelo   = Item(0,Some(Incrementos(velocidad = 1.3)))
  val ComestibleHambre = Item(0,Some(Incrementos(hambre    = 0.4)))
  
  val Hipo    = Vikingo(item = SistemaDeVuelo)
  val Astrid  = Vikingo(item = Hacha)
  val Patan   = Vikingo(item = Maza)
  val Patapez = Vikingo(item = ComestibleHambre)
  
  val Chimuelo = FuriaNocturna(100,List(Basico,Tiene(SistemaDeVuelo)))
  
  val FestivalDeInvierno = Torneo(List(Pesca,Combate,Carrera),List(Hipo,Astrid,Patan,Patapez))
      
}