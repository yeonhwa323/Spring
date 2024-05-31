package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
// @Transactional
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	// @Autowired
	// private DataSourceTransactionManager transactionManager;
	
	// @Autowired
	// private TransactionTemplate transactionTemplate;
	
	// 공지사항의 갯수 반환하는 메서드
	@Override
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = " SELECT COUNT(*) CNT "
				   + " FROM NOTICES "
				   + " WHERE "+field+" LIKE :query";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("query", query);
		
		return this.npJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
		// return this.jdbcTemplate.queryForObject(sql, Integer.class,"%"+query+"%");
	}
	
	// 공지사항의 목록을 List 컬렉션으로 반환하는 메서드
	@Override
	public List<NoticeVO> getNotices(
			int page           // 현재 페이지 번호
			, String field     // 검색조건
			, String query     // 검색어
			) throws ClassNotFoundException, SQLException
	{					
		
		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...
		
		String sql = " SELECT * "
	               + "  FROM ( "
	               + "                 SELECT ROWNUM NUM, N.* "
	               + "                 FROM ("
	               + "                          SELECT * "
	               + "                          FROM NOTICES "
	               + "                          WHERE "+field+" LIKE :query "
	                     + "                   ORDER BY REGDATE DESC"
	                     + "                ) N"
	                     + "  ) "
	                 +  " WHERE NUM BETWEEN :srow AND :erow ";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("query", "%"+query+"%");
		paramMap.put("srow", srow);
		paramMap.put("erow", erow);
		
		return this.npJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
		// return this.jdbcTemplate.query(sql, new Object [] {"%"+query+"%",srow,erow} ,new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class) );
	}
	
	// 공지사항 삭제하는 메서드
	@Override
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "DELETE notices "
				  + " WHERE seq=:seq";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seq", seq);
		
		return this.npJdbcTemplate.update(sql, parameterSource);
	}
	
	// 공지사항 수정하는 메서드*****
	@Override
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException{
		
		String sql = "UPDATE NOTICES "
				  + " SET TITLE=:title, CONTENT=:content, FILESRC=:filesrc "
				  + " WHERE SEQ=:seq";
		
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(notice);
		return this.npJdbcTemplate.update(sql, parameterSource);
		
		/*
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("title", notice.getTitle());
		parameterSource.addValue("content", notice.getContent());
		parameterSource.addValue("filesrc", notice.getFilesrc());
		parameterSource.addValue("seq", notice.getSeq());
		
		return this.npJdbcTemplate.update(sql, parameterSource);
	    */
	}
	
	// 공지사항 보기
	@Override
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				  + " FROM NOTICES "
				  + " WHERE SEQ=:seq";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
	    parameterSource.addValue("seq", seq );
	      
	      return this.npJdbcTemplate.queryForObject(sql
	            , parameterSource
	            , new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	}

	// 공지사항 추가하는 메서드
	@Override
	@Transactional
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		
		  // A. 공지사항 쓰기
	      String sql = "INSERT INTO NOTICES"
	              + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
	              + " VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
	      // A
	      SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
	      npJdbcTemplate.update(sql,  parameterSource);

	      // B. 작성자 포인트 1증가
	      String   sql2    = " UPDATE member "
	            + " SET point  = point + 1 "
	            + " WHERE id = :id "; 
	      // B
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();
	      paramSource.addValue("id", "yeon");      
	      int updateCount = npJdbcTemplate.update(sql2, paramSource);
	      
	      return updateCount;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT)
	public void hitUp(String seq) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE notices "
	               + " SET hit = hit + 1 "
	               + " WHERE seq = :seq ";
	      MapSqlParameterSource paramSource =new MapSqlParameterSource();
	      paramSource.addValue("seq", seq);       
	      this.npJdbcTemplate.update(sql, paramSource);
		
	}

	@Override
	@Transactional
	public int getHit(String seq) throws ClassNotFoundException, SQLException {
		String sql = "SELECT hit  "
	               + " FROM notices "
	               + " WHERE seq = :seq";
	      
	      Map<String, Object> paramMap = new HashMap<String, Object>();
	      paramMap.put("seq", seq);
	      return this.npJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
		
	}
	
	// [5] 트랜잭션의 전파방식 설명 - 수정
	/*
	@Override
	//@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
	
		insert(notice);
		
		// title UK
		notice.setTitle(notice.getTitle()+ "-2");
		insert(notice);
	}
	*/
	// [4] 선언적 트랜잭션 처리
	/*
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
		    	+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
		    	+ " VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'sieun', SYSDATE, 0, :filesrc)";
	
	    SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
      
        this.npJdbcTemplate.update(sql,  parameterSource);
        
		// 2. 작성자 포인트 1증가
        sql = "UPDATE MEMBER "
		   + " SET point = point + 1 "
	   	   + " WHERE ID=:id";
		
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("id", id );
		this.npJdbcTemplate.update(sql, paramSource);
		
	}
	*/
	
	// [3] transactionTemplate을 사용하여 트랜잭션 처리
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 1. 공지사항 쓰기
		String sql1 = "INSERT INTO NOTICES"
		    	+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
		    	+ " VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'sieun', SYSDATE, 0, :filesrc)";
		
		// 2. 작성자 포인트 1증가 
		String sql2 = "UPDATE MEMBER "
				   + " SET point = point + 1 "
			   	   + " WHERE ID=:id";
		
		// p514 참조
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
		        npJdbcTemplate.update(sql1,  parameterSource);
				
		        MapSqlParameterSource paramSource = new MapSqlParameterSource();
			    paramSource.addValue("id", id );
				npJdbcTemplate.update(sql2, paramSource);
				
			}
		});
		
		
	}
	*/
	
	// [2] transactionManager를 사용해서 트랜잭션 처리
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 1. 공지사항 쓰기
		String sql1 = "INSERT INTO NOTICES"
		    	+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
		    	+ " VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'sieun', SYSDATE, 0, :filesrc)";
		
		// 2. 작성자 포인트 1증가 
		String sql2 = "UPDATE MEMBER "
				   + " SET point = point + 1 "
			   	   + " WHERE ID=:id";
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = this.transactionManager.getTransaction(definition );
		try {
			SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
	        this.npJdbcTemplate.update(sql1,  parameterSource);
			
	        MapSqlParameterSource paramSource = new MapSqlParameterSource();
		    paramSource.addValue("id", id );
			this.npJdbcTemplate.update(sql2, paramSource);
			
			this.transactionManager.commit(status);
		}catch (Exception e) {
			this.transactionManager.rollback(status);
		}
		
	}
    */
	
	// [1] 트랜잭션이 처리되지 않은 메서드
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		// 1. 공지사항 쓰기
		String sql = "INSERT INTO NOTICES"
		    	+ " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
		    	+ " VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'sieun', SYSDATE, 0, :filesrc)";
	
	    SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
      
        this.npJdbcTemplate.update(sql,  parameterSource);
        
		// 2. 작성자 포인트 1증가
        sql = "UPDATE MEMBER "
		   + " SET point = point + 1 "
	   	   + " WHERE ID=:id";
		
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("id", id );
		this.npJdbcTemplate.update(sql, paramSource);
		
	}
	*/
}
