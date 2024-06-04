package org.doit.senti.mapper.board;

import java.sql.SQLException;

import org.doit.senti.domain.board.MemberVO;

public interface MemberMapper {

	// id에 해당하는 멤버 객체를 반환하는 메서드
	MemberVO getMember(String memberName) throws ClassNotFoundException, SQLException;

	// 회원가입 메서드
	int insert(MemberVO member) throws ClassNotFoundException, SQLException;

}
