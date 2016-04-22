package asw.Model;

public class EmailCodigoVoter {
	
	private long colegioelectoral;
	private String email;
	
	/**
	 * Método de acceso a la propiedad colegioelectoral de la clase Voter.
	 * @return Long con el numero del colegio electoral correspondiente
	 * al votante.
	 */
	public long getColegioelectoral() {
		return colegioelectoral;
	}
	
	/**
	 * Método de modificación de la propiedad colegioelectoral de la clase voter.
	 * @param colegioelectoral, Long con el numero del colegio electoral correspondiente
	 * al votante.
	 */
	public void setColegioelectoral(long colegioelectoral) {
		this.colegioelectoral = colegioelectoral;
	}
	
	/**	Método de acceso a la propiedad email de la clase Voter.
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
}
