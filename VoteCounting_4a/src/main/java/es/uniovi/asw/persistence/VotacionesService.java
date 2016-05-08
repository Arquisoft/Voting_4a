package es.uniovi.asw.persistence;

import es.uniovi.asw.model.Election;

import java.util.List;

public interface VotacionesService {

	/**
	 * Metodo que comprueba, dada una votacion, si esta activa o no.
	 * 
	 * @param opcion boolean
	 * @return Lista de elecciones
	 */
	List<Election> getVoteInfo(boolean opcion);

}
