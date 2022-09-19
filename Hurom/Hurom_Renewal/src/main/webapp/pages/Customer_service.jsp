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
        <div class="customer_support"><p>고객지원</p></div>
        <div class="top">
            <div class="qna"><a href="../mvc2/listQNA.do">
                <img class="thumnail_headset" src="../image/customer_service/headset.png" alt="inquiry">
                <p class="qna_wr" >1:1문의</p>
            </a></div>
            <div class="repair"><a href="../mvc2/listAS.do">
                <img class="thumnail_repair" src="../image/customer_service/repairtool.PNG" alt="">
                <p class="repair_wr">A/S및 부품 신청</p>
            </a></div>       
            <div class="manual_book"><a href="../pages/manual.jsp">
                <img class="thumnail_book" src="../image/customer_service/book-bookmark.png" alt="">
                <P class="manual_wr">제품 설명서 및 동영상 가이드</P>
            </a></div>              
        </div>
        
        <div class="frequent_qna">
            <p>자주 묻는 질문</p>
        </div>
        
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
                <!-- <th><input class="check.all" type="checkbox"></th> -->
                <th><p>번호</p></th>
                <th><p>제목</p></th>
                <th><p>작성자</p></th> 
                <th><p>조회수</p></th> 
                <th><p>날짜</p></th>
                
              </tr>
            </thead>
            
            <tbody> 
	      	<!-- 게시물의 유무에 따라 결과가 달라짐 
	      		el 사용-->
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
									${ map.totalCount - (((map.pageNum - 1) * map.pageSize) + loop.index)}
								</td>
								<td> 
									<!-- 상세보기1. 제목을 클릭하면 -->
									<a href="../mvc2/view0.do?idx=${row.idx }"> ${ row.title }</a>
								</td> 
								<td>${row.name }</td> 
								<td>${row.visitcount }</td>
								<td>${row.postdate }</td> 
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
				
				
				<td>
          			<form method="get" >
						<table  width="90%">
							<tr align="center" >	
								<td></td>
								<td></td>								
								<td colspan="4" align="center"><select name="searchField">
									<option value="title">제목</option>
									<option value="content">내용</option>
								</select></td>
								<td><input type="text" name="searchWord"></td>
								<td><input type="submit" value="검색하기"></td>
								<td></td>
							</tr>
						</table>
					</form>
          		</td>
          		
          		
          		
          		
          		
			</tr>			
		  </table>
          
          
         
          
          
          
          

    </main>
    <jsp:include page="./Footer.jsp"></jsp:include>
    <script src="../JS/customer_service.js"></script>
</body>
</html>