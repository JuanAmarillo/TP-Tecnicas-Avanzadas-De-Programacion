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
    val mejorFormaParaHipo = Hipo.mejorForma(dragones, new Combate(0))._1
    assertEquals(mejorFormaParaHipo, Jinete(Hipo,Slifer))
  }
  
  
  @Test
  def mejorMonturaDeHipoParaPesca(){
    val mejorFormaParaHipo = Hipo.mejorForma(dragones, new Pesca(0))._1
    assertEquals(mejorFormaParaHipo, Jinete(Hipo,DragonPesado))
  }
  
  @Test
  def noHayMejorMonturaParaAstrid(){
    assertEquals(Astrid.mejorForma(List(), new Combate(0))._1,Astrid)
  }
}