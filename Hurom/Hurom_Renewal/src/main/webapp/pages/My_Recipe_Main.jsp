<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/My_Recipe_Main.css?v=1" rel="stylesheet">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="r_main">
		<h2>고객레시피</h2>
		
		<span class="writebut">
			<button id="writebut" type="button" onclick="location.href='../recipe/write.do'">글쓰기</button>
		</span>
		
		<div id="recipe_mainlist">
			<c:choose>
				<c:when test="${ empty boardLists }">
					<span id="nonecontent">등록된 게시물이 없습니다.</span>
				</c:when>
				<c:otherwise>
					<c:forEach items="${boardLists}" var="row" varStatus="loop">
						<a id="r_link" href="../recipeboard/view.do?idx=${row.idx}">
							<img src="../Upload/${row.thumnail}">
							<br>
							<span class="product_name_title">
								<strong id="product_name">${row.product_name }</strong>
								<span id="title">${row.title }</span>
							</span>
						</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		
		<div id="r_bottom">
			<table width="100%">
				<tr align="center">
					<td id="pSagingimg">
						${map.pagingImg }
					</td>
				</tr>
			</table>
			
			<form id="r_search" method="get" action="../recipe/main.do">
				<table width="100%">
					<tr>
						<td align="center">
							<select id="searchField" name="searchField">
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="product_name">모델명</option>
							</select>
							<input id="searchWord" name="searchWord" type="text">
							<input id="serchbut" type="submit" value="검색">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>