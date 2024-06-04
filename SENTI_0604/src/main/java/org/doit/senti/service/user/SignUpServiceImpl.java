package org.doit.senti.service.user;

import org.doit.senti.domain.user.MemberVO;
import org.doit.senti.mapper.SignUpMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {

	private SignUpMapper signUpMapper;

	@Override
	public boolean checkEmail(String member_email) {
		log.info("> SignUpServiceImpl.checkEmail()...");
		return this.signUpMapper.dupliEmail(member_email)==1;
	}

	@Override
	public void join(MemberVO member) {
		log.info("> SignUpServiceImpl.join()...");
		this.signUpMapper.insert(member);
	}
	
	
}
