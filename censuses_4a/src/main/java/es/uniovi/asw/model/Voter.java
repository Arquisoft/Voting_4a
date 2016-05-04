package es.uniovi.asw.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Voter Created by ivan on 29/03/16.
 */
@Entity
public class Voter implements Serializable {

	private static final long serialVersionUID = -2570727675899821819L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String nif;

	@Column(nullable = false)
	private Long idVotingPlace;

	private String password;

	@ManyToOne
	private VotingPlace votingPlace;

	@OneToMany(mappedBy = "voter", cascade = { CascadeType.ALL })
	private Set<VotedElection> votedElections = new HashSet<>();

	public Voter() {
	}

	public Voter(String name, String email, String nif, Long idVotingPlace, String password) {
		this.name = name;
		this.nif = nif;
		this.idVotingPlace = idVotingPlace;
		this.email = email;
		this.password = password;
	}

	public void addVotedElection(VotedElection votedElection) {
		if (votedElections.add(votedElection)) {
			votedElection.setVoter(this);
		}
	}

	public void removeVotedElection(VotedElection votedElection) {
		if (votedElections.remove(votedElection)) {
			votedElection.setVoter(null);
		}
	}

	public Set<VotedElection> getVotedElections() {
		return votedElections;
	}

	public VotingPlace getVotingPlace() {
		return votingPlace;
	}

	public void setVotingPlace(VotingPlace votingPlace) {
		this.votingPlace = votingPlace;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Long getIdVotingPlace() {
		return idVotingPlace;
	}

	public void setIdVotingPlace(Long idVotingPlace) {
		this.idVotingPlace = idVotingPlace;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Voter))
			return false;

		Voter voter = (Voter) o;

		return getName() != null ? getName().equals(voter.getName())
				: voter.getName() == null && (getEmail() != null ? getEmail().equals(voter.getEmail())
						: voter.getEmail() == null
								&& (getNif() != null ? getNif().equals(voter.getNif()) : voter.getNif() == null));

	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + (getNif() != null ? getNif().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Voter{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", nif='" + nif + '\''
				+ ", idVotingPlace=" + idVotingPlace + ", password='" + password + '\'' + ", votingPlace="
				+ votingPlace + '}';
	}
}
