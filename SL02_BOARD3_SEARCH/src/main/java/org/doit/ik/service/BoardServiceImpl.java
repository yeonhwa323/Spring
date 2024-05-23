package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor // 스프링 4.3 부터 생성자 DI에 의해서 자동으로 객체가 생성될수 있음
public class BoardServiceImpl implements BoardService {

	//@Autowired
	//@Setter
	private BoardMapper boardMapper;

	@Override
	public List<BoardVO> getList() {
		log.info("> BoardServieImpl.getList()...");
		return this.boardMapper.getList();
	}

	@Override
	public void register(BoardVO boardVO) {
		log.info("> BoardServiceImpl.register()...");
		// this.boardMapper.insert(boardVO);
		this.boardMapper.insertSelectKey(boardVO);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("> BoardServiceImpl.get()...");
		return this.boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO boardVO) {
		log.info("> BoardServiceImpl.modify()...");
		return this.boardMapper.update(boardVO) == 1 ;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("> BoardServiceImpl.remove()...");
		return this.boardMapper.delete(bno) == 1 ;
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria criteria) {
		log.info("> BoardServiceImpl.getListWithPaging()...");
		return this.boardMapper.getListWithPaging(criteria);
		
	}

	@Override
	public int getTotal(Criteria criteria) {
		log.info("> BoardServiceImpl.getTotal()...");
		return this.boardMapper.getTotalCount(criteria);
	}
	

}