package es.uniovi.asw.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * District Created by ivan on 29/03/16.
 */
@Entity
public class District implements Serializable {

	private static final long serialVersionUID = -2075450364764273079L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	private Region region;

	@OneToMany(mappedBy = "district", cascade = { CascadeType.ALL })
	private Set<Candidature> candidatures = new HashSet<>();

	public District() {
	}

	public District(String name) {
		this.name = name;
	}

	public void addCandidature(Candidature candidature) {
		if (candidatures.add(candidature)) {
			candidature.setDistrict(this);
		}
	}

	public void removeCandidature(Candidature candidature) {
		if (candidatures.add(candidature)) {
			candidature.setDistrict(null);
		}
	}

	public Set<Candidature> getCandidatures() {
		return candidatures;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof District))
			return false;

		District district = (District) o;

		return getName() != null ? getName().equals(district.getName()) : district.getName() == null;

	}

	@Override
	public int hashCode() {
		return getName() != null ? getName().hashCode() : 0;
	}

	@Override
	public String toString() {
		return "District{" + "id=" + id + ", name='" + name + '\'' + ", region=" + region + '}';
	}
}
