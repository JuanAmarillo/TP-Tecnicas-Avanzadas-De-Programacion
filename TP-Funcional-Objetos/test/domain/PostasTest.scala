package domain

import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class PostasTest {
  
  @Test
  def patanEsMejorQueAstridEnCombate(){
    assertTrue(Patan.esMejorQue(Astrid, Combate(0)))
  }
  
  @Test
  def patanGanaLaPostaDeCombate(){
    assertTrue( Combate(0).participar(participantesInvierno).head.equals(Patan) )
  }
   
}