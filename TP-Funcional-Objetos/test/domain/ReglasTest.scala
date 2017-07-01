package domain
import org.junit.Assert._
import org.junit.Test


class ReglasTest {
	val estandar = new Estandar
  
	@Test
	def eleccionDeDragonesTest(){
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
	def porInversoTest(){
	  assertEquals(Inverso.quienesAvanzan(participantesInvierno), List(Patan,Patapez))
	}
}