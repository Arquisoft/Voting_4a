package es.uniovi.asw.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Vote Created by ivan on 29/03/16.
 */
@Entity
public class Vote implements Serializable {

	private static final long serialVersionUID = -5096934069175328173L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private VotingPlace votingPlace;

	@ManyToOne
	private Candidature candidature;

	@Column(name="vote_option")
	private boolean option;

	public Vote() {
	}

	public Vote(boolean option) {
		this.option = option;
	}

	public Vote(VotingPlace votingPlace, Candidature candidature) {

		this.votingPlace = votingPlace;
		this.candidature = candidature;

		votingPlace.addVote(this);
		candidature.addVote(this);

	}

	public VotingPlace getVotingPlace() {
		return votingPlace;
	}

	public void setVotingPlace(VotingPlace votingPlace) {
		this.votingPlace = votingPlace;
	}

	public Candidature getCandidature() {
		return candidature;
	}

	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Vote{" + "id=" + id + ", votingPlace=" + votingPlace + ", candidature=" + candidature + '}';
	}

	public boolean getOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}
}