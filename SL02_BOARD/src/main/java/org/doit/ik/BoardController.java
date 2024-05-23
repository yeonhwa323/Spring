package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/list")
	public void list(Model model) {
		log.info("> BoardController.list()...");
		model.addAttribute("list",this.boardService.getList());
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
	
	@GetMapping(value = { "/get" , "/modify" })
	public void get(Model model, @RequestParam("bno") Long bno ) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO", this.boardService.get(bno) );	
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr ) {
		log.info("> BoardController.modify() POST...");
		if( this.boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result", "success") ;
		}// if   
		
		//return "redirect:/board/list";
		return String.format("redirect:/board/get?bno=%d", boardVO.getBno());
	}
	
	@GetMapping(value = { "/remove" })
	public String remove(Model model, @RequestParam("bno") Long bno
			, RedirectAttributes rttr ) {
		log.info("> BoardController.remove()...");
		if(this.boardService.remove(bno) ) {
			rttr.addFlashAttribute("result", "success");
		} //if
		return "redirect:/board/list";
	}


}// class