<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.del_wrap{
	margin-top: 100px;
	margin-left: 800px;	
	height: 500px;
}

.del_wrap h2{
	margin-bottom: 20px;
}

.userDelBtn{
	margin-top: 30px;
	
}

</style>
</head>
<body>
<jsp:include page="../pages/Header.jsp"></jsp:include>	
<div class="del_wrap">
	<form method="post" action="../mypageCon/userDel.do">
		<h2>회원 탈퇴</h2>
		<table>
			<tr><td>
				<input type="text" style="border:none;" value="${dto.membername}">님 정말 탈퇴하시겠어요?				
			</td></tr>
			
			<tr><td>
				비밀번호를 입력해주세요
				<input type="password" name="boardPass" id="boardPass">
			</td></tr>
			<tr><td>
				<input type="submit" value="탈퇴하기" class="userDelBtn">
			</td></tr>
		</table>
	</form>			
</div>
<jsp:include page="../pages/Footer.jsp"></jsp:include>	

</body>
</html>