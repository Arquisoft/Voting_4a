package asw.DBRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import asw.Model.Voter;

@Component("repo")
public interface VoterRepository extends CrudRepository<Voter, Long>{

	/**
	 * Método para la obtención de un votante de nuestro repositorio
	 * a partir de su email y su contraseña.
	 * @param email, email del votante.
	 * @param password, contraseña del votante.
	 * @return Objeto Voter con el votante correspondiente.
	 */
	Voter findByEmailAndPassword(String email, String password);
}
