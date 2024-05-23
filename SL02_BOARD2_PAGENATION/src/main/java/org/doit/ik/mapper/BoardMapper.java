package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardMapper {
	
	List<BoardVO> getList(); // 페이징처리 X 함수(모든항목을 가져옴)
	List<BoardVO> getListWithPaging(Criteria criteria); // 페이징처리 O
	int getTotalCount( Criteria criteria );
	
	void insert(BoardVO boardVO); // 새글쓰기
	void insertSelectKey(BoardVO boardVO); // 새글쓰기 + PK(글번호)
	
	BoardVO read(Long bno);
	int update(BoardVO boardVO);
	int delete(Long bno);
	
}
