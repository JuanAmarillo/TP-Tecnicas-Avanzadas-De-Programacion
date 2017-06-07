package domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class MontarDragonesTest {
  
  @Test
  def hipoMontaAChimuelo(){
    assertTrue(Chimuelo.puedeSerMontadoPor(Hipo))
  }
  
  @Test
  def patanNoPuedeMOntarAChimuelo(){
    assertFalse(Chimuelo.puedeSerMontadoPor(Patan))
  }
 

}