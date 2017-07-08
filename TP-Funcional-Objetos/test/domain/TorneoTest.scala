package domain

import org.junit.Assert._
import org.junit.Test

class TorneoTest {
  
  @Test
  def jueganUnCombate(){
    val torneo = new Torneo(List(new Combate(0)),participantesInvierno,dragones,new Estandar())
    val HipoLuego   = Hipo.copy(nivelDeHambre = 5)
    val AstridLuego = Astrid.copy(nivelDeHambre = 5)
    assertEquals(torneo.jugarPostas,List(HipoLuego,AstridLuego))
  }
  
  @Test
  def jueganUnCombateYUnaPesca(){
    val torneo = new Torneo(List(new Combate(0), new Pesca(0)),participantesInvierno,dragones,new Estandar())
    val HipoLuego   = Hipo.copy(nivelDeHambre = 10)
    val AstridLuego = Astrid.copy(nivelDeHambre = 10)
    assertEquals(torneo.jugarPostas,List(HipoLuego))
  }
  
  @Test
  def compitenEnCombateYPesca(){
    val torneo = new Torneo(List(new Combate(0), new Pesca(0)),participantesInvierno,dragones,new Estandar())
    val HipoLuego   = Hipo.copy(nivelDeHambre = 10)
    assertEquals(torneo.competir.get,HipoLuego)
  }
  
  @Test
  def compitenEnTresCombates(){
    val torneo = new Torneo(List(new Combate(0), new Combate(0), new Combate(0)),participantesInvierno,dragones,new Estandar())
    val HipoLuego = Hipo.copy(nivelDeHambre = 10)
    assertEquals(torneo.competir.get, HipoLuego)
  }
  
}