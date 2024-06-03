package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberShipServiceImpl implements MemberShipService {

	@Autowired
	private NoticeMapper noticeDao;
	
	@Override
	@Transactional
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
			this.noticeDao.insert(notice);
			
			// title UK
			notice.setTitle(notice.getTitle()+ "-2");
			this.noticeDao.insert(notice);
		}

}


