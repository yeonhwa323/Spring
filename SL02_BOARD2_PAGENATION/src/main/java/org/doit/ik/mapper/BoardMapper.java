package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;

public interface BoardMapper {
	
	List<BoardVO> getList();
	
	void insert(BoardVO boardVO); // 새글쓰기
	void insertSelectKey(BoardVO boardVO); // 새글쓰기 + PK(글번호)
	
	BoardVO read(Long bno);
	int update(BoardVO boardVO);
	int delete(Long bno);
	
}
