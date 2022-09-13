<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주묻는 질문-상세보기</title>
    <link rel="stylesheet" href="../css/customer_service_q&a.css">
</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>
	<jsp:include page="./slide.jsp"></jsp:include>
    <main>
      
      	<h2>자주묻는 질문-상세보기</h2>

		<table border="1" width="90%">	
			<calgroup>	
				<col width = "15%" />	
				<col width = "35%" />
				<col width = "15%" />	
				<col width = "35%" />	
			</calgroup> 
	
			<tr>	
				<td>번호</td>	<td>${dto.idx}</td>	
				<td>작성자</td>	<td>${dto.name}</td>							
			</tr>			
	
			<tr>	
				<td>작성일</td>	<td>${dto.postdate}</td>	
				<td>조회수</td>	<td>${dto.visitcount}</td>	
			</tr>
	
				
			<tr>	
				<td>제목</td>	
				<td colspan="3">${dto.title }</td>	
			</tr>	
			
	
			<tr>	
				<td>내용</td>	
				<td colspan="3" height="100px">${dto.content }</td>	
			</tr>
				
	
			<tr>	
				<td>첨부파일</td>	
				<td>
	
				<!-- 다운로드1. 다운로드 버튼을 클릭하면.. -->	
					<c:if test="${not empty dto.ofile}">	
						${dto.ofile }	
						<a href="../mvc2/download.do?ofile=${dto.ofile }&sfile=${dto.sfile}&idx=${dto.idx}">[다운로드]</a>		
					</c:if>					
				</td>	
				<td>다운로드수</td>	
				<td>${dto.downcount}</td>	
			</tr>
	
			
	
			<tr>	
			<!-- 삭제1/ 수정1 -->	
				<td colspan="4" align="center">	
				<!-- 수정1. 버튼을 클릭하면 비밀번호확인 시작 -->	
				<!-- 수정하기와 삭제하기는 먼저 패스워드가 맞는지 확인해야함 = 비밀번호검증 model2 에 PassController.java생성 -->	
				<!-- mode = EditController에 연결되어 있음 -->	
					<button type="button" onclick="location.href='../mvc2/pass0.do?mode=edit&idx=${param.idx}'">수정하기</button>
					<button type="button" onclick="location.href='../mvc2/pass0.do?mode=delete&idx=${param.idx}'">삭제하기</button>
					<button type="button" onclick="location.href='../mvc2/list.do'">목록보기</button>	
				</td>	
			</tr>
		</table>             
    </main>
    <jsp:include page="./Footer.jsp"></jsp:include>
    
</body>
</html>