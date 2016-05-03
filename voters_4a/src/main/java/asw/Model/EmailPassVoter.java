package asw.Model;

import java.security.NoSuchAlgorithmException;

import asw.util.MD5;

public class EmailPassVoter {
	
	private String email;
	private String password;
	
	/**
	 * Constructor de la clase EmailPassVoter.
	 * @param email, email de un votante.
	 * @param password, contraseña de un votante.
	 * @throws NoSuchAlgorithmException 
	 */
	public EmailPassVoter(String email, String password) throws NoSuchAlgorithmException {
		super();
		this.email = email;
		this.password = MD5.getMD5(password);
	}
	
	/**
	 * Constructor de la clase EmailPassVoter sin parámetros.
	 */
	public EmailPassVoter() { }
	
	/**	
	 * Método de acceso a la propiedad email de la clase Voter.
	 * @return String con el email del votante.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Método de modificación de la propiedad email de la clase voter.
	 * @param email, String con el email del votante.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Método de acceso a la propiedad password de la clase Voter.
	 * @return String con la contraseña del votante.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Método de modificación de la propiedad password de la clase voter.
	 * @param password, String con la contraseña del votante.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
