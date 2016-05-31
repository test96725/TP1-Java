package fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Calendario {

	private ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
	private ArrayList<Recurso> listaRecursos = new ArrayList<Recurso>();
	private ArrayList<Evento> listaEventos = new ArrayList<Evento>();
	
	//La siguiente variable indica cuantos dias hay cada mes, donde el mes 1 esta en la posicion 0
	private int[] diasPorMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private int mesMax = diasPorMes.length, horaMax = 24;
	
	public void agregarPersona(String persona) {
		if(!existePersona(persona)){
			Persona nPersona = new Persona(persona);
			listaPersonas.add(nPersona);
		}
	}
	
	public boolean existePersona(String persona){
		return (getInvitado(listaPersonas, persona) != null);
	}
	
	public void agregarRecurso(String recurso){
		if(!existeRecurso(recurso)){
			Recurso nRecurso = new Recurso(recurso);
			listaRecursos.add(nRecurso);
		}
	}
	
	public boolean existeRecurso(String recurso) {
		return (getInvitado(listaRecursos, recurso) != null);
	}
	
	public void agregarEvento(String nombre, List<String> invitados, int anio, int mes, int dia, int hora)
			throws InvalidDateException, RecursoOcupadoException {
		
		if(!dateIsValid(mes, dia, hora)) throw new InvalidDateException();
		
		Evento evento = new Evento( nombre, invitados, anio, mes, dia, hora);
		listaEventos.add(evento);
		
		for(Iterator<String> i = invitados.iterator(); i.hasNext();){
			String inviNombre = i.next();
			Invitado persona = getInvitado(listaPersonas, inviNombre);
			Invitado recurso = getInvitado(listaRecursos, inviNombre);
			if(recurso != null){
				if(estaOcupado(inviNombre, anio, mes, dia, hora)) 
					throw new RecursoOcupadoException();
				recurso.addEvento(evento);
			}
			else if(persona != null) {
				persona.addEvento(evento);
			}
			else i.remove();
		}
	}
	
	public boolean estaOcupado(String nombre, int anio, int mes, int dia, int hora) throws InvalidDateException{
		
		if(!dateIsValid(mes, dia, hora)) throw new InvalidDateException();
		if (!existePersona(nombre) && !existeRecurso(nombre)){
			return false;
		} else {
			ArrayList<? extends Invitado> lista;
			if(existePersona(nombre)) lista = listaPersonas;
			else lista = listaRecursos;
			Invitado invitado = getInvitado(lista, nombre);
			return invitado.estaOcupado(anio, mes, dia, hora);
		}
	}
	
	public void agregarEventoSemanal(String nombre, int cantSem, List<String> invitados,
			int anio, int mes, int dia, int hora) throws InvalidDateException {
		
		if(!dateIsValid(mes, dia, hora)) throw new InvalidDateException();
		
		int[] fecha = {anio, mes, dia};
		for(int i = 0; i < cantSem; i++){
			agregarEvento(nombre, invitados, fecha[0], fecha[1], fecha[2], hora);
			sumarSemana(fecha);
		}
	}
	
	private void sumarSemana(int[] fecha){
		//Se espera que fecha tenga 3 casilleros, con anio, mes y dia respectivamente.
		fecha[2] += 7;
		if(fecha[2] > diasPorMes[fecha[1]-1]){//Empieza un nuevo mes
			fecha[2] -= diasPorMes[fecha[1]-1];
			if(fecha[1]+1 > mesMax) {//Empieza un nuevo anio
				fecha[1] = 1;
				fecha[0]++;
			} else fecha[1]++;
		}
	}
	
	public Invitado getInvitado(ArrayList<? extends Invitado> lista, String nombre){
		
		Invitado temp = null;
		for(Invitado invitado : lista){
			if(invitado.equals(nombre))
				temp = invitado;
		}
		return temp;
	}
	
	public boolean dateIsValid(int mes, int dia, int hora){
		return (mesValido(mes) && diaValido(dia, mes) && horaValida(hora));
	}
	
	public boolean mesValido(int mes) {
		return ((mes > 0) && (mes <= mesMax));
	}
	
	//el valor de "mes" tiene que estar entre 1 y mesMax
	public boolean diaValido(int dia, int mes){
		return ((dia > 0) && (dia <= diasPorMes[ mes-1]));
	}
	
	public boolean horaValida(int hora) {
		return ((hora > 0) && (hora <= horaMax));
	}
	
}
