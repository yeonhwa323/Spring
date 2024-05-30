package org.doit.ik.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 요청URL : /customer/noticeDetail.htm?seq=2
// 공지사항 보기 컨트롤러
public class NoticeDetailController implements Controller {

	private NoticeDao noticeDao;
	
	public NoticeDetailController() {	}
	
	// setter DI
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public ModelAndView handleRequest(
			HttpServletRequest request
			, HttpServletResponse response ) throws Exception {
			
		String viewName = "noticeDetail.jsp";
		ModelAndView mav = new ModelAndView(viewName);
		
		String seq = request.getParameter("seq");
		
		NoticeVO notice = this.noticeDao.getNotice(seq);
				
		mav.addObject("notice", notice);
		//mav.setViewName("noticeDetail.jsp");
		
		return mav;
	}
	
}
