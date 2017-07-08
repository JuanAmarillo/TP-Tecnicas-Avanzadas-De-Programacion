package domain
import org.junit.Assert._
import org.junit.Test


class ReglasTest {
	val estandar = new Estandar
  
	@Test
	def eleccionDeDragonesEnCombateTest(){
    val HipoJinete    = Jinete(Hipo,Slifer)
    val AstridJinete  = Jinete(Astrid,DragonPesado)
    val ParticipantesListos = List(HipoJinete,AstridJinete,Patan,Patapez)
	  
	  assertEquals(estandar.eleccionDeDragones(participantesInvierno, Combate(), dragones),ParticipantesListos)
	}
	
	@Test
	def decidirGanadorEstandarDeLosParticipantesDeInviernoTest(){
	  assertEquals(estandar.decidirGanador(participantesInvierno).get,Hipo)
	}
	
	@Test 
	def decidirGanadorSinParticipantesTest(){
	  assertEquals(estandar.decidirGanador(List()), None)
	}
	
	@Test
	def quienesAvanzanEstandarTest(){
	  assertEquals(estandar.quienesAvanzan(participantesInvierno), List(Hipo,Astrid))
	}
	
	@Test
	def porEliminacionAvanzanCincoTest(){
	  val eliminacion = new Eliminacion(5)
	  assertEquals(eliminacion.quienesAvanzan(participantesInvierno),participantesInvierno)
	}
	
	@Test
	def porEliminacionAvanzanDosTest(){
	  val eliminacion = new Eliminacion(2)
	  assertEquals(eliminacion.quienesAvanzan(participantesInvierno), List(Hipo,Astrid))
	}
	
	@Test
	def porInversoAvanzanPatanYPatapezTest(){
	  assertEquals(Inverso.quienesAvanzan(participantesInvierno), List(Patan,Patapez))
	}
	
	@Test
	def porInversoGanaPatapezTest(){
	  assertEquals(Inverso.decidirGanador(participantesInvierno).get,Patapez)
	}
	
	@Test
	def restringirDragonesPorVetoConMasDeCienDeAtaqueTest(){
	  assertEquals(Veto(masDeCienDeAtaque).restringirDragones(dragones),List(Slifer,DragonPesado))
	}
	
	@Test
	def seEligenLosDragonesPorHandicapEnCombateTest(){
		val PatapezJinete  = Jinete(Patapez,Slifer)
		val AstridJinete   = Jinete(Astrid,DragonPesado)
    val HipoJinete     = Jinete(Hipo,Chimuelo)
    val ParticipantesListos = List(HipoJinete,AstridJinete,Patan,PatapezJinete)
	  
	  assertEquals(Handicap.eleccionDeDragones(participantesInvierno, Combate(), dragones),ParticipantesListos)
	}
	
  @Test
  def porEquiposAvanzanTest(){

  }
	
	
}