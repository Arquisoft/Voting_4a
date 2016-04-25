package es.uniovi.asw.presentation.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import es.uniovi.asw.model.Opcion;
import es.uniovi.asw.model.Voto;

/**
 * Clase de utilidad para manejo de datos con votos
 * @author voting_4a
 *
 */
public class VotesUtil {

	/**
	 * Método que dada una lista de votos para diferentes opciones, los agrupa
	 * por opcion
	 * @param votes lista de votos
	 * @return Mapa de clave Opcion y valor Lista de votos
	 */
	public static Map<Opcion, List<Voto>> groupByOption (List<Voto> votes) {
		return votes.stream().collect(Collectors.groupingBy(Voto::getOpcion));
	}
}
