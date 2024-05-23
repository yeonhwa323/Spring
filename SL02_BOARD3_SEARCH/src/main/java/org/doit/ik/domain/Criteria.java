package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 기준 + 검색 기준
@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum; // 현재페이지 번호
	private int amount; // 현재페이지에 출력할 게시글 수
	
	private String type; 	// 검색조건
	private String keyword; // 검색어
	
	public Criteria() {
		 this(1,10); 
	}
		
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// /board/list?pageNum=28&amount=10&type=T&keyword=홍길동 
	// org.springframework.web.util.UriComponentsBuilder
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
					.queryParam("pageNum", this.pageNum)
					.queryParam("amount", this.amount)
					.queryParam("type", this.type)
					.queryParam("keyword", this.keyword);
		return builder.toUriString();
		
	}
	
	// 함수 추가
	// 검색조건 "TWC" => new String[] {"T" , "W" , "C" }
	public String [] getTypeArr() {
		return type == null ? new String[] {} : this.type.split("");
	}
	
	
}//class
