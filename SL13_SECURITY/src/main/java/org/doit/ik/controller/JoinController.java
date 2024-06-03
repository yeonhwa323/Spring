package org.doit.ik.controller;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/joinus/*")
@Log4j
public class JoinController {

	@Autowired
	private MemberMapper memberDao;
	
	// /joinus/login.htm -> /joinus/login.jsp 응답
	@GetMapping("/login.htm")
	public String login() throws Exception {
		return "joinus.login";
	}
	// /joinus/join.htm -> /joinus/join.jsp 응답
	@GetMapping("/join.htm")
	public String join() throws Exception {
		return "joinus.join";
	}
	
	@PostMapping("/join.htm")
	public String join(MemberVO member) throws Exception {
		this.memberDao.insert(member);
		return "redirect:../index.htm";
	}
	
}
