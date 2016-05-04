package es.uniovi.asw.util;

import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbupdate.repositories.CandidatureRepository;
import es.uniovi.asw.dbupdate.repositories.DistrictRepository;
import es.uniovi.asw.dbupdate.repositories.ElectionCallRepository;
import es.uniovi.asw.dbupdate.repositories.ElectionRepository;
import es.uniovi.asw.dbupdate.repositories.RegionRepository;
import es.uniovi.asw.dbupdate.repositories.VoterRepository;
import es.uniovi.asw.dbupdate.repositories.VotingPlaceRepository;
import es.uniovi.asw.model.District;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.ElectionCall;
import es.uniovi.asw.model.ReferendumOption;
import es.uniovi.asw.model.Region;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.model.VotingPlace;
import es.uniovi.asw.model.types.ElectionDateTime;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DBLoader implements ApplicationListener<ContextRefreshedEvent> {

	private Logger log = LoggerFactory.getLogger(DBLoader.class);

	@Autowired
	private ElectionCallRepository electionCallRepository;

	@Autowired
	private ElectionRepository electionRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private CandidatureRepository candidatureRepository;

	@Autowired
	private VotingPlaceRepository placeRepository;

	@Autowired
	private VoterRepository voterRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		// Create a new election call

		ElectionCall electionCall = new ElectionCall("Referéndum por el modelo electoral 2016", "Referéndum por el modelo electoral arobado en 2016");
		electionCallRepository.save(electionCall);
		log.info("Saved electionCall - id: " + electionCall.getId());

		// Create a new election

		Election election = new Election();

		election.setName("¿Estás de acuerdo con el nuevo modelo electoral?");
		election.setDescription("Referéndum por el modelo electoral 2016");

		ElectionDateTime electionDateTime = new ElectionDateTime();
		Calendar calendar = Calendar.getInstance();

		Timestamp start = new Timestamp(calendar.getTimeInMillis());
		Timestamp end = new Timestamp(calendar.getTimeInMillis() + 1000000);

		electionDateTime.setStartTime(start);
		electionDateTime.setEndTime(end);
		election.setElectionDateTime(electionDateTime);

		// Link the election call with the election
		electionCall.addElection(election);
		electionRepository.save(election);

		log.info("Saved election - id: " + election.getId());

		// Create and link the region with it's election
		Region region = new Region();
		region.setName("España");

		election.addRegion(region);
		regionRepository.save(region);

		log.info("Saved region - id: " + region.getId());

		// Create a new district and link
		District district = new District();
		district.setName("Estado");

		region.addDistrict(district);
		districtRepository.save(district);

		log.info("Saved district - id: " + district.getId());

		ReferendumOption referendumOption = new ReferendumOption();
		referendumOption.setOption("Sí");

		district.addCandidature(referendumOption);
		candidatureRepository.save(referendumOption);

		ReferendumOption referendumOption2 = new ReferendumOption();
		referendumOption2.setOption("No");

		district.addCandidature(referendumOption2);
		candidatureRepository.save(referendumOption2);

	}

}
