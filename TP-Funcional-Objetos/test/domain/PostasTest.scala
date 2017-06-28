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
    val PatanDespuesDeCombate = Vikingo(nivelDeHambre = 10,item = Maza)
    assertTrue(Combate(0).participar(participantesInvierno).head.equals(PatanDespuesDeCombate))
  }
   
}