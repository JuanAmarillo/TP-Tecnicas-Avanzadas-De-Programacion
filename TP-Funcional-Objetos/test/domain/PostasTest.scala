package domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class PostasTest {
  
  val PatanDespuesDeCombate = Vikingo(60,1.0,50,0.1,item = Maza)
     
  @Test
  def patanEsMejorQueAstridEnCombate(){
    assertTrue(Patan.esMejorQue(Astrid, Combate(0)))
  }
  
  @Test
  def patanGanaLaPostaDeCombate(){
    assertTrue(Combate(0).participar(participantesInvierno).head.toString().equals(PatanDespuesDeCombate.toString()) )
  }
   
}