package domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test
import scala.util.Try
import scala.util.Success
import scala.util.Failure

class MonturaTest {
  
  val jineteExitoso : Try[Jinete] = Hipo.montar(Chimuelo)
  val jineteNoExitoso : Try[Jinete] = Astrid.montar(Chimuelo)
  
  @Test
  def HipoPuedeMontarAChimuelo() {
    assertTrue(jineteExitoso.isSuccess)
  }
  
  @Test
  def AstridNoPuedeMontarAChimuelo() {
    assertTrue(jineteNoExitoso.isFailure)
  }
}