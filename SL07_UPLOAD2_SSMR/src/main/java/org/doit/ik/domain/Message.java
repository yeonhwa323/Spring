package org.doit.ik.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Message { // NoticeVO
	
	private String output;
	private MultipartFile attach;
	
}
