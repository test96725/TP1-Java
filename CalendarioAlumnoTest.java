package fiuba.algo3.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.*;

public class CalendarioAlumnoTest {
	
	@Test(expected=InvalidDateException.class)
	public void test01eventoMesInvalido(){
		
		List<String> invitados = new ArrayList<>();
		Calendario cal = new Calendario();		
		cal.agregarEvento("Clase Teorica", invitados, 2016, 13, 11, 10);
	}
	
	@Test(expected=InvalidDateException.class)
	public void test02eventoDiaInvalido(){
		
		List<String> invitados = new ArrayList<>();
		Calendario cal = new Calendario();		
		cal.agregarEvento("Clase Teorica", invitados, 2016, 12, 0, 10);
	}
	
	@Test(expected=InvalidDateException.class)
	public void test03eventoHoraInvalida(){
		
		List<String> invitados = new ArrayList<>();
		Calendario cal = new Calendario();		
		cal.agregarEvento("Clase Teorica", invitados, 2016, 12, 11, -1);
	}
	
	@Test(expected=InvalidDateException.class)
	public void test04eventoSemanalFechaInvalida(){
		//Con uno de estos es suficiente porque estamos probando que pase el
		//error proviniente de crearEvento() correctamente, no el error en si.
		List<String> invitados = new ArrayList<>();
		Calendario cal = new Calendario();		
		cal.agregarEventoSemanal("Clase Teorica", 3, invitados, 2016, 12, 11, -1);
	}
	
	@Test
	public void test05eventoSemanalFechaCorrecta() {
		
		List<String> invitados = new ArrayList<>();
		invitados.add("juan");

		Calendario cal = new Calendario();
		cal.agregarPersona("juan");
		
		cal.agregarEventoSemanal("Clase Algebra", 12, invitados, 2016, 10, 21, 10);
		
		// ocupado por 12 semanas (con corrimiento correcto de fechas)
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 10, 21, 10));//1
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 10, 28, 10));//2
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 11, 4, 10));//3
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 11, 11, 10));//4
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 11, 18, 10));//5
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 11, 25, 10));//6
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 12, 2, 10));//7
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 12, 9, 10));//8
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 12, 16, 10));//9
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 12, 23, 10));//10
		Assert.assertTrue(cal.estaOcupado("juan", 2016, 12, 30, 10));//11
		Assert.assertTrue(cal.estaOcupado("juan", 2017, 1, 6, 10));//12
		
		Assert.assertFalse(cal.estaOcupado("juan", 2017, 1, 13, 10));//desocupado la 13
	}
	
	@Test
	public void test06getInvitadoTest(){
		//Personas y Recursos usan los mismos metodos (de la superclase Invitado)
		//por lo que hacer las pruebas con uno lo prueba con el otro.
		Calendario cal = new Calendario();
		Persona p1 = new Persona("Juan"), p2 = new Persona("Gorge"), p3 = new Persona("Arnoldo");
		ArrayList<Persona> personas = new ArrayList<Persona>();
		personas.add(p1);
		personas.add(p2);
		personas.add(p3);
		Assert.assertTrue(cal.getInvitado(personas, "Juan").equals(p1));
		Assert.assertTrue(cal.getInvitado(personas, "Gorge").equals(p2));
		Assert.assertTrue(cal.getInvitado(personas, "Arnoldo").equals(p3));
	}
	
	@Test
	public void test07mesValido(){
		
		Calendario cal = new Calendario();
		Assert.assertFalse(cal.mesValido(0));
		Assert.assertFalse(cal.mesValido(-1));
		Assert.assertFalse(cal.mesValido(13));
		
		Assert.assertTrue(cal.mesValido(1));
		Assert.assertTrue(cal.mesValido(12));
	}
	
	@Test
	public void test08diaValido(){
		
		Calendario cal = new Calendario();
		Assert.assertFalse(cal.diaValido(0, 1));
		Assert.assertFalse(cal.diaValido(-1, 1));
		Assert.assertFalse(cal.diaValido(32, 1));//enero
		Assert.assertFalse(cal.diaValido(29, 2));//febrero
		Assert.assertFalse(cal.diaValido(32, 3));//marzo
		Assert.assertFalse(cal.diaValido(31, 4));//abril
		Assert.assertFalse(cal.diaValido(32, 5));//mayo
		Assert.assertFalse(cal.diaValido(31, 6));//julio
		Assert.assertFalse(cal.diaValido(32, 7));//junio
		Assert.assertFalse(cal.diaValido(32, 8));//agosto
		Assert.assertFalse(cal.diaValido(31, 9));//septiembre
		Assert.assertFalse(cal.diaValido(32, 10));//octubre
		Assert.assertFalse(cal.diaValido(31, 2));//noviembre
		Assert.assertFalse(cal.diaValido(32, 2));//diciembre
		
		
		Assert.assertTrue(cal.diaValido(30, 4));
		Assert.assertTrue(cal.diaValido(31, 12));
	}
	
	@Test
	public void test09horaValida(){
		
		Calendario cal = new Calendario();
		Assert.assertFalse(cal.horaValida(0));
		Assert.assertFalse(cal.horaValida(-1));
		Assert.assertFalse(cal.horaValida(25));
		
		Assert.assertTrue(cal.horaValida(1));
		Assert.assertTrue(cal.horaValida(24));
	}
}