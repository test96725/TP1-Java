package fiuba.algo3.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Invitado;
import fiuba.algo3.modelo.Evento;

public class InvitadoAlumnoTests {

	@Test
	public void test01setGetName(){
		
		Invitado inv = new Invitado();
		Assert.assertTrue(inv.getName() == null);
		inv.setName("Pedro");
		Assert.assertTrue(inv.getName() == "Pedro");
	}
	
	@Test
	public void test02eventoEstaOcupado(){
		
		List<String> invitados = new ArrayList<>();
		
		Evento evento = new Evento("test", invitados, 2016, 5, 5, 5);
		Invitado inv = new Invitado();
		Assert.assertFalse(inv.estaOcupado(2016, 5, 5, 5));
		inv.addEvento(evento);
		Assert.assertTrue(inv.estaOcupado(2016, 5, 5, 5));
	}
	
	@Test
	public void test03equals(){
		
		Invitado inv1 = new Invitado();
		Invitado inv2 = new Invitado();
		Invitado inv3 = new Invitado();
		inv1.setName("Jorge");
		inv2.setName("Jorge");
		inv3.setName("Raul");
		
		Assert.assertTrue(inv1.equals("Jorge"));
		Assert.assertTrue(inv1.equals(inv2));
		Assert.assertFalse(inv1.equals("Raul"));
		Assert.assertFalse(inv1.equals(inv3));
	}
}