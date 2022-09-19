<%@ page language="java" contentType="text/html; charset=UTF-8"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/customer_service.css">
</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>
    <main>
        
        <h2>A/S 및 부품신청</h2>
        <input name="memberid" type="hidden" value="${sessionScope.memberid}">
        <table> <!--게시판-->
            <colgroup>
              <col class="select">
              <col class="no">
              <col class="title">
              <col class="writer">
              <col class="date">
            </colgroup>
            
            <thead>
              <tr>
                <th><input class="check.all" type="checkbox"></th>
                <th><p>번호</p></th>
                <th><p>제목</p></th>
                <th><p>작성자</p></th> 
                <th><p>조회수</p></th> 
                <th><p>날짜</p></th>
                <th><p>첨부파일</p></th>
              </tr>
            </thead>
            
            <tbody> 
	      	<!-- 게시물의 유무에 따라 결과가 달라짐 -->
				<c:choose>
					<c:when test="${ empty boardLists }">
						<tr>
							<td colspan="6" align="center">
								등록된 게시물이 없습니다.
							</td>
						</tr>
					</c:when>
			
					<c:otherwise>
						<c:forEach items="${boardLists}" var="row" varStatus="loop">
							<tr align="center">
								<td>
									${ map.totalCount - (((map.pageNum - 1) * map.pageSize) + loop.index)}
								</td>
								<td> 
									<!-- 상세보기1. 제목을 클릭하면 -->
									<a href="../mvc2/view2.do?idx=${row.idx }"> ${ row.title }</a>
								</td> 
								<td>${row.name }</td> 
								<td>${row.visitcount }</td>
								<td>${row.postdate }</td> 
								<td> 
									<c:if test="${not empty row.ofile }">
										<a href="../model2/download.do?ofile=${row.ofile}&sfile=${row.sfile}&idx=${row.idx}">[Down]</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
            </tbody>
          </table> <!-- 게시판 테이블 끝 -->
          
          <!-- 글쓰기1. 버튼을 클릭하면 글쓰는 창으로 넘어감 
          버튼 위치를 중간으로 둬야함 -->
         	
          
          <table  width="90%">
			<tr align="center">
				<td>
					${map.pagingImg }
				</td>
				
				<td>
          			<form method="get">
						<table  width="90%">
							<tr>
								<td align="center">
									<select name="searchField">
										<option value="title">제목</option>
										<option value="content">내용</option>
									</select>
									<input type="text" name="searchWord">
									<input type="submit" value="검색하기">
								</td>
							</tr>
						</table>
					</form>
          		</td>	
          		
          		<c:choose>
       				<c:when test="${sessionScope.memberid eq null }">
            			<td></td>
       				</c:when>
        			<c:otherwise>
						<td>				
							<button type="button" onclick="location.href='../mvc2/writecomp.do';">글쓰기</button>
						</td>
					</c:otherwise>
				</c:choose>
			
				<!-- <td>				
					<button type="button" onclick="location.href='../mvc2/writecomp.do';">글쓰기</button>
				</td> -->
			</tr>	
		  </table>
	<jsp:include page="./Footer.jsp"></jsp:include>
</body>
</html>
</body>
</html>