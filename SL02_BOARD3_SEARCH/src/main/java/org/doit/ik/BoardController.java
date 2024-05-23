package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.pageDTO;
import org.doit.ik.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor
public class BoardController {

	// @Autowired
	private BoardService boardService;

	// 페이징 처리 X 컨트롤러메서드
	/*
	@GetMapping("/list")
	public void list(Model model) {
		log.info("> BoardController.list()...");
		model.addAttribute("list",this.boardService.getList());
	}
	*/
	// *** 페이징 처리 O 컨트롤러메서드 ***
	// http://localhost/board/list		   1        5 [기본]
	// http://localhost/board/list?pageNum=3&amount=10&type=TW&keyword=
	// http://localhost/board/list?pageNum=3&amount=10&type=TW&keyword=검색어
	@GetMapping("/list")
	public void list(Model model, Criteria criteria) {
		log.info("> BoardController.list()...");
		model.addAttribute("list", this.boardService.getListWithPaging(criteria));
		// 1 2 [3] 4 5 6 7 8 9 10 >
		int total = this.boardService.getTotal(criteria);
		model.addAttribute("pageMaker", new pageDTO(criteria, total));		
	}
	
	// <a href ='board/register'>
	@GetMapping("/register")
	public void register(Model model) {
		log.info("> BoardController.register() GET...");
	}

	// <form action ='board/register' method = 'post'>
	@PostMapping("/register")
	public String register(BoardVO boardVO, RedirectAttributes rttr ) {
		log.info("> BoardController.register() POST...");
		this.boardService.register(boardVO);      
		rttr.addFlashAttribute("result", boardVO.getBno()) ;
		return "redirect:/board/list";
	}

	// <a href="/board/get?bno=2">
	// <a href="/board/get/2">
	/*
	@GetMapping(value = { "/get/{bno}" , "/modify/{bno}" })
	//public void get(Model model, @RequestParam("bno") Long bno ) {
	public String get(Model model, @PathVariable("bno") Long bno ) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO", this.boardService.get(bno) );		
		return "/board/get"; // WEB-INF/views + /board/get + .jsp
	}
	*/
	/*
	@GetMapping(value = { "/get" , "/modify" })
	public void get(Model model, @RequestParam("bno") Long bno, Criteria criteria ) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO", this.boardService.get(bno) );	
		model.addAttribute("criteria", criteria);
	}
	*/
	@GetMapping(value = { "/get" , "/modify" })
	public void get(Model model, @RequestParam("bno") Long bno
			, @ModelAttribute("criteria") Criteria criteria ) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO", this.boardService.get(bno) );			
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr 
			, @ModelAttribute("criteria") Criteria criteria) {
		log.info("> BoardController.modify() POST...");
		if( this.boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result", "success") ;
		}// if   
		
		rttr.addFlashAttribute("pageNum", criteria.getPageNum() ) ;
		rttr.addFlashAttribute("amount", criteria.getAmount() ) ;
		
		//return "redirect:/board/list";
		//return "redirect:/board/list?pageNum="+criteria.getPageNum()";
		return "redirect:/board/list" + criteria.getListLink() ;
		//return String.format("redirect:/board/get?bno=%d", boardVO.getBno());
	}
	
	@GetMapping(value = { "/remove" })
	public String remove(Model model, @RequestParam("bno") Long bno
			, RedirectAttributes rttr
			, Criteria criteria) {
		log.info("> BoardController.remove()...");
		if(this.boardService.remove(bno) ) {
			rttr.addFlashAttribute("result", "success");
		} //if
		return "redirect:/board/list" + criteria.getListLink() ;
	}

	
	

}// class

//RedirectAttributes : 일회성으로 가져갈 파라미터값