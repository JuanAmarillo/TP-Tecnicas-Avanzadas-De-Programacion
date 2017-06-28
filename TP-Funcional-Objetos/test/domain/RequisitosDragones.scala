package domain

import org.junit.Assert._
import org.junit.Test

class RequisitosDragones {
  
  @Test
  def HipoCumpleRequisitosDeChimuelo(){
    assertTrue(Chimuelo.cumpleRequisitos(Hipo))
  }
  
  @Test
  def AstridNoCumpleLosRequisitosDeChimuelo(){
    assertFalse(Chimuelo.cumpleRequisitos(Astrid))
  }
  
  @Test
  def dragonConMuchosRequisitosPuedeSerMontado(){
    val dragon = new Dragon(60,1000,500,List(vanidoso,tiene(Hacha),pesado(90)))
    val vikingo = new Vikingo(item = Hacha)
    assertTrue(dragon.cumpleRequisitos(vikingo))
  }
  @Test
  def dragonConMuchosRequisitosNoPuedeSerMontado(){
    val dragon = new Dragon(60,1000,500,List(vanidoso,tiene(Hacha),pesado(40)))
    val vikingo = new Vikingo(item = Hacha)
    assertFalse(dragon.cumpleRequisitos(vikingo))
  }
}