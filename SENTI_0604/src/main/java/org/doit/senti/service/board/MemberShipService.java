package org.doit.senti.service.board;

import java.sql.SQLException;

import org.doit.senti.domain.board.InquiryVO;

public interface MemberShipService {

	public void insertAndPointUpOfMember(InquiryVO inquiry, int inquiryId) throws ClassNotFoundException, SQLException;
	
}
