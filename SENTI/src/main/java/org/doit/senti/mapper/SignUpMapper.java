package org.doit.senti.mapper;

import org.apache.ibatis.annotations.Select;
import org.doit.senti.domain.user.MemberVO;

public interface SignUpMapper {
	
	// 회원가입
	void insert(MemberVO memver);
	
	// 이메일 중복확인
	int dupliEmail(String memeber_email);
	
	
	
}
