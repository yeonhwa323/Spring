package org.doit.ik.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class pageDTO {
	
	// 페이징 처리
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;

	private int total; // 총 레코드수
	private Criteria criteria; // 한페이지 출력갯수

	// 생성자2
	public pageDTO(Criteria criteria, int total) {
		this.criteria = criteria;
		this.total = total;

		this.endPage = (int)(Math.ceil(criteria.getPageNum()/
				(double)criteria.getAmount())) * criteria.getAmount();
		this.startPage = this.endPage - criteria.getAmount() + 1;

		int realEndPage = (int)(Math.ceil((double)total/criteria.getAmount()));
		if(realEndPage < this.endPage) this.endPage = realEndPage;

		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
}
