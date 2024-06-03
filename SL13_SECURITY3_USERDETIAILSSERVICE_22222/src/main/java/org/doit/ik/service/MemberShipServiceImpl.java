package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberShipServiceImpl implements MemberShipService {

	private final NoticeMapper noticeMapper;
	
	@Override
	@Transactional
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		this.noticeMapper.insert(notice);
		
		notice.setTitle(notice.getTitle()+"-2");
		this.noticeMapper.insert(notice);
		
		
	}

}
