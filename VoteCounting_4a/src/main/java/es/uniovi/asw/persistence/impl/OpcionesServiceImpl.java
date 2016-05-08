package es.uniovi.asw.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.*;
import es.uniovi.asw.persistence.repository.CandidatureRepository;
import es.uniovi.asw.persistence.repository.DistrictRepository;
import es.uniovi.asw.persistence.repository.ElectionRepository;
import es.uniovi.asw.persistence.repository.RegionRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.persistence.OpcionesService;

@Service
public class OpcionesServiceImpl implements OpcionesService {

	@Autowired
	private CandidatureRepository candidatureRepository;

	@Autowired
	private ElectionRepository electionRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public List<Candidature> getOpciones(Election v) {

		List<Candidature> candidatures = new ArrayList<>();

		for (Region region : regionRepository.findByElection(electionRepository.findOne(v.getId()))) {
			for (District district : districtRepository.findByRegion(region)) {
				for (Candidature candidature : candidatureRepository.findByDistrict(district)) {
					candidatures.add(candidature);
				}
			}
		}

		return candidatures;
	}

}
