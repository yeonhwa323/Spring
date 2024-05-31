package org.doit.ik.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.MemberDao;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.net.aso.r;

@Controller
@RequestMapping("/joinus/*")
@Log4j
public class JoinController {

	@Autowired
	private MemberDao memberDao;
	
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
