package org.doit.senti.service.user;

import org.doit.senti.domain.user.MemberVO;

public interface SignUpService {

	boolean checkEmail(String member_email);
	void join(MemberVO member);
	
}
