package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table (name="TVoter")
@Entity
public class Voter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String nif;
	@NotNull
	private Long code;
	@NotNull
	private String password;

	protected Voter() {}

	public Voter(String name, String email, String nif, Long code, String password) {
		this.name = name;
		this.nif = nif;
		this.code = code;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNif() {
		return nif;
	}

	@Override
	public String toString() {
		return "Voter [name=" + name + ", nif=" + nif + ", code=" + code + "]";
	}


}
