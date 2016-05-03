package asw.DBRepository;

import asw.Model.Voter;

public interface GetVoter {

	/**
	 * Método que dado un repositorioy un email y una contraseña de un votante, 
	 * devuelve el objeto Voter asociado al votante correspondiente.
	 * @param voterRepo, reposiorio donde almacenamos los votantes.
	 * @param email, email del votante a buscar.
	 * @param password, contraseña del votante a buscar.
	 * @return Objeto Voter asociado al votante buscado.
	 */
	Voter findVoter(VoterRepository voterRepo, String email, String password);
	
	/**
	 * Método que dado un repositorioy un email y una contraseña de un votante, 
	 * cambia la contraseña al objeto Voter asociado al votante correspondiente.
	 * @param voterRepo, reposiorio donde almacenamos los votantes.
	 * @param email, email del votante a buscar.
	 * @param oldPassword, contraseña actual del votante.
	 * @param newPassword, contraseña que se le va a asignar al votante.
	 * @return True, en caso de que el cambio se realice correctamente, False 
	 * en caso contrario.
	 */
	Boolean ChangePasswordVoter(VoterRepository voterRepo, String email, 
			String oldPassword, String newPassword);
}
