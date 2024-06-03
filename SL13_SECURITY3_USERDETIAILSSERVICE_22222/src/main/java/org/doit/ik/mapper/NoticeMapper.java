package org.doit.ik.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.doit.ik.domain.NoticeVO;

public interface NoticeMapper {
	
	public int getCount(@Param("field") String field,@Param("query") String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항의 목록을 List 컬렉션으로 반환하는 메서드
	public List<NoticeVO> getNotices(int page , String field , String query) throws ClassNotFoundException, SQLException;
	
	
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	
	
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException;
		
	
	
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;
	

	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException ;
	
	//트랜잭션 처리를 위한 메서드 추가
	
	//public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException;
	
	public void hitup(String seq) throws ClassNotFoundException, SQLException ;
	
	public int gethit(String seq) throws ClassNotFoundException, SQLException ;

}
