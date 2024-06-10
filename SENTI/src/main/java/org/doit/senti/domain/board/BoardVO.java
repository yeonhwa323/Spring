package org.doit.senti.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {

	private int pdId;
	private String pdName;
	private int pdPrice;
	private String pdInfo;
	private int pdSales_quantity; // 상품 판매량 default 0
	private int pdDiscount_rate; // 상품 할인율 default 1	
	private int brandId;
	private String brandName;	
	private int pdImageId;
	private String pdImageUrl;
	private String pdInfoImageUrl;
	private int mediumCtgrId;
	private int pdGrade;
	private String mediumCtgrName;
	private int deliveryPay;
	private String pdOptionName;
	private String reviewContent;
	private String pdContent;
	private int smallCtgrId;  
	private String smallCtgrName;
	private int largeCtgrId;
	
	// 좋아요 수
	private int pdLikeCount;

	 
}


