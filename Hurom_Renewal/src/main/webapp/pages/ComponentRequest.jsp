<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
          <h2>a/s 및 부품신청</h2>
          
          <form name="writeFrm" method="post" enctype="multipart/form-data"
			onsubmit="return validateForm(this);" action="../mvc2/writecomp.do">
			<input name="memberid" type="hidden" value="${sessionScope.memberid}">
		<table  width="90%">
			<tr>
				<td>작성자</td>
				<td><input type="text" name="name" style="width: 150px;"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" style="width: 90%"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" style="width: 90%; height: 100px"></textarea></td>
			</tr>
			<tr>
				<td>첨부 파일</td>
				<td><input type="file" name="ofile"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass" style="width: 150px;"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit" >작성 완료</button>
					<button type="reset">초기화</button>
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