<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="../css/customer_service_q&a.css">
</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>
	<jsp:include page="./slide.jsp"></jsp:include>
    <main>
      
     	<h2>AS 및 부품신청-수정페이지</h2>
		<form name="writeFrm" method="post" enctype="multipart/form-data" 
		onsubmit="return validateForm(this)" action="../mvc2/edit2.do">

		<!-- 내용을 같이 전달할 input ; 폼에서 전달 할 때 input에 있는 내용들도 함께 전달됨  -->
		<input type="hidden" name="idx" value="${dto.idx }">
		<input type="hidden" name="prevOfile" value="${dto.ofile }">
		<input type="hidden" name="prevSfile" value="${dto.sfile }">

		<table border="1" width="90%">
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="name" style="width:150px;" value="${dto.name}">
				</td>
			</tr>

			

			<tr>
				<td>제목</td>
				<td><input type="text" name="title" style="width: 90%" value="${dto.title}"></td>
			</tr>

			<tr>
				<td>내용</td>
				<td><textarea name="content" style="width: 90%; height: 100px">${dto.content}</textarea></td>
			</tr>
	
			<tr>
				<td>첨부 파일</td>
				<td><input type="file" name="ofile"></td> 
			</tr>

			<tr>
				<td colspan="2" align="center">
					<button type="submit">작성 완료</button>
					<button type="reset">초기화</button>
					<!-- onclick : 클릭하면 해당하는 location으로 이동시켜주세요-->
					<button type="button" onclick="location.href='../mvc2/listAS.do';">목록 바로가기</button>
				</td>
			</tr>

		</table>
	</form>
     	           
    </main>
    <jsp:include page="./Footer.jsp"></jsp:include>  
    <script src="../JS/write.js"></script>  
</body>
</html>