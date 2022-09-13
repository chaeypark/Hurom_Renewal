<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 목록28화면에 구현할 폼 구현 -->
	<h2>파일 첨부형 게시판 - 목록보기(List)</h2>
	
	<!-- 검색창만들기 -->
	<form method="get">
		<table border="1" width="90%">
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
	</form><!-- 검색창 만들기 끝 -->
	
	
	<!-- 게시판 테이블 -->
	<table border="1" width="90%">
		<tr>
			<th width="10%">번호</th>
			<th width="*">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
			<th width="10%">첨부파일</th>
		</tr>
		
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
	</table>
	
	<!-- 목록28 하단메뉴. (바로가기, 글쓰기) ==목록구현의 끝 -->
	
	<table border="1" width="90%">
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
</body>
</html>









