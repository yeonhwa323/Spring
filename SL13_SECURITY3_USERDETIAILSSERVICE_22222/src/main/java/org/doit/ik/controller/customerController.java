package org.doit.ik.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.doit.ik.service.MemberShipService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@org.springframework.stereotype.Controller
@RequestMapping("/customer/*")
@Log4j
@AllArgsConstructor
public class customerController{
	
	private NoticeMapper noticeDao;
	
	private MemberShipService memberShipService;
	
	@GetMapping("noticeDel.htm")
	public String noticedel(@RequestParam("seq") String seq , @RequestParam("filesrc") String filesrc , HttpServletRequest request) throws Exception{
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		//1.첨부파일이 있는 공지사항일 경우 첨부파일도 삭제
		File delFile = new File(uploadRealPath, filesrc);
		if (delFile.exists()) {
			delFile.delete();
		}
		//2. 공지사항 글도 삭제
		int rowcount = 0 ;
		try {
			rowcount = this.noticeDao.delete(seq);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rowcount == 1) {
			return "redirect:notice.htm";
		} else {
			return "redirect:noticeDetail.htm?seq=" + seq + "&error";
		}
		
	}
	//<a class="btn-edit button" href="noticeEdit.htm?seq=${notice.seq }">수정</a>
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit1(@RequestParam("seq") String seq , Model model) throws Exception {
		NoticeVO notice =  this.noticeDao.getNotice(seq);
		model.addAttribute("notice" , notice);
		return "customer.noticeEdit";
		
	}
	
	
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(NoticeVO notice ,@RequestParam("o_filesrc") String oFilesrc , HttpServletRequest request) throws Exception {
		// 1.원래 첨부파일이 있는 경우 새로 첨부파일이 선택되면 
		//기존 첨부파일을 삭제하고 새로 첨부된 파일을 저장
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		
		
		if (!multipartFile.isEmpty()) {
			System.out.println(uploadRealPath);
			//이전 첨부파일이 있다면 이전 첨부파일 삭제.. 
			File o_file = new File(uploadRealPath, oFilesrc);
			if(o_file.exists()) {
				o_file.delete();
			}
			String originalFilename = multipartFile.getOriginalFilename();
			String systemFilename = getFileNameCheck(uploadRealPath,originalFilename);
			File dest = new File(uploadRealPath,systemFilename );
			multipartFile.transferTo(dest); // 실제 파일 저장
			
			notice.setFilesrc(systemFilename);
		} else {
			notice.setFilesrc(oFilesrc);
		}
		// 2. 원래 첨부파일이 있는 경우 새 첨부파일이 첨부되지 않으면 
		// 기존 파일을 사용
		
		int rowcount =  this.noticeDao.update(notice);
		if (rowcount == 1) {
			return "redirect:noticeDetail.htm?seq=" + notice.getSeq();
		} else {
			return "redirect:notice.htm";
		}
		
		
	}
	// NoticeController => 컨트롤러 메서드 구현
	// 글쓰기 저장버튼 누를때 
	//2) 커맨드 객체로 받는 방법
	
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
	      int index = 1;      
	      while( true ) {         
	         File f = new File(uploadRealPath, originalFilename);         
	         if( !f.exists() ) return originalFilename;         
	         // upload 폴더에 originalFilename 파일이 존재한다는 의미         a-2  .txt (4자리)
	         String fileName = originalFilename.substring(0, originalFilename.length() - 4 );  //   a
	         String ext =  originalFilename.substring(originalFilename.length() - 4 );  // .txt
	         // asdfasf-3.txt
	         originalFilename = fileName+"-"+(index)+ext;

	         index++;
	      } // while 
	   }
	
	@PostMapping("/noticeReg.htm")
	public String noticereg(NoticeVO notice , Model model, HttpServletRequest request) throws Exception {
		
		CommonsMultipartFile File = notice.getFile();
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		
		
		if (!File.isEmpty()) {
			System.out.println(uploadRealPath);
			String originalFilename = File.getOriginalFilename();
			String systemFilename = getFileNameCheck(uploadRealPath,originalFilename);
			File dest = new File(uploadRealPath,systemFilename );
			File.transferTo(dest);
			
			notice.setFilesrc(systemFilename);
		}
		notice.setWriter("malgeum");
		int rowcount = this.noticeDao.insert(notice);
		//this.memberShipService.insertAndPointUpOfMember(notice,notice.getWriter());
		
		if (rowcount==1) {
			return "redirect:notice.htm"; 
		} else {
			return "redirect:notice.htm"; 
		}
		
		
		/*int rowcount =1 ;
		if (rowcount == 1 ) { // 글쓰기 성공
			return "redirect:notice.htm"; // 스프링 리다이렉트 방법 / 포워드 
		} else { //글쓰기 실패
			return "noticeReg.jsp?error";
			
		}*/
		
	}
	/*1)
	 * @PostMapping("/noticereg.htm") public String noticereg(Model model
	 * , @RequestParam("title") String title ,
	 * 
	 * @RequestParam("content") String contentString ) throws Exception { return
	 * "noticeReg.jsp"; }
	 */
	@GetMapping("/noticeReg.htm")
	public String noticereg(HttpSession session ,Model model) throws Exception {
		return "customer.noticeReg";
	}
	@GetMapping("/notice.htm")
	public String notices(HttpServletRequest request, HttpServletResponse response , Model model 
		, @RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "field", defaultValue = "title") String field,
		@RequestParam(value = "query", defaultValue = "") String query) throws Exception {
		// ModeAndView 타입  p284  참조
		// 1) Model
		// 2) View
		
		List<NoticeVO> list = this.noticeDao.getNotices(page,field,query);
		model.addAttribute("list",list);
		model.addAttribute("message","Hello World");
		
	
		return "customer.notice" ;
	}

	/*
	 * @GetMapping("/notice.htm") public ModelAndView
	 * handleRequest(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception { // ModeAndView 타입 p284 참조 // 1) Model // 2) View
	 * 
	 * ModelAndView mav = new ModelAndView();
	 * 
	 * String ppage = request.getParameter("page"); String pfield =
	 * request.getParameter("field"); String pquery =
	 * request.getParameter("pquery");
	 * 
	 * int page = 1 ; String field = "title"; String query = ""; if ( ppage != null
	 * && !ppage.equals("")) { page = Integer.parseInt(ppage) ; } if ( pfield !=
	 * null && !pfield.equals("")) { field = pfield ; } if ( pquery != null &&
	 * !pquery.equals("")) { query = pquery ; }
	 * 
	 * List<NoticeVO> list = this.noticeDao.getNotices(page,field,query);
	 * 
	 * mav.addObject("list", list); mav.addObject("message", "Hello World") ;
	 * mav.setViewName("notice.jsp"); return mav; }
	 */
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(@RequestParam("seq") String seq,Model model) throws Exception {
		
		this.noticeDao.hitup(seq);
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice",notice);
		this.noticeDao.gethit(seq);
		
		return "customer.noticeDetail";
	}
	
	
}
