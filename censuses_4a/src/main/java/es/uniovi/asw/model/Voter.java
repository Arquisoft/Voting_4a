package es.uniovi.asw.model;

import javax.persistence.*;

@Entity
public class Voter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String email;
	private String nif;
	private Integer code;
	private String password;

	protected Voter() {}

	public Voter(String name, String email, String nif, Integer code, String password) {
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

	public Integer getCode() {
		return code;
	}

	public void setCode(int code) {
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
