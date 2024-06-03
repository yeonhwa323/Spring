package org.doit.ik.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.MemberVO;

public interface MemberMapper {

	// id에 해당하는 멤버 객체를 반환하는 메서드
	// 회원정보 O + 권한정보 X
	MemberVO getMember(String id) throws ClassNotFoundException, SQLException;

	// 회원가입 메서드
	int insert(MemberVO member) throws ClassNotFoundException, SQLException;

	// 회원정보 O + 권한정보 O
	public MemberVO read(@Param("userid") String userid) throws ClassNotFoundException, SQLException;

}
