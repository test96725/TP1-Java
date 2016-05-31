package fiuba.algo3.modelo;

import java.util.ArrayList;

public class Invitado {
	
	protected String nombre;
	private ArrayList<Evento> listaEventos = new ArrayList<Evento>();
	
	public void setName(String name) {
		nombre = name;
	}
	
	public String getName() {
		return nombre;
	}
	
	public void addEvento(Evento evento) {
		listaEventos.add(evento);
	}
	
	public boolean equals(Invitado invitado){
		return nombre.equals(invitado.getName());
	}
	
	public boolean equals(String name){
		return nombre.equals(name);
	}
	
	public boolean estaOcupado(int anio, int mes, int dia, int hora){
		
		for(Evento evento : listaEventos){
			if((evento.anio == anio) && (evento.mes == mes) && (evento.dia == dia) && (evento.hora == hora))
				return true;
		}
		return false;
	}
}
