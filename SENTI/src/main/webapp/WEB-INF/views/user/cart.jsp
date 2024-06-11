<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>감도 깊은 취향 셀렉트샾 29CM</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/css/cart.css">
</head>

<header>
	<jsp:include page="/WEB-INF/views/layout/top.jsp" flush="false"></jsp:include>
</header>

<body>
	<div class="middle">
		<div class="mid-shopping-state">
			<ol class="state-text">
				<li class="shopping-bag">01 SHOPPING BAG
					<svg class="efn0hf62 css-1eloq9d e2007db0" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 42 80">
						<g fill="none" fill-rule="evenodd" stroke="rgb(212, 212, 212)" stroke-width="5">
							<path d="M1 0l40 40.083L1.166 80"></path>
						</g>
					</svg>
				</li>
				<li class="order">02 ORDER
					<svg class="efn0hf62 css-1eloq9d e2007db0" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 42 80">
						<g fill="none" fill-rule="evenodd" stroke="rgb(212, 212, 212)" stroke-width="5">
							<path d="M1 0l40 40.083L1.166 80"></path>
						</g>
					</svg>
				</li>
				<li class="order-confirmed">03 ORDER CONFIRMED</li>
			</ol>
		</div>
		<c:choose>
			<c:when test="${empty list }">
				<div class="cart-info">
					<span class="cart-text">장바구니에 담은 상품이 없습니다.</span>
					<div class="shop-btn">
						<a class="go-29cm" href="/main.do">CONTINUE SHOPPING</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<section class="cart-box">
				<form id="deleteCartForm" method="post" action="/user/cartDel.do">
					<!-- 
					<div class="checkbox1">
						<span class="checkbox-span" id="select-btn">
							<input type="checkbox" class="checkbox-btn" />
							<label class="check-info">
							선택(0/1)
							</label>
						</span>
						<button class="delete" type="button">선택삭제</button>
					</div>
					 -->
					<div class="cart-box-info">
						<div class="box-info-top">
							<div class="top-info1">
								<span class="checkbox-span">
									<input type="checkbox" class="checkbox-btn-all" name="checkbox-btn-all" checked="checked"/>
								</span>
							</div>
							<div class="top-info2">상품 정보</div>
							<div class="top-info3">수량</div>
							<div class="top-info3">주문금액</div>
							<div class="top-info4">배송비</div>
						</div>
						<div class="box-info-bottom">
							<h3 class="item-name" translate="no"></h3>
							
							<c:forEach items="${list }" var="list">
							<div class="box-bottom-main" id="checkbox-bottom-main">
								<div class="bot-checkbox">
									<span class="checkbox-span">
										<!-- <input type="checkbox" class="checkbox-btn" name="checkbox-btn" id="checkbox-btn" onclick="checkSelectAll()" checked="checked"/> -->
										<input type="checkbox" class="checkbox-btn" name="cartIdList" id="checkbox-btn" checked="checked" data-cartId="${list.cartId }" value="${list.cartId }"/>
										<input type="hidden" class="invisible-pdPrice" value="${list.pdPrice }" />
										<input type="hidden" class="invisible-pdPrice" value="${list.deliPay }" />
									</span>
								</div>
								<div class="bot-info1">
									<div class="bot-item-info">
										<a href="/product/viewDetail.do?pd_id=${list.pdId }">
											<img class="item-img" src="${list.pdImageURL }" alt="${list.pdName }" loading="lazy"/>
										</a>
										<div class="item-img-info">
											<div translate="no">
												<a class="item-brand" href="/product/viewDetail.do?pd_id=${list.pdId }">${list.brandName }
													<input type="hidden" value="${list.pdId }" />
												</a>
											</div>
											<a id="item-title" class="item-title" href="/product/viewDetail.do?pd_id=${list.pdId }">${list.pdName }</a>
											<div class="item-price">
												<span class="price">
												 	<fmt:formatNumber pattern="##,###" value="${list.pdPrice }" />원
												</span>
											</div>
											<div class="item-bot-info">옵션 : ${list.selectOption }</div>
										</div>
									</div>
									<%-- <button id="item-delete" class="item-delete" data-cartId="${list.cartId }"></button> --%>
									<a id="item-delete" class="item-delete" href="/user/cartDel.do?cart_id=${list.cartId }"></a>
								</div>
								
								<div class="bot-info2">
									<div class="item-cnt">
										<div class="item-cnt-box">
											<button id="minus-btn" class="minus" type="button">-</button>
											<input type="text" class="cnt" autocapitalize="none" id="num-of-item" inputmode="numeric" value="${list.stock }"/>
											<button id="plus-btn" class="plus" type="button" >+</button>
										</div>
									</div>
								</div>
								<div class="bot-info3">
									<div>
										<span class="sell-price" id="sell-price">
											<fmt:formatNumber pattern="##,###" value="${list.pdPrice }" />원
										</span>
									</div>
									<div class="buy-now">
										<button class="buy-now-btn" type="button">BUY NOW</button>
									</div>
								</div>
								<div class="bot-info4">
									<div class="bot-deli-pay">
										<fmt:formatNumber pattern="##,###" value="${list.deliPay }" /> 
									</div>
								</div>
							</div>
							</c:forEach>
							
						</div>
						
					</div>
					<div class="bot-bot">
						<div class="bot-bot-btn">
							<button type="submit" class="click-btn" id="delete-btn" name="click-btn">선택상품 삭제</button>
						</div>
						<p class="max-cart-text">장바구니는 최대 100개의 상품을 담을 수 있습니다.</p>
					</div>
					</form>
				</section>
				<section class="total-pay" >
					<div class="total-pay-box">
						<div class="total-pay-top">
							<div class="total-pay-text1">총 주문금액</div>
							<div class="total-pay-text2">총 배송비</div>
							<div class="total-pay-text3">총 결제금액</div>
						</div>
						<div class="total-pay-bottom">
							<div class="total-pay-bottom-box1">
								<span class="total-pay-price">
									<strong class="total-charge">
										<span class="total-pay-price-text">
											
										</span>
									</strong>
								</span>
								<span class="total-pay-item-cnt">
									<span class="total-cnt-text">
										
									</span>
								</span>
							</div>
							<div class="total-pay-bottom-box2">
								<i class="css-15wexqq e17g5zv810"></i>
								<span class="total-pay-deli-charge">
									<strong class="total-charge2">
										<span class="total-deli-price-text">
											
										</span>
									</strong>
								</span>
							</div>
							<div class="total-pay-bottom-box3">
								<i class="css-z92i1e e17g5zv811"></i>
								<span class="total-pay-price2">
									<strong class="total-pay-price-text2">
										<span class="final-total-price-text">
											
										</span>
									</strong>
								</span>
							</div>
						</div>
					</div>
				</section>
			<div class="main-bottom">
					<a class="continue" href="/main.do">CONTINUE SHOPPING</a>
					<button id="check-out-btn" class="check-out" type="button">CHECK OUT</button>
					<script>
						$(".check-out").on("click", function(){
							let confirmOrder = confirm("주문페이지로 이동하겠습니까 ?")
							location.href = "/user/order.do";
							
							if (!confirmOrder) {
								event.preventDefault;
							}
							
						})
					</script>
			</div>
			</c:otherwise>
		</c:choose>
	</div>
	
	<%-- 
	<form action="/cartDel.do" method="post" class="delete_form">
			<input type="hidden" name="cartId" class="delete_cartId">
			<input type="hidden" name="memberId" value="${member.memberId}">
	</form>
	 --%>
	
<footer>
	<jsp:include page="/WEB-INF/views/layout/bottom.jsp" flush="false"></jsp:include>
</footer>
</body>
<script>
	$(".buy-now-btn").on("click", function(){
		let confirmOrderOne = confirm("상품을 구매하시겠습니까 ?")
		location.href = "/user/order.do";
		
		if (!confirmOrderOne) {
			event.preventDefault;
		}
		
	})
</script>	    
<script>

   	$(document).ready(function(){
   		// 수량에서 + 버튼 눌렀을 때
   		$(".plus").on("click", function() {
   			let changeStock = $(this).siblings(".cnt");
   			let currentVal = parseInt(changeStock.val());
   			changeStock.val(currentVal + 1);
   			updatePrice(changeStock);
   		});

   		// - 버튼 눌렀을 때
   		$(".minus").on("click", function() {
   			let changeStock = $(this).siblings(".cnt");
   			let currentVal = parseInt(changeStock.val());
   			if (currentVal > 1) { // 최소 값이 1이 되도록 설정
   				changeStock.val(currentVal - 1);
   				updatePrice(changeStock);
   			}
   		});

   		$(".cnt").on("input", function() {
   			let currentVal = $(this).val();
   			if (isNaN(currentVal) || currentVal <= 0) { // 숫자가 아니거나 0 이하일 경우 1로 고정
   				$(this).val(1);
   			}
   			updatePrice($(this));
   		});
   	})
   	
   	
   	/* 상품 수량 증가할때 가격도 증가되도록 ajax 처리 해야함...
   	function updatePrice(changeStock){
   		let cartId = changeStock.closest(".box-bottom-main").find(".checkbox-btn").data("cartId");
   		let currentStock = changeStock.val();
   		
   		$.ajax({
   			url: "/user/updateCart.do",
   			type: "POST",
   			data: JSON.stringify({ cartId: cartId, currentStock: currentStock }),
            contentType: 'application/json; charset=UTF-8',
   			success: function(response) {
   				let changePrice = changeStock.closest(".box-bottom-main").find(".sell-price");
   				changePrice.text(response.updatedPrice + "원");

   				updateTotalPrice();
   			}
   		});
   	}
   	 */
   	
</script>
	   	
<script>
	$(document).ready(function(){
		setTotalInfo();
	});

	$(".checkbox-btn").on("change", function(){
		setTotalInfo($(".checkbox-span"));
	})

	$(".checkbox-btn-all").on("click", function(){
		
		var chk = $(".checkbox-btn-all").prop("checked");
		if(chk) {
			$(".checkbox-btn").prop("checked", true);
		} else {
			$(".checkbox-btn").prop("checked", false);
		}
		
		setTotalInfo($(".checkbox-span"));	
		
	});
	
	$(".checkbox-btn").click(function(){
		$(".checkbox-btn-all").prop("checked", false);
	});
	
	$(".checkbox-btn").on("click", function() {
        var allChecked = $(".checkbox-btn").length === $(".checkbox-btn:checked").length;
        $(".checkbox-btn-all").prop("checked", allChecked);
    });

/* 총 금액, 총 갯수, 총 결제금액을 여기저기서 사용하기위해 전역변수로 뺌 */
let totalCnt = 0;
let totalPrice = 0;
let finalTotalPrice = 0;
let totalDeliPrice = 0;

function setTotalInfo(){
	totalPrice = 0;
	totalCnt = 0;
	totalDeliPrice = 0;
	finalTotalPrice = 0;
	
	$(".box-bottom-main").each(function(index, element){
		if ($(element).find(".checkbox-btn").is(":checked") === true) {
			totalPrice += parseInt($(element).find(".invisible-pdPrice").val());
			totalCnt += parseInt($(element).find("#num-of-item").val());
		}
	})
	
	/* 배송비를 다 합치는 건 아니라고 생각해서 조건을 넣어 각 총 주문금액에 따라 배송비 부여 */
	if (totalPrice >= 100000) {
		totalDeliPrice = 0;
	} else if (totalPrice == 0) {
		totalDeliPrice = 0;
	} else {
		totalDeliPrice = 3000;
	}
	
	finalTotalPrice = totalPrice + totalDeliPrice;
	
	$(".total-pay-price-text").text(totalPrice.toLocaleString() + "원");
	$(".total-cnt-text").text("총 " + totalCnt + "개")
	$(".total-deli-price-text").text(totalDeliPrice.toLocaleString() + "원");
	$(".final-total-price-text").text(finalTotalPrice.toLocaleString() + "원");
}
</script>
<script>
	/* 개별삭제 */
	$(".item-delete").on("click", function(){
    	let confirm_del = confirm("담기 취소 하시겠습니까 ?");
		
    	if (!confirm_del) {
            event.preventDefault();
        }
	});
	
	/* 체크된 상품 삭제 */
   	$(".click-btn").on("click", function(){
   		
    	if (totalCnt == 0) {
			alert("항목을 선택하세요.")
			
			event.preventDefault();
		} else {
	    	var confirm_del2 = confirm("선택한 " + totalCnt + "개 상품을 삭제하시겠습니까?");
	
	    	if(confirm_del2) {
	    	    var checkArr = new Array();
	    	    
	   	    $(".checkbox-btn:checked").each(function() {
	               checkArr.push($(this).attr("data-cartId"));
	        });
		      
			} else {
				event.preventDefault();
			}
		}
    	

   });
</script>
</html>