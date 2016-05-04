package es.uniovi.asw.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * VotingPlace Created by ivan on 29/03/16.
 */
@Entity
public class VotingPlace implements Serializable {

	private static final long serialVersionUID = -3874085987261119609L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Long idVotingPlace;

	@OneToMany(mappedBy = "votingPlace", cascade = { CascadeType.ALL })
	private Set<Voter> voters = new HashSet<>();

	@OneToMany(mappedBy = "votingPlace", cascade = { CascadeType.ALL })
	private Set<Vote> votes = new HashSet<>();

	public VotingPlace() {
	}

	public VotingPlace(String name, Long idVotingPlace) {
		this.name = name;
		this.idVotingPlace = idVotingPlace;
	}

	public void addVoter(Voter voter) {
		if (voters.add(voter)) {
			voter.setVotingPlace(this);
		}
	}

	public void removeVoter(Voter voter) {
		if (voters.remove(voter)) {
			voter.setVotingPlace(null);
		}
	}

	public Set<Voter> getVoters() {
		return voters;
	}

	public void addVote(Vote vote) {
		if (votes.add(vote)) {
			vote.setVotingPlace(this);
		}
	}

	public void removeVote(Vote vote) {
		if (votes.remove(vote)) {
			vote.setVotingPlace(null);
		}
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public Long getId() {
		return id;
	}

	public Long getIdVotingPlace() {
		return idVotingPlace;
	}

	public void setIdVotingPlace(Long idVotingPlace) {
		this.idVotingPlace = idVotingPlace;
	}

	public String getName() {
		return name;

	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VotingPlace)) return false;

		VotingPlace that = (VotingPlace) o;

		return getName() != null ? getName().equals(that.getName()) : that.getName() == null && (getIdVotingPlace() != null ? getIdVotingPlace().equals(that.getIdVotingPlace()) : that.getIdVotingPlace() == null);

	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getIdVotingPlace() != null ? getIdVotingPlace().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "VotingPlace{" +
				"idVotingPlace=" + idVotingPlace +
				", name='" + name + '\'' +
				", id=" + id +
				'}';
	}
}
