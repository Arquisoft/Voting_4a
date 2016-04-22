package asw.Controller;
//modelo.attribute
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import asw.DBRepository.GetVoter;
import asw.DBRepository.VoterRepository;
import asw.DBRepository.Impl.GetVoterImpl;
import asw.Model.EmailCodigoVoter;
import asw.Model.EmailPassPassVoter;
import asw.Model.EmailPassVoter;
import asw.Model.Voter;
import asw.util.MD5;

@Controller
public class VoterController {
	
	@Autowired
	private VoterRepository voterRepo;
	
	/* Parte opcional de cambiar password */
	@RequestMapping(value="/cambiarPass", method=RequestMethod.GET)
    public String cambiarPassForm(Model model) {
        model.addAttribute("cambiarPass", new EmailPassPassVoter());
        return "nuevaPass";
    }

    @RequestMapping(value="/cambiarPass", method=RequestMethod.POST)
    public String cambiarPassSubmit(@ModelAttribute EmailPassPassVoter voterdto, 
    		Model model) {

		model.addAttribute("voterdto", voterdto);
		
    	if (cambiarPassword(voterdto) == true) 
            return "passChanged";
    	return "errorPassChanged";
    }
   
    /* Parte opcional: meter html para el acceso a votar */
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String votarForm(Model model) {
        model.addAttribute("emailpass", new EmailPassVoter());
        return "votar";
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String votarFormSubmit(@ModelAttribute EmailPassVoter voterdto, 
    		Model model) {

    	Voter tmps = votar(voterdto);
    	if (tmps != null) {
    		EmailCodigoVoter ecv = new EmailCodigoVoter();
    		ecv.setColegioelectoral(tmps.getColegioelectoral());
    		ecv.setEmail(tmps.getEmail());
    		model.addAttribute("resultado", ecv);
    		return "votarSuccess";
    	}
    	else {
    		model.addAttribute("resultado", voterdto);
    		return "votarError";
    	}
    }
    
    @RequestMapping(value="/user", method=RequestMethod.POST)
    @ResponseBody
    private ResponseEntity<EmailCodigoVoter> findUserPasswordEmail(
    		@RequestBody EmailPassVoter voterdto) {
    	
    	Voter tmps = votar(voterdto);
    	if (tmps != null) {
    		EmailCodigoVoter ecv = new EmailCodigoVoter();
    		ecv.setColegioelectoral(tmps.getColegioelectoral());
    		ecv.setEmail(tmps.getEmail());
    		return new ResponseEntity<EmailCodigoVoter>(ecv, HttpStatus.ACCEPTED);
    	}
    	else
    		return new ResponseEntity<EmailCodigoVoter>(HttpStatus.BAD_REQUEST);
    }
	
    @RequestMapping(value="/password", method=RequestMethod.POST)
    @ResponseBody
    private ResponseEntity<EmailPassVoter> changeUserPassword(
    		@RequestBody EmailPassPassVoter voterdto) {   	
    	
    	if (cambiarPassword(voterdto) == true)
    		return new ResponseEntity<EmailPassVoter>(new EmailPassVoter(
    				voterdto.getEmail(), voterdto.getNewPassword()), 
    				HttpStatus.ACCEPTED);
    	else
    		return new ResponseEntity<EmailPassVoter>(HttpStatus.BAD_REQUEST);
    }
    
    private Voter votar(EmailPassVoter voterdto) {
    	GetVoter voterAccess = new GetVoterImpl();
    	return voterAccess.findVoter(voterRepo, voterdto.getEmail(),
    			MD5.getMD5(voterdto.getPassword()));
    }
    
    private boolean cambiarPassword(EmailPassPassVoter voterdto) {
    	GetVoter voterAccess = new GetVoterImpl();
    	return voterAccess.ChangePasswordVoter(voterRepo, voterdto.getEmail(),
    			MD5.getMD5(voterdto.getOldPassword()), MD5.getMD5(voterdto.getNewPassword()));
    }
    
}