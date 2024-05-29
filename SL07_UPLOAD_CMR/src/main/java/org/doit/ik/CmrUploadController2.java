package org.doit.ik;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.doit.ik.domain.Message;
import org.doit.ik.domain.Message2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/cmr/*")
public class CmrUploadController2 {
	// context path
	// webapp/cmr/upload2
	@GetMapping( value = "/upload2")
	public void upload() {
		log.info("> CmrUploadController2.upload()...Get");
		// "/WEB-INF/views/" + /cmr/upload2 + ".jsp" 

	}
	/* input태그로 값이 넘어옴
	<div><input type="text" name="output" value="hello world!" /></div>
  	<div><input type="file" name="attach"/></div>
	 */
	// 2. 커맨드 객체를 사용해서 여러 개의 파일 업로드 처리..
	@PostMapping( value = "/upload2")
	public void upload(
			Message2 message2
			) {
		log.info("> CmrUploadController2.upload()...Post");

		log.info("-".repeat(30));
		log.info("1. output : " + message2.getOutput() );

		// C://upload 폴더 생성.
		String uploadFolder = "C://upload";
		List<CommonsMultipartFile> attachList = message2.getAttachList();
		
		for (MultipartFile attach :attachList ) {
			if ( ! attach.isEmpty() ) {
				log.info("-".repeat(30));
				log.info("2. originalFileName : " + attach.getOriginalFilename() );
				log.info("2. size : " + attach.getSize() );
				// 2. 첨부파일 저장
				File saveFile = new File(uploadFolder, attach.getOriginalFilename() );
				try {
					attach.transferTo(saveFile);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

			}// if

		} // for

		log.info(" = end = "); // 

	}



	//<div><input type="text" name="output" value="hello world!" /></div>
	//<div><input type="file" name="attach" multiple="multiple" /></div>
	// 1. 
	/*
	@PostMapping( value = "/upload2")
	public void upload(
			@RequestParam("output") String output
			, @RequestParam("attach") List<MultipartFile> attachList
			) {
		log.info("> CmrUploadController2.upload2()...Post");

		log.info("-".repeat(30));
		log.info("1. output : " + output );

		// C://upload 폴더 생성.
		String uploadFolder = "C://upload";

		for (MultipartFile attach : attachList) {

			if ( ! attach.isEmpty() ) {
				log.info("-".repeat(30));
				log.info("2. originalFileName : " + attach.getOriginalFilename() );
				log.info("2. size : " + attach.getSize() );
				// 2. 첨부파일 저장
				File saveFile = new File(uploadFolder, attach.getOriginalFilename() );
				try {
					attach.transferTo(saveFile);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

			}// if

		} // for

		log.info(" = end = "); // 

	}
	 */

}// class
