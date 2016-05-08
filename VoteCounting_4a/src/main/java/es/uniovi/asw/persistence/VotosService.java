package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.VotingPlace;

public interface VotosService {

	/**
	 * Metodo que devuelve todos los votos dado un colegio electoral.
	 * 
	 * @param ce colegio electoral
	 * @return lista de votos
	 */
	List<Vote> getAllVotes(VotingPlace ce);

	/**
	 * Metodo para actualizar el estado de un voto dado a leido, con el fin de
	 * que no se cunete dos veces.
	 * 
	 * @param v
	 */
	void updateVotes(Vote v);

	/**
	 * Metodo que devuelve todos los votos dado un colegio electoral, que aun no
	 * han sido recontados.
	 * 
	 * @param leido
	 * @return
	 */
	List<Vote> getVotes(boolean leido);

}
