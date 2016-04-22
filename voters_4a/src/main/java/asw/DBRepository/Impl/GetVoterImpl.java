package asw.DBRepository.Impl;

import asw.DBRepository.GetVoter;
import asw.DBRepository.VoterRepository;
import asw.Model.Voter;

public class GetVoterImpl implements GetVoter {
	
	@Override
	public Voter findVoter(VoterRepository voterRepo, String email, 
			String password) {
		return voterRepo.findByEmailAndPassword(email, password);
	
	}

	@Override
	public Boolean ChangePasswordVoter(VoterRepository voterRepo, 
			String email, String oldPassword, String newPassword) {
		Voter voter = findVoter(voterRepo,email,oldPassword);
		if(voter!=null){
			voter.setPassword(newPassword);
			voterRepo.save(voter);
			return true;
		}
		return false;
	}

}
