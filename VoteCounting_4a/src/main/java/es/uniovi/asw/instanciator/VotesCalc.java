package es.uniovi.asw.instanciator;

import java.util.List;

import es.uniovi.asw.model.Vote;


public abstract class VotesCalc {
	
	/**
	 * Metodo el cual dada una lista de votos, realiza el recuento de resultados.
	 * @param votoscalculados votos
	 * @return lista
	 */
	public abstract List<Vote> calcularResultados(List<Vote> votoscalculados);

}
