package es.uniovi.asw.controller;

import es.uniovi.asw.dbupdate.ports.GetVoterP;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.ElectionCall;
import es.uniovi.asw.model.ReferendumOption;
import es.uniovi.asw.parametersmanager.ports.InsertR;
import es.uniovi.asw.parametersmanager.ports.RParameters;
import es.uniovi.asw.util.Login;
import es.uniovi.asw.util.ParametersException;
import es.uniovi.asw.votingmanager.ports.GetParametersR;
import es.uniovi.asw.votingmanager.ports.RCheckVoter;
import es.uniovi.asw.votingmanager.ports.RVote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.uniovi.asw.model.Voter;

@Controller
public class Main {

	@Autowired
	private Login login;

	@Autowired
	private RParameters rParameters;

	@Autowired
	private InsertR insertR;

	@Autowired
	private GetParametersR getParametersR;

	@Autowired
	private RCheckVoter rCheckVoter;

	@Autowired
	private RVote rVote;

	@Autowired
	private GetVoterP getVoterP;

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Model model) {
		logger.info("Index page access");
		model.addAttribute("voter", new Voter());

		return new ModelAndView("index");
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(Model model) {
		logger.info("About Us page access");
		model.addAttribute("voter", new Voter());

		return new ModelAndView("about-us");
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminIndex(Model model) {
		logger.info("Admin page access");
		model.addAttribute("voter", new Voter());

		return new ModelAndView("index-electoral-board");
	}

	@RequestMapping(value = "/admin-vote", method = RequestMethod.GET)
	public ModelAndView adminVote(Model model) {
		logger.info("Admin Vote access");

		model.addAttribute("voter", new Voter());
		model.addAttribute("elections", rParameters.getElections());

		return new ModelAndView("admin-vote");
	}

	@RequestMapping(value = "/admin-vote", method = RequestMethod.POST)
	public ModelAndView adminVoteVoting(@RequestParam(value = "voterNIF") String nif, @RequestParam(value = "election") Long idElection, Model model) {
		logger.info("Admin Vote Voting access");
		model.addAttribute("elections", rParameters.getElections());

		try {

			Voter voter = getVoterP.getVoterByNif(nif);
			rVote.markVoterVoted(voter.getId(), idElection);
			model.addAttribute("info", "El votante con NIF " + nif + " ha sido marcado correctamente");

		}

		catch (ParametersException e) {
			logger.info("ERROR admin vote: " + e.getMessage());
			model.addAttribute("error", e.getMessage());
		}

		return new ModelAndView("admin-vote");
	}

	@RequestMapping(value = "/check-voter", method = RequestMethod.GET)
	public ModelAndView checkVoter(Model model) {
		logger.info("Check voter access");

		model.addAttribute("voter", new Voter());
		model.addAttribute("elections", rParameters.getElections());

		return new ModelAndView("check-voter");
	}

	@RequestMapping(value = "/check-voter", method = RequestMethod.POST)
	public ModelAndView checkVoterVoted(@RequestParam(value = "voterNIF") String nif, @RequestParam(value = "election") Long idElection, Model model) {
		logger.info("Check voter voted access");

		try {

			boolean voted = rCheckVoter.hasVoted(nif, idElection);
			model.addAttribute("nif", nif);
			model.addAttribute("voted", voted);

		}

		catch (ParametersException e) {
			logger.info("ERROR check-voter: " + e.getMessage());
			model.addAttribute("error", e.getMessage());
			model.addAttribute("elections", rParameters.getElections());
		}

		return new ModelAndView("check-voter");
	}

	@RequestMapping(value = "/vote/{id}/{voter}/{idVotingPlace}", method = RequestMethod.GET)
	public ModelAndView Vote(@PathVariable Long id, @PathVariable Long voter, @PathVariable Long idVotingPlace, Model model) {
		logger.info("Vote access");

		try {

			Election election = getParametersR.getReferendum(id);
			model.addAttribute("election", election);

			Iterable<ReferendumOption> referendumOptions = getParametersR.getReferendumOptions(election.getId());
			model.addAttribute("referendumOptions", referendumOptions);

			model.addAttribute("voter", voter);
			model.addAttribute("votingPlace", idVotingPlace);

		}

		catch (ParametersException e) {
			logger.info("ERROR vote: " + e.getMessage());
			model.addAttribute("error", e.getMessage());
		}

		return new ModelAndView("vote");
	}

	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public ModelAndView RegisterVote(@RequestParam(value = "voter") Long idVoter, @RequestParam(value = "votingPlace") Long votingPlace, @RequestParam(value = "election") Long election, @RequestParam(value = "id") Long id, Model model) {
		logger.info("Vote");

		try {

			rVote.registerVote(idVoter, votingPlace, election, id);
			model.addAttribute("info", "El voto ha sido contabilizado");

			model.addAttribute("elecciones", rParameters.getElectionCalls(idVoter));
			model.addAttribute("voter", getVoterP.getVoter(idVoter));

		}

		catch (ParametersException e) {
			logger.info("ERROR vote: " + e.getMessage());
			model.addAttribute("error", e.getMessage());
		}

		return new ModelAndView("index-voter");
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newElection(Model model) {
		logger.info("New Election page access");
		model.addAttribute("electionCall", new ElectionCall());

		return new ModelAndView("new-election-phase1");
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newElection(@RequestParam(value = "electionName") String electionName,
	                                @RequestParam(value = "electionDesc") String electionDesc,
	                                @RequestParam(value = "dateStart") String dateStart,
	                                @RequestParam(value = "dateEnd") String dateEnd,
	                                @RequestParam(value = "electionQuestion") String electionQuestion,
	                                @RequestParam(value = "electionResp1") String electionResp1,
	                                @RequestParam(value = "electionResp2") String electionResp2, Model model) {

		logger.info("Create Election access");

		try {

			insertR.createReferendum(electionName, electionDesc, dateStart, dateEnd, electionQuestion, electionResp1, electionResp2);
			model.addAttribute("info", "Referéndum creado correctamente");

		}

		catch (Exception e) {
			logger.info("ERROR newElection: " + e.getMessage());
			model.addAttribute("error", e.getMessage());
		}

		return new ModelAndView("new-election-phase1");
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "lg_username") String email,
	                          @RequestParam(value = "lg_password") String password, Model model) {

		logger.info("Login");

		try {

			Object loginData = login.auth(email, password);

			if (loginData == null) {
				logger.info("User/pass incorrect");
				model.addAttribute("error", "Usuario y/o password incorrectos");
				return new ModelAndView("index");
			}

			else if (loginData instanceof Voter) {
				logger.info("Login Usuario " + ((Voter) loginData).getName());
				model.addAttribute("voter", loginData);
				model.addAttribute("elecciones", rParameters.getElectionCalls(((Voter) loginData).getId()));
				return new ModelAndView("index-voter");
			}

			else if (loginData instanceof String) {
				logger.info("Login Junta Electoral");
				return new ModelAndView("index-electoral-board");
			}

		}

		catch (ParametersException e) {
			logger.info("ERROR: " + e.getMessage());
			model.addAttribute("error", e.getMessage());
		}

		return new ModelAndView("index");

	}

	/*@RequestMapping("candidaturas/{id}")
	public String showCandidaturas(@PathVariable Long id, Model model) {

		Region r = (Region) electionRepository.findOne(id).getRegions().toArray()[0];
		District circunscripcion = (District) r.getDistricts().toArray()[0];
		Collection<Candidature> candidaturas = circunscripcion.getCandidatures();
		model.addAttribute("candidaturas", candidaturas);

		return "candidaturas";
	}*/

}