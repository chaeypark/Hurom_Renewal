<!-- 비밀번호 확인 뷰어 -->
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
      
     	<h2>비밀번호 확인</h2>

	<form name="writefrm" method="post" onsubmit="return validateForm(this);" action="../mvc2/pass0.do">
	<input type="hidden" name="idx" value="${param.idx }">
	<input type="hidden" name="mode" value="${param.mode }">
	
		<table border="1" width="90%">
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass" style="width:100px;"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button type = "submit">확인</button>
				<button type="reset">초기화</button>
				<button type="button" onclick="location.href='../mvc2/list.do';">목록보기</button>
				</td>
			</tr>
		</table>
	</form>
      	
      	           
    </main>
    <jsp:include page="./Footer.jsp"></jsp:include>  
    <script src="../JS/pass.js"></script> 
</body>
</html>