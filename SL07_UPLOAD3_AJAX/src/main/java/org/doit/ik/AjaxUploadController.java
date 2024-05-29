package org.doit.ik;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/ajax")
public class AjaxUploadController {
	// /ajax/upload
	@GetMapping(value = "/upload")
	public void upload() {
		log.info("> AjaxUploadController.upload()...Get");
		// "/WEB-INF/views/ajax/upload.jsp"
	}
	
}// class
