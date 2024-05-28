package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/customer/*")
@Log4j
public class CustomerController {

	@Autowired
	private NoticeDao noticeDao;
	
	// <a class="btn-del button" href="noticeDel.htm?seq=${ notice.seq }">삭제</a> 
	@GetMapping("/noticeDel.htm")
	public String noticeDel(@RequestParam("seq") String seq ) throws Exception {
		int rowCount = this.noticeDao.delete(seq);
		if (rowCount == 1 ) { // 글삭제 성공
			// redirect 접두어 == response.sendRedirect()
			return "redirect:notice.htm";
			// 스프링 [리다이렉트] redirect: 접두사 사용. 
		} else { // 글삭제 실패
			return "redirect:noticeDetail.htm?seq=" + seq + "&error" ;
		}
	
	}
	
	// [2] <button class="btn-save button" type="submit" >수정</button> // PostMapping
		@PostMapping(value = "/noticeEdit.htm")
		public String noticeEdit(NoticeVO notice) throws Exception { // 저장버튼 누를 때
			int rowCount = this.noticeDao.update(notice);
			if (rowCount == 1 ) { // 글수정 성공
				return "redirect:noticeDetail.htm?seq=" + notice.getSeq();  
				// 스프링 [리다이렉트] redirect: 접두사 사용. 
			} else { // 글수정 실패
				return "redirect:notice.htm";
			}			
		}
	
	// <a class="btn-edit button" href="noticeEdit.htm?seq=${ notice.seq }">수정</a>
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq") String seq
			, Model model ) throws Exception {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		return "noticeEdit.jsp";
	}
	

	// <input class="btn-save button" type="submit" value="저장" />
	// [2] NoticeVO notice 커맨드 객체( command object )
	@PostMapping(value = "/noticeReg.htm")
	public String noticeReg(NoticeVO notice) throws Exception { // 저장버튼 누를 때
		int rowCount = this.noticeDao.insert(notice);
		if (rowCount == 1 ) { // 글쓰기 성공
			return "redirect:notice.htm";  
			// 스프링 [리다이렉트] redirect: 접두사 사용. 
		} else { // 글쓰기 실패
			return "noticeReg.jsp?error";
		}			
	}
	
	/* [1] @RequestParam
	@PostMapping(value = "/noticeReg.htm")
	public String noticeReg(@RequestParam("title") String title
			, @RequestParam("content") String content) throws Exception { // 저장버튼 누를 때
		return "noticeReg.jsp"; 				
	}
	*/
	
	// <a class="btn-write button" href="noticeReg.htm">글쓰기</a> 클릭시 화면
	@GetMapping(value = "/noticeReg.htm")
	public String noticeReg(HttpSession session) throws Exception {
		return "noticeReg.jsp"; 				
	}

	//NoticeDetailController.java => 컨트롤러 메서드 구현
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(
			@RequestParam("seq") String seq
			, Model model
			) throws Exception {	

		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);

		return "noticeDetail.jsp";

	}// noticeDetail

	//NoticeController.java => 컨트롤러 메서드로 구현
	// [2]
	@GetMapping(value = "/notice.htm")
	public String notices(
			HttpServletRequest request
			, HttpServletResponse response
			, Model model 
			, @RequestParam(value = "page", defaultValue = "1") int page
			, @RequestParam(value = "field", defaultValue = "title") String field
			, @RequestParam(value = "query", defaultValue = "") String query
			) throws Exception {

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		model.addAttribute("list", list);
		model.addAttribute("message", "hello world!");		

		return "notice.jsp";

	}// notices

	/* [1]
	@GetMapping(value = "/notice.htm")
	public ModelAndView notices(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// ModelAndView 타입  p284 참조
		// 1) Model
		// 2) View
		ModelAndView mav = new ModelAndView();

		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");

		int page = 1;
		String field = "title";
		String query = "";

		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;

		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);

		mav.addObject("list", list);
		mav.addObject("message", "hello world!");
		mav.setViewName("notice.jsp");
		return mav;

	}// notices
	 */
}// class
