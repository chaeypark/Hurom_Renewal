<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="../css/customer_service.css">
</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>
	<jsp:include page="./slide.jsp"></jsp:include>
	<table> <!--게시판-->
            <colgroup>
              <col class="no">
              <col class="title">
              <col class="type">
              <col class="url">
            </colgroup>
            
            <thead>
               <tr>        
                <th><p>제품번호</p></th>
                <th><p>제품이름</p></th>
                <th><p>타입</p></th>
                <th><p>첨부파일</p></th>
              </tr>
            </thead>
            
            <tbody>
            <c:choose>
					<c:when test="${ empty boardLists }">
						<tr>
							<td colspan="5" align="center">
								등록된 게시물이 없습니다.
							</td>
						</tr>
					</c:when>
			
					<c:otherwise>
						<c:forEach items="${boardLists}" var="row" varStatus="loop">
							<tr align="center">
								
								<td>
									${row.product_num}
								</td>
								<td> 
									
									<!-- 상세보기1. 제목을 클릭하면 -->
									<a href="../mvc2/view.do?idx=${row.product_num }"> ${ row.product_name }</a> 
								</td> 
								<td>${row.p_cat }</td> 
								<td> 
									<a href="${row.url_add }">메뉴얼</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
            </tbody>
          </table> <!-- 게시판 테이블 끝 -->
          
          <table  width="90%">
			<tr align="center">
				<td>
					${map.pagingImg }
				</td>
			<!-- 가지고온 아이디의 값이 test1일 때만 글쓰기 버튼 보임 -->
			<c:choose>
				<c:when test="${sessionScope.memberid eq 'test1' }">
					<td>
						<button type="button" onclick="location.href='../mvc2/edit.do';">글쓰기</button>
					</td>
				</c:when>
				<c:otherwise>
					<td></td>
				</c:otherwise>
			</c:choose>
			
				
			</tr>
		  </table>
	<jsp:include page="./Footer.jsp"></jsp:include>
</body>
</html>