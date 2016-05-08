package es.uniovi.asw.instanciator.impl.referendum;

import java.util.List;

import es.uniovi.asw.instanciator.VotesCalc;
import es.uniovi.asw.model.Vote;


public class ReferendumCalc extends VotesCalc{

	/* (non-Javadoc)
	 * @see es.uniovi.asw.instanciator.VotesCalc#calcularResultados(java.util.List)
	 */
	@Override
	public List<Vote> calcularResultados(List<Vote> votoscalculados) {
		//El referendum no implica ninguna operacion sobre los votos
		return votoscalculados;
	}
}
