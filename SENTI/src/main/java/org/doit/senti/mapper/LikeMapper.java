package org.doit.senti.mapper;

import java.sql.SQLException;
import java.util.List;

import org.doit.senti.domain.board.ProductLikeDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
	
	// 좋아요 있는지 없는지
	int checkLike(ProductLikeDTO pdLikeDTO) throws ClassNotFoundException, SQLException;
	
	// 좋아요 추가
	public void insertProductLike(ProductLikeDTO pdLikeDTO) throws ClassNotFoundException, SQLException;
	
	// 좋아요 삭제
	public int deleteProductLike(int pdId, String memberId) throws ClassNotFoundException, SQLException; 
	
	// 좋아요 갯수
	public int getLikeCount(int pdId) throws ClassNotFoundException, SQLException;
	
	
	
}
