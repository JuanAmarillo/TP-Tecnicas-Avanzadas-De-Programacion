package domain

import org.junit.Assert._
import org.junit.Test

class TorneoTest {
  @Test
  def jueganCombate(){
    val torneo = new Torneo(List(new Combate(0)),participantesInvierno,dragones,new Estandar())
    //print(torneo.jugarPostas)
   // print(torneo.competir())
  }
}