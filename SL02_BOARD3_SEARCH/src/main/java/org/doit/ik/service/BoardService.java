package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardService {
	
	List<BoardVO> getList();
	void register(BoardVO bno);
	BoardVO get(Long bno);
	boolean modify(BoardVO board);
	boolean remove(Long bno);
	
	// 페이징 처리해서 목록 가져오는 메서드
	List<BoardVO> getListWithPaging(Criteria criteria); // 페이징처리 O
	int getTotal( Criteria criteria );
}
