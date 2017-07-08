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
  
   @Test
  def porEquiposAvanzanTest(){
    val PatapezUno = Patapez.copy(equipo = Some("Uno"))
    val AstridUno  = Astrid.copy(equipo = Some("Uno"))
    val HipoDos    = Hipo.copy(equipo = Some("Dos"))
    val PatanDos   = Patan.copy(equipo = Some("Dos"))
    val PatapezTres   = Patapez.copy(equipo = Some("Tres"))
  
    val HipoDosLuego = HipoDos.copy(nivelDeHambre = 10)
    val PatanDosLuego  = PatanDos.copy(nivelDeHambre  = 10)
    
    val EquipoUno = Equipo("Uno",List(PatapezUno,AstridUno))
    val EquipoDos = Equipo("Dos",List(HipoDos,PatanDos,HipoDos))
    val EquipoTres= Equipo("Tres",List(PatapezTres))
    
    val EquipoDosLuego = Equipo("Dos",List(PatanDosLuego,HipoDosLuego))
    
    val torneo = new Torneo(List(new Combate(0)),List(EquipoTres,EquipoDos,EquipoUno),List(),Equipos)
    
    assertEquals(torneo.competir,Some(EquipoDosLuego))
  }
  
}