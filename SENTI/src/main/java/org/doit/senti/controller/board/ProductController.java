package org.doit.senti.controller.board;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.doit.senti.domain.board.ProductImageDTO;
import org.doit.senti.domain.board.ProductRegisterDTO;
import org.doit.senti.persistence.board.ProductRegisterDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/product/*")
public class ProductController {
	
	private ProductRegisterDAO productRegisterDAO;
	
	@GetMapping("/productRegister.do")
	public String productReg(HttpSession session) throws Exception{

		return "product/productRegister.jsp";
	}
	
	private String getFileUuidName(String uploadRealPath, String originalFileName) {
		UUID uuid = UUID.randomUUID();
		
		String fileName = originalFileName.substring(0, originalFileName.length() - 4);
		String ext = originalFileName.substring(originalFileName.length() - 4);
		String fileUuidName = fileName + "-" + ext;
		
		return fileUuidName;
		
	}
	
	@PostMapping("/productRegister.do")
	public String productReg(ProductRegisterDTO pdDTO
						, ProductImageDTO pdImageDTO
						, HttpServletRequest request) throws Exception{

		CommonsMultipartFile multipartFile = pdImageDTO.getFile();
		String uploadRealPath = null;
		System.out.println(multipartFile);
		
		Collection<Part> prs = request.getParts();
		
		System.out.println("1");
		
		for (Part part : prs) {
			
			if(part.getName().equals("pd_image_url")) {
				uploadRealPath = request.getServletContext().getRealPath("/upload");
				System.out.println("> uploadRealPath : " + uploadRealPath);
				
				String originalFileName = multipartFile.getOriginalFilename();
				String fileUuidName = getFileUuidName(uploadRealPath, originalFileName);
				
				File dest = new File(uploadRealPath, fileUuidName);
				multipartFile.transferTo(dest);
				
				pdImageDTO.setPd_image_url(uploadRealPath);
				pdImageDTO.setPd_image_uuid(fileUuidName);
				
			}
			else {
				uploadRealPath = request.getServletContext().getRealPath("/upload");
				System.out.println("> uploadRealPath : " + uploadRealPath);
				
				String originalFileName = multipartFile.getOriginalFilename();
				String fileUuidName = getFileUuidName(uploadRealPath, originalFileName);
				
				File dest = new File(uploadRealPath, fileUuidName);
				multipartFile.transferTo(dest);
				
				pdImageDTO.setPd_info_image_url(uploadRealPath);
				pdImageDTO.setPd_image_info_uuid(fileUuidName);
			}
			
		}
		
		//if(!multipartFile.isEmpty()) {}
		
		int rowCount = this.productRegisterDAO.insertProductImg(pdImageDTO);
		
		if (rowCount == 1) {
			return "main.jsp";
		} else {
			return "redirect:productRegister.jsp?error";
		}

	}

} // class
