package org.doit.senti.service.board;

import java.sql.SQLException;

import org.doit.senti.domain.board.InquiryVO;
import org.doit.senti.mapper.board.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberShipServiceImpl implements MemberShipService {

	@Autowired
	private InquiryMapper inquiryDao;

	@Override
	public void insertAndPointUpOfMember(InquiryVO inquiry, int inquiryId) throws ClassNotFoundException, SQLException {
		this.inquiryDao.insert(inquiry);
		
		// title UK
		inquiry.setInquiryTitle(inquiry.getInquiryTitle()+ "-2");
		this.inquiryDao.insert(inquiry);
		
	}

}


