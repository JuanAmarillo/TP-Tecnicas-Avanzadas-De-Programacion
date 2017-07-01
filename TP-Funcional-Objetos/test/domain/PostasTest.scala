package domain

import org.junit.Assert._
import org.junit.Test

class PostasTest {
     
	@Test
	def patanEsMejorQueAstridEnCombate(){
		assertTrue(Patan.esMejorQue(Astrid, Combate(0)))
	}
  
  @Test
  def patapezParticipaEnCombate(){
    val PatapezDespuesDeCombate = Vikingo(nivelDeHambre = 16,item = ComestibleHambre, efectos = EfectosPosta(50,2))
    assertTrue(Combate(0).participar(List(Patapez)).head.equals(PatapezDespuesDeCombate))
  }
  
  @Test
  def patanGanaLaPostaDeCombate(){
    val PatanDespuesDeCombate = Patan.copy(nivelDeHambre = 10)
    assertTrue(Combate(0).participar(participantesInvierno).head.equals(PatanDespuesDeCombate))
  }
  
  @Test
  def PatanGanaLaPostaDePesca(){
    val PatanDespuesDePesca = Patan.copy(nivelDeHambre = 5)
    assertEquals(Pesca(0).participar(participantesInvierno).head,PatanDespuesDePesca)
  }
  
  @Test
  def combateSinParticipantes(){
    assertTrue(new Combate(0).participar(List()).isEmpty)
  }
  
  @Test
  def vikingosNoPuedenJugarCarrera(){
    assertTrue(new Carrera(10).participar(participantesInvierno).isEmpty)
  }
  
  @Test
  def jineteHipoJuegaCarrera(){
    val jineteHipo = Jinete(Hipo,Chimuelo)
    val jineteHipoLuegoDeCarrera = jineteHipo.copy(vikingo = Hipo.copy(nivelDeHambre = 5))
    assertEquals(new Carrera(10).participar(List(jineteHipo)).head,jineteHipoLuegoDeCarrera)
  }
   
}