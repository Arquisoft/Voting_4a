package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.ReferendumOption;

public interface OpcionesService {

	/**
	 * Metodo que devuelve una lista con todas las opciones de una votacion da
	 * ésta.
	 * 
	 * @param v
	 *            La votacion de la que queremos obtener las opciones
	 * @return Lista con las opciones de voto
	 */
	List<Candidature> getOpciones(Election v);

}
