package es.uniovi.asw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * VotedElection
 * Created by ivan on 4/04/16.
 */
@Entity
public class VotedElection implements Serializable {

	private static final long serialVersionUID = 3125185227897200641L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Voter voter;

	private Long idElection;

	public VotedElection() {
	}

	public VotedElection(Long idElection) {
		this.idElection = idElection;
	}

	public Long getId() {
		return id;
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	public Long getIdElection() {
		return idElection;
	}

	public void setIdElection(Long idElection) {
		this.idElection = idElection;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VotedElection)) return false;

		VotedElection that = (VotedElection) o;

		if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
		return getIdElection() != null ? getIdElection().equals(that.getIdElection()) : that.getIdElection() == null;

	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getIdElection() != null ? getIdElection().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "VotedElection{" +
				"id=" + id +
				", voter=" + voter +
				", idElection=" + idElection +
				'}';
	}
}
