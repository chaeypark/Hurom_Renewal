<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
									<a href="../model2/view.do?idx=${row.idx }"> ${ row.title }</a>
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
          
          <table  width="90%">
			<tr align="center">
				<td>
					${map.pagingImg }
				</td>
			
				<td>
				<!-- 글쓰기1. 버튼을 클릭하면 .. -->
					<button type="button" onclick="location.href='../model2/write.do';">글쓰기</button>
				</td>
			</tr>
		  </table>
	<jsp:include page="./Footer.jsp"></jsp:include>
</body>
</html>