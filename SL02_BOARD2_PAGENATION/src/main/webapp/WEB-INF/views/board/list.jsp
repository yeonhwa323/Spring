<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="shortcut icon" href="/images/SiSt.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
span.material-symbols-outlined {
	vertical-align: text-bottom;
}
</style>
</head>
<body>
	<header>
		<h1 class="main">
			<a href="#" style="position: absolute; top: 30px;">yeon HOme</a>
		</h1>
		<ul>
			<li><a href="#">로그인</a></li>
			<li><a href="#">회원가입</a></li>
		</ul>
	</header>
	<h3>
		<span class="material-symbols-outlined">view_list</span> Spring days02
	</h3>
	<div>
		<xmp class="code"> list.jsp </xmp>
		<table>
			<caption style="text-align: right;">
				<a href="/board/register">글쓰기</a>
			</caption>
			<thead>
				<tr>
					<th>#번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>수정일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ empty  list }">
						<tr>
							<td colspan="5">no board...</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ list }" var="board">
							<tr>
								<td><c:out value="${ board.bno }" /></td>
								<%-- <td><a href="/board/get?bno=${ board.bno }"><c:out value="${ board.title }" /></a></td> --%>
								<%-- <td><a href="/board/get/${ board.bno }"><c:out value="${ board.title }" /></a></td> --%>
								<td><a class="move" href="${ board.bno }"><c:out value="${ board.title }" /></a></td>								
								<td><c:out value="${ board.writer }" /></td>
								<td><fmt:formatDate value="${ board.regdate }"
										pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatDate value="${ board.updatedate }"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<div class="center">
							<div class="pagination">
								<c:if test="${ pageMaker.prev }">
									<a href="${ pageMaker.startPage-1 }">&laquo;</a>
								</c:if>
								<c:forEach begin="${ pageMaker.startPage }"
									end="${ pageMaker.endPage }" step="1" var="num">
									<a href="${ num }"
									class='${ num eq pageMaker.criteria.pageNum ? "active": "" }'>${ num }</a>
								</c:forEach>
								<c:if test="${ pageMaker.next }">
									<a href="${ pageMaker.endPage+1 }">&raquo;</a>
								</c:if>
							</div>
							<!-- pagination -->
						</div>
						<!-- center -->
					</td>
				</tr>
			</tfoot>
		</table>
		
		<form id="actionForm" action="/board/list" method="get">
		  <input type="hidden" name="pageNum" value="${ pageMaker.criteria.pageNum}">
		  <input type="hidden" name="amount" value="${ pageMaker.criteria.amount}">
		  <!-- 검색조건, 검색어  -->
		</form>

	</div>

	<script>
  // rttr.addFlashAttribute("result", boardVO.getBno()) ;
  $(function () {
	  var result = '<c:out value="${result}" />';
	  checkModal(result);
	  // 뒤로가기 후 깨끗한 새로운 창 생성
	  history.replaceState({}, null, null);
	  
	  function checkModal(result) {
		  if( result == '' || history.state ) return ;
		  if( parseInt(  result  ) > 0 ) alert( `\${result} 번이 등록되었습니다.` );
	  }
	  
	  // 1. 제목을 클릭하면 상세보기 페이지로 이동.
	  // href="3" 글번호
	  // /board/get?bno=&pageNum=&amount=&type=&keyword= 등등
	  var actionForm = $("#actionForm")	;
	  
	  $("a.move").on("click", function() {
		  event.preventDefault();
		  
		  actionForm
		  	.attr("action", "/board/get")
		  	.append("<input type='hidden' name='bno' value='" + $(this).attr("href") +"'>")
		  	.submit();
		  
	  }); // click
	  
	  // 2. 페이징 번호 클릭 이동.
	  // /board/list?pageNum=3
	  $(".pagination a").on("click", function() {
		  event.preventDefault();
		  let pageNum = $(this).attr("href");
		  
		  //$("#actionForm :hidden[name=pageNum]").val(pageNum);
		  
		  actionForm		  	
		  	.find(":hidden[name=pageNum]").val(pageNum)
		  		.end()
		  	.submit();
		  
	  }); // click
	  
	  
  }); // function
</script>

</body>
</html>

<!-- c:out => 태그(스타일) 적용안되게하기위해서 사용 -->