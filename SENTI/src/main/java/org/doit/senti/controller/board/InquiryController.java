package org.doit.senti.controller.board;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.doit.senti.domain.board.InquiryVO;
import org.doit.senti.mapper.board.InquiryMapper;
import org.doit.senti.service.board.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/inquiry/*")
@Log4j
public class InquiryController {

	@Autowired
	private InquiryMapper InquiryDao;
	
	@Autowired
	private MemberShipService memberShipService;
	
	// <a class="btn-del button" href="noticeDel.htm?seq=${notice.seq}&filesrc=${ notice.filesrc }">삭제</a>
	@GetMapping("/inquiryDel.do")
	public String noticeDel(
			@RequestParam("inquiryId") int inquiryId
		  , @RequestParam("filesrc") String filesrc
		  , HttpServletRequest request
		  ) throws Exception{
		// 1. 첨부파일이 있는 공지사항일 경우 첨부파일도 삭제
		String uploadRealPath = request.getServletContext().getRealPath("/inquiry/upload");
		File delFile = new File(uploadRealPath, filesrc);
		if (delFile.exists()) {
			delFile.delete();
		}
		// 2. 공지사항 글도 삭제
		int rowCount = this.InquiryDao.delete(inquiryId);
		if (rowCount ==1) {  // 글삭제 성공
			// redirect 접두어 == response.sendRedirect()
			return "redirect:inquiry.do";
		}else {  // 글삭제 실패
			return "redirect:inquiryDetail.do?seq="+ inquiryId + "&error";
		}
	}
	
	// <button class="btn-save button" type="submit">수정</button>
	@PostMapping("inquiryEdit.do")
	public String noticeEdit(
			InquiryVO inquiry
		   ,@RequestParam("o_filesrc") String oFilesrc
		   ,HttpServletRequest request
		   ) throws Exception{
		// 1. 원래 첨부파일이 있는 경우 새로 첨부파일이 선택되면 원래 있던 이전 첨부파일은 삭제.. 코딩추가
		CommonsMultipartFile multipartFile = inquiry.getFile();
		String uploadRealPath = null;
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/inquiry/upload");
			System.out.println("> uploadRealPath : "+ uploadRealPath);
			
			// 1-1. 이전 첨부파일이 있다면 이전 첨부파일 삭제
			File o_file = new File(uploadRealPath,oFilesrc);
			if (o_file.exists()) o_file.delete();
			
			// 1-2. 새로 첨부파일 저장
			String originalFilename = multipartFile.getOriginalFilename();
			
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			
			File dest = new File(uploadRealPath, filesystemName);
			multipartFile.transferTo(dest);
			
			inquiry.setFilesrc(filesystemName);
		}else {
			inquiry.setFilesrc(oFilesrc);
		}
		// 2. 
		int rowCount = this.InquiryDao.update(inquiry);
		if (rowCount ==1) {  // 글수정 성공
			return "redirect:inquiryDetail.do?seq=" + inquiry.getInquiryId();  
		}else {  // 글수정 실패
			return "redirect:inquiry.jsp";
		}
	}
	
	// <a class="btn-edit button" href="noticeEdit.do?seq=${ notice.seq }">수정</a>
	@GetMapping("inquiryEdit.do")
	public String inquiryEdit(@RequestParam("inquiryId") int inquiryId
			, Model model) throws Exception{
		InquiryVO inquiry = this.InquiryDao.getInquiry(inquiryId);
		model.addAttribute("inquiry", inquiry);
		return "inquiry.inquiryEdit.jsp";
	}
	
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
	
	// 2. InquiryVO inquiry 커맨드 객체(command object)
	@PostMapping(value = "/inquiryReg.do")
	public String inquiryReg(InquiryVO inquiry, HttpServletRequest request)throws Exception {
		CommonsMultipartFile multipartFile = inquiry.getFile();
		String uploadRealPath = null;
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/inquiry/upload");
			System.out.println("> uploadRealPath : "+ uploadRealPath);
			
			String originalFilename = multipartFile.getOriginalFilename();
			
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath, filesystemName);
			multipartFile.transferTo(dest);
			
			inquiry.setFilesrc(filesystemName);
		}
		/* inquiry.setMemberId("5"); */
		int rowCount = this.InquiryDao.insert(inquiry);
		if (rowCount ==1) {  // 글쓰기 성공
			return "redirect:inquiry.jsp";  // 스프링 [리다이렉트] redirect: 접두사 사용 / 포워딩
		}else {  // 글쓰기 실패
			return "inquiryReg.jsp?error";
		}
	
		
	}
	
	// <a class="btn-write button" href="noticeReg.htm">글쓰기</a>
	@GetMapping(value = "/inquiryReg.do")
	public String inquiryReg(HttpSession session)throws Exception {
		return "inquiry.inquiryReg.jsp";
	}
	
	// 2.
	@GetMapping("/inquiry.do")
	public String Inquirys(
			Model model
		  , @RequestParam(value = "page", defaultValue = "1") int page
		  , @RequestParam(value = "field", defaultValue = "title") String filed
		  , @RequestParam(value = "query", defaultValue = "") String query
		  ) throws Exception {
		
		List<InquiryVO> list = (List<InquiryVO>) this.InquiryDao.getInquirys(page, filed, query);
		
		model.addAttribute("list", list);
		model.addAttribute("message", "hello world!");
	
		return "inquiry.inquiry.jsp";
	}
	

}
