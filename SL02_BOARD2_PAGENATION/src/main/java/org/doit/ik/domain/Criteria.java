package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 기준, 검색 기준
@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum; // 현재페이지 번호
	private int amount; // 현재페이지에 출력할 게시글 수
	
	
	 public Criteria() {
		 this(1,10); 
	}
	 
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// ?pageNum=28&amount=10&type=T[itle]&keyword=홍길동 
	//[쿼리스트링 만들어주는 클래스] UriComponentsBuilder
	// org.springframework.web.util.UriComponentsBuilder
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
					.queryParam("pageNum", this.pageNum)
					.queryParam("amount", this.amount);
		return builder.toUriString();
		
	}
	
	
	
	
}//class
