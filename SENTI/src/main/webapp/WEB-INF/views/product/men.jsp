<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>감도 깊은 취향 셀렉트샾 29CM</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- <script src="/jspPro/resources/cdn-main/example.js"></script> -->
<link rel="stylesheet" href="../../resources/css/men_cloth.css">
<style type="text/css">

</style>

</head>

<body>
<header>
	 <jsp:include page="/WEB-INF/views/layout/top.jsp" flush="false"></jsp:include>
</header>
	<input id="pdId" type="hidden" value="${BoardVO.pdID }" name="pdId">
	<input id="memberId" type="hidden" value="${BoardVO.memberId }" name="memberId">
	<input id="likeCheck" type="hidden" value="${likeCheck }" name="likeCheck">
		
	<div id="wrap">
		<div id="best-left">
			<button></button>
			<div>
				<!-- <button></button>  화면이 작아졌을 때 #best-left메뉴 나타나게 하는거 -->
				<h2 class="best_title_left">남성의류</h2>
				<!-- <ul class="left_bar_meue" > -->
				<ul class="left-menu">
				<c:forEach items="${mList}" var="dto">
				<li value="${dto.mediumCtgrId }" class="medium_ctgr_id111"> 
   						 <a href="#" class="medium-ctgr" <c:if test="${mediumCtgrId == dto.mediumCtgrId}">selected</c:if>>${dto.mediumCtgrName}</a>
				</li> 
				</c:forEach>
				</ul>
				
				
			</div>
		</div>

		<div id="best-right">			
			<div class="widget">
				<div class="best_radio_box">
					<ul class="aa">	
					<!--small ctgr 표시 되는 곳.  -->
										
					</ul>
				</div>
				<div class="controlgroup"><!-- 정렬 기능을 추가한 sql문이 필요 , 따로 json파일을 만들어야 할지 고민중...  -->
					<select id="sort-type" class="styled-select">
						<option>추천순</option>
						<option>신상품순</option>
						<option>리뷰많은순</option>
						<option>낮은가격순</option>
						<option>높은가격순</option>
						<option>높은할인순</option>
						<option>좋아요많은순</option>
						<option>판매순</option>
					</select>
				</div>
				
			</div>
			
			<ul class="photo_list">	
			<c:forEach var="list" items="${list}">
				<li class="photo1">
       					<div class="cc">
       						<a href="/sentiBoard/list/view.do?pd_id=${list.pdId}">
       							<div class="dd">
       								<img alt=""
       									src="${list.pdInfoImageUrl}"
       									class="ff">
       							</div>
       						</a> 
       						<div class="gg">
       							<a class="hh" href="#">${list.brandName}</a> <a
       								title="${element.pdName}">
       								<div class="j">
       									<h5 class="jj">${list.pdName} (10 Color)</h5>
       									<strong class="jjj"></strong>
       									<div class="01">
       										<span class="kkk"></span> <strong class="qqq">
       											<fmt:formatNumber pattern="###,###" value="${list.pdPrice}" />원
       										</strong>
       									</div>
       									<ul class="eee">
       										<li class="yyy"></li>
       										<li></li>
       									</ul>
       								</div>
       							</a>
       							<div class="ppp">
       								<button class="heart" data-pdid="${list.pdId }">
       									
       										
       										<c:if test="${likeCheck eq 'F' }">
		       									<svg xmlns="http://www.w3.org/2000/svg" width="21" height="18"
		       										viewBox="0 0 20 20" class="bi-suit-heart">
		       										<path
		       											d="M2.24 3.425a4.758 4.758 0 0 1 6.79 0c.416.421.74.901.971 1.413.23-.512.553-.992.97-1.413a4.758 4.758 0 0 1 6.79 0 4.91 4.91 0 0 1 0 6.88L10 18.166l-7.76-7.863-.166-.176a4.911 4.911 0 0 1 .166-6.703z"
		       											fill="none" fill-rule="evenodd" stroke="#5d5d5d" stroke-width="1.5" />
		       									</svg>
	       									</c:if>
	       									<c:if test="${likeCheck eq 'T' }">
		       									<svg xmlns="http://www.w3.org/2000/svg" width="21" height="18"
		       										viewBox="0 0 20 20" class="bi-suit-heart-fill">
		       										<path d="M2.24 3.425a4.758 4.758 0 0 1 6.79 0c.416.421.74.901.971 1.413.23-.512.553-.992.97-1.413a4.758 4.758 0 0 1 6.79 0 4.91 4.91 0 0 1 0 6.88L10 18.166l-7.76-7.863-.166-.176a4.911 4.911 0 0 1 .166-6.703z" 
		       										fill="red" fill-rule="evenodd" stroke="#5d5d5d" stroke-width="1.5"></path>
		       									</svg>
	       									</c:if>
	       									
	       									
	       									<!-- 로그인 안되어있을 시 좋아요 누르면 로그인 페이지로 이동 코딩 추가  -->
	       									
       									
       									<h5 class="jj">좋아요 수 쿼리</h5>
       								</button>
       								<a href="#" class="review"> <svg
       										xmlns="http://www.w3.org/2000/svg" width="15" height="15"
       										viewBox="0 0 13 12" class="css-ik4rmz e1f8g7yn1">
       								<path
       											d="M4.146 3.95L0 4.583l3 3.075L2.292 12 6 9.95 9.708 12 9 7.658l3-3.075-4.146-.633L6 0z"
       											fill="none" fill-rule="evenodd" stroke="#5d5d5d"
       											stroke-width="1.5"></path>
       								</svg>
       									<div class="review-point"></div>
       									<div class="review-count">평점 준 사람들 카운팅 하는 쿼리문 필요함</div>
       								</a>
       							</div>
       						</div>
       						</div>
       					</li>    
       				</c:forEach>							
			</ul> 
		</div>
	</div>
	 <script>
	 $(".medium_ctgr_id111").on("click", function(event){
		 /* medium 카테고리를 클릭했을 때 small 카테고리를 표시하는 코드. */
		 
		 // a 링크태그의 기본 페이지 이동 기능 막기.  
		 event.preventDefault();
		 
		 // REST  full
		 // js Object -> json/xml/ 순수데이터  -> GET/POST/DELETE/PUT  등등
		 //           <-                   <=
  	 	 
        
         let selectedMedium_ctgr_id = $(this).val(); 
         $.ajax({
        	    url: "/product/men_ci.do",
        	    dataType: "json",
        	    type: "POST",
        	     data: JSON.stringify({ mediumCtgrId: selectedMedium_ctgr_id }), 
        	    /* data: { mediumCtgrId: selectedMedium_ctgr_id }, */
        	    contentType: 'application/json; charset=utf-8',
        	    cache: false,
        	    success: function(data)
        	    {    var addedCategories = {};
        	    	//  server json -> 전송 -> js object   []
        	        console.log(data);  // 데이터를 콘솔에 출력하여 구조 확인      	      
        	        $(".aa").empty();   
        	        $(".photo_list").empty();
       	        
        	        // 데이터 구조가 List<BoardVO>라면
        	        $(data).each(function(index, element) { 
        	        	  if (!addedCategories[element.smallCtgrId]) {
        	            $(".aa").append(`
        	                <li class="right_radio" value="\${element.smallCtgrId}">
        	                    <a href="#" class="right_radio_a">\${element.smallCtgrName}</a>
        	                </li>
        	            `);   
        	            addedCategories[element.smallCtgrId] = true;
        	              }
        	            
        	        	  
        	            $(".photo_list").append(`	
        	                  	<li class="photo1">
        	       					<div class="cc">
        	       					
        	       						<a href="/sentiBoard/list/view.do?pd_id=\${element.pdId} ">
        	       							<div class="dd">
        	       								<img alt=""
        	       									src="\${element.pdImageUrl}"
        	       									class="ff">
        	       							</div>
        	       						</a> 
        	       						<div class="gg">
        	       							<a class="hh" href="#">\${element.brandName}</a> <a
        	       								title="\${element.pdName}">
        	       								<div class="j">
        	       									<h5 class="jj">\${element.pdName} (10 Color)</h5>
        	       									<strong class="jjj"></strong>
        	       									<div class="01">
        	       										<span class="kkk"></span> <strong class="qqq">\${element.pdPrice.toLocaleString('ko-KR')}원</strong>
        	       									</div>
        	       									<ul class="eee">
        	       										<li class="yyy"></li>
        	       										<li></li>
        	       									</ul>
        	       								</div>
        	       							</a>
        	       							<div class="ppp">
        	       							<button class="heart" data-pdid="\${list.pdId }">
	        	       						
	       										
		       										<c:if test="\${likeCheck eq 'F' }">
				       									<svg xmlns="http://www.w3.org/2000/svg" width="21" height="18"
				       										viewBox="0 0 20 20" class="bi-suit-heart">
				       										<path
				       											d="M2.24 3.425a4.758 4.758 0 0 1 6.79 0c.416.421.74.901.971 1.413.23-.512.553-.992.97-1.413a4.758 4.758 0 0 1 6.79 0 4.91 4.91 0 0 1 0 6.88L10 18.166l-7.76-7.863-.166-.176a4.911 4.911 0 0 1 .166-6.703z"
				       											fill="none" fill-rule="evenodd" stroke="#5d5d5d" stroke-width="1.5" />
				       									</svg>
			       									</c:if>
			       									<c:if test="\${likeCheck eq 'T' }">
				       									<svg xmlns="http://www.w3.org/2000/svg" width="21" height="18"
				       										viewBox="0 0 20 20" class="bi-suit-heart-fill">
				       										<path d="M2.24 3.425a4.758 4.758 0 0 1 6.79 0c.416.421.74.901.971 1.413.23-.512.553-.992.97-1.413a4.758 4.758 0 0 1 6.79 0 4.91 4.91 0 0 1 0 6.88L10 18.166l-7.76-7.863-.166-.176a4.911 4.911 0 0 1 .166-6.703z" 
				       										fill="red" fill-rule="evenodd" stroke="#5d5d5d" stroke-width="1.5"></path>
				       									</svg>
			       									</c:if>
		       									
		       								
	           									<h5 class="jj">좋아요 수 쿼리</h5>
           									</button>
        	       								<a href="#" class="review"> <svg
        	       										xmlns="http://www.w3.org/2000/svg" width="15" height="15"
        	       										viewBox="0 0 13 12" class="css-ik4rmz e1f8g7yn1">
        	       								<path
        	       											d="M4.146 3.95L0 4.583l3 3.075L2.292 12 6 9.95 9.708 12 9 7.658l3-3.075-4.146-.633L6 0z"
        	       											fill="none" fill-rule="evenodd" stroke="#5d5d5d"
        	       											stroke-width="1.5"></path>
        	       								</svg>
        	       									<div class="review-point">\${element.pdGrade}</div>
        	       									<div class="review-count">평점 준 사람들 카운팅 하는 쿼리문 필요함</div>
        	       								</a>
        	       							</div>
        	       						</div>
        	       					</div>
        	       				</li>                   		 
        	                         `);	
        	       
        	        });       	   
        	    },
        	    error: function() {
        	        alert("error");
        	    }
        	});
     });

	</script>
	<script>
	/*
	ajax를 통해 불러온 데이터는 기존에 사용하던 방법으로는 이벤트를 바인딩하지 못한다.
	그래서 ajax를 만들 때에 하드코딩으로 바로 event를 넣어버리는 경우가 있는데, event는 따로 javascript태그 안에 만들어 두는 것이 유지보수나 가시성에서 편리하다.
	따라서 다른 방법으로 바인딩하는 것이 좋다.
	$(document).on('click', '클래스명 혹은 id', function(){
	}); 
	*/
		
		$(document).on('click', '.right_radio', function(){
			 event.preventDefault();
	
			 let selectedSmall_ctgr_id = $(this).val(); 
			/*  alert(selectedSmall_ctgr_id) */
		        $.ajax({
		           url: "/product/men_si.do", 
		           dataType: "json",
		           type: "POST", 		           
		           data: JSON.stringify({ smallCtgrId: selectedSmall_ctgr_id }), 
		           contentType: 'application/json; charset=utf-8',
		           cache: false,
		           success: function(data){   		        	   
		                	$(".photo_list").empty(); 
		                	 console.log(data); 
		               $(data).each(function(index, element){	                     
		                   $(".photo_list").append(`	
		                  	<li class="photo1">
		       					<div class="cc">
		       						<a href="/sentiBoard/list/view.do?pd_id=\${element.pdId}">
		       							<div class="dd">
		       								<img alt=""
		       									src="\${element.pdImageUrl}"
		       									class="ff">
		       							</div>
		       						</a> 
		       						<div class="gg">
		       							<a class="hh" href="#">\${element.brandName}</a> <a
		       								title="\${element.pd_name}">
		       								<div class="j">
		       									<h5 class="jj">\${element.pdName} (10 Color)</h5>
		       									<strong class="jjj"></strong>
		       									<div class="01">
		       										<span class="kkk"></span> <strong class="qqq">\${element.pdPrice.toLocaleString('ko-KR')}원</strong>
		       									</div>
		       									<ul class="eee">
		       										<li class="yyy"></li>
		       										<li></li>
		       									</ul>
		       								</div>
		       							</a>
		       							<div class="ppp">
		       							<button class="heart" data-pdid="\${element.pdId }">
		       							
	       										<c:if test="\${likeCheck eq 'F' }">
			       									<svg xmlns="http://www.w3.org/2000/svg" width="21" height="18"
			       										viewBox="0 0 20 20" class="bi-suit-heart">
			       										<path
			       											d="M2.24 3.425a4.758 4.758 0 0 1 6.79 0c.416.421.74.901.971 1.413.23-.512.553-.992.97-1.413a4.758 4.758 0 0 1 6.79 0 4.91 4.91 0 0 1 0 6.88L10 18.166l-7.76-7.863-.166-.176a4.911 4.911 0 0 1 .166-6.703z"
			       											fill="none" fill-rule="evenodd" stroke="#5d5d5d" stroke-width="1.5" />
			       									</svg>
		       									</c:if>
		       									<c:if test="\${likeCheck eq 'T' }">
			       									<svg xmlns="http://www.w3.org/2000/svg" width="21" height="18"
			       										viewBox="0 0 20 20" class="bi-suit-heart-fill">
			       										<path d="M2.24 3.425a4.758 4.758 0 0 1 6.79 0c.416.421.74.901.971 1.413.23-.512.553-.992.97-1.413a4.758 4.758 0 0 1 6.79 0 4.91 4.91 0 0 1 0 6.88L10 18.166l-7.76-7.863-.166-.176a4.911 4.911 0 0 1 .166-6.703z" 
			       										fill="red" fill-rule="evenodd" stroke="#5d5d5d" stroke-width="1.5"></path>
			       									</svg>
		       									</c:if>
	       									
	       									
	       									<h5 class="jj">좋아요 수 쿼리</h5>
       									</button>
		       								<a href="#" class="review"> <svg
		       										xmlns="http://www.w3.org/2000/svg" width="15" height="15"
		       										viewBox="0 0 13 12" class="css-ik4rmz e1f8g7yn1">
		       								<path
		       											d="M4.146 3.95L0 4.583l3 3.075L2.292 12 6 9.95 9.708 12 9 7.658l3-3.075-4.146-.633L6 0z"
		       											fill="none" fill-rule="evenodd" stroke="#5d5d5d"
		       											stroke-width="1.5"></path>
		       								</svg>		       								
		       								<div class="review-count">평점 준 사람들 카운팅 하는 쿼리문 필요함</div>
		       								</a>
		       							</div>
		       						</div>
		       					</div>
		       				</li>                   		 
		                         `);	                     
										 });  //data.small_ctgr		       
		              												 
		          						 },//success
		       			  	  error: function(){
		      		      	   alert("error");
		         						  }	            	             
		     					   });

		});
	</script> 
	<script>
	$('.heart').on('click', function(){
			let pdId = $(this).data("pdid");
			
			if($(this).children('svg').attr('class') == "bi-suit-heart"){
				alert("빈하트 클릭" + pdId);
				
				$.ajax({
					url: "/product/addlike.do",
					dataType: "json",
					type: "POST",
					data: JSON.stringify({pdId: pdId}),
					contentType: 'application/json; charset=utf-8',
			        cache: false,
					success: function() {
						console.log("좋아요 추가");
					},
					error: function(request, status, error){
						console.log("addLike Ajax 에러 발생");
						console.log("상태코드 : " + request.status);
					}
				});
			}
		})
	</script>
<footer>
	 <jsp:include page="/WEB-INF/views/layout/bottom.jsp" flush="false"></jsp:include>
</footer>
</body>
</html>