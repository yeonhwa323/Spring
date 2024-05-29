package org.doit.ik.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class Message2 { // NoticeVO
	
	private String output;
	private List<CommonsMultipartFile> attachList;
	//private CommonsMultipartFile [] attachList;
	
}
