package org.doit.senti.service.board;

import java.util.List;

import org.doit.senti.domain.board.BoardVO;
import org.springframework.web.bind.annotation.PathVariable;

public interface BoardService {

	List<BoardVO> getList(int medium_ctgr_id);
	
	/*
	void register(BoardVO board);
	BoardVO get(Long bno);
	boolean modify(BoardVO board);
	boolean remove(Long bno);
	*/

	List<BoardVO> mList(int large_ctgr_id);
	
}
