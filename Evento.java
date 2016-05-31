package fiuba.algo3.modelo;

import java.util.List;

public class Evento {
	
	protected String nombre;
	protected List<String> listGuestNames;
	protected int anio;
	protected int mes;
	protected int dia;
	protected int hora;
	
	public Evento(String name, List<String> guests, int year, int month, int day, int hour) {
		nombre = name;
		listGuestNames = guests;
		anio = year;
		mes = month;
		dia = day;
		hora = hour;
	}
	
}