

package object domain {
  
  val Hipo    = Vikingo(item = SistemaDeVuelo)
  val Astrid  = Vikingo(item = Hacha)
  val Patan   = Vikingo(item = Maza)
  val Patapez = Vikingo(item = ComestibleHambre)
  
  val FestivalDeInvierno = Torneo(List(Pesca,Combate,Carrera),List(Hipo,Astrid,Patan,Patapez))
      
}