package domain

import org.junit.Assert._
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
  
  @Test
  def mejorMonturaDeHipoParaCombate(){
    val mejorDragonParaHipo = Hipo.mejorMontura(dragones, new Combate(0)).get.dragon
    assertEquals(mejorDragonParaHipo, Slifer)
  }
  
  
  @Test
  def mejorMonturaDeHipoParaPesca(){
    val mejorDragonParaHipo = Hipo.mejorMontura(dragones, new Pesca(0)).get.dragon
    assertEquals(mejorDragonParaHipo, DragonPesado)
  }
  
  @Test
  def noHayMejorMonturaParaAstrid(){
    assertTrue(Astrid.mejorMontura(List(), new Combate(0)).isEmpty)
  }
}