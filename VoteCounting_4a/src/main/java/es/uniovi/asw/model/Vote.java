package es.uniovi.asw.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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

	@Temporal(TemporalType.TIMESTAMP)
	private Date voteDate;

	@ColumnDefault(value="false")
	private boolean read;

	public Vote() {
	}

	public Vote(VotingPlace votingPlace, Candidature candidature) {

		this.votingPlace = votingPlace;
		this.candidature = candidature;

		votingPlace.addVote(this);
		candidature.addVote(this);

		voteDate = new Timestamp(new Date().getTime());

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

	public Date getVoteDate() {
		return voteDate;
	}

	public void setVoteDate(Timestamp voteDate) {
		this.voteDate = voteDate;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vote)) return false;

		Vote vote = (Vote) o;

		if (isRead() != vote.isRead()) return false;
		if (getId() != null ? !getId().equals(vote.getId()) : vote.getId() != null) return false;
		if (getVotingPlace() != null ? !getVotingPlace().equals(vote.getVotingPlace()) : vote.getVotingPlace() != null)
			return false;
		return getCandidature() != null ? getCandidature().equals(vote.getCandidature()) : vote.getCandidature() == null && (getVoteDate() != null ? getVoteDate().equals(vote.getVoteDate()) : vote.getVoteDate() == null);

	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getVotingPlace() != null ? getVotingPlace().hashCode() : 0);
		result = 31 * result + (getCandidature() != null ? getCandidature().hashCode() : 0);
		result = 31 * result + (getVoteDate() != null ? getVoteDate().hashCode() : 0);
		result = 31 * result + (isRead() ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Vote{" +
				"id=" + id +
				", votingPlace=" + votingPlace +
				", candidature=" + candidature +
				", voteDate=" + voteDate +
				", read=" + read +
				'}';
	}
}
