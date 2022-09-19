<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/SignUp.css">
</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>
	<h2>회원가입</h2>
	<form class="signup_frm" action="../membership/signUp.do" method="post" onsubmit="return validateForm(this)">
		<ul>
			<li>아이디</li>
			<li><input type="text" name="user_id"></li><br>
			<li>비밀번호</li>
			<li><input type="password" name="user_pw"></li><br>
			<li>비밀번호 재확인</li>
			<li><input type="password" name="pw_re"></li><br>
			<li>이름</li>
			<li><input type="text" name="user_name"></li><br>
			<li>생년월일</li>
			<li><input type="number" name="birthyear">
				<select name="birthmonth">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
				<input name="birthday" type="number" min="1" max="31">
			</li>
			<li>성별</li>
			<li><select name="gender">
				<option selected>남성</option>
				<option>여성</option>
				<option>선택안함</option>
			</select></li><br>
			<li>전화번호</li>
			<li><input type="text" name="user_tel"></li><br>
			<li>이메일</li>
			<li><input type="email" name="user_email"></li>
			<li><a data-bs-toggle="modal" data-bs-target="#checkbox1">필수사항</a><input type="checkbox" name="requiredList" required></li>
			<li><a data-bs-toggle="modal" data-bs-target="#checkbox2">동의사항2</a><input type="checkbox" name="optionalList"></li>
		</ul>


		<div class="modal fade" id="checkbox1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="checkbox1">필수 동의사항</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        개인정보
		        연락처
		        기타 등등
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>

		<div class="modal fade" id="checkbox2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="checkbox2">선택 동의 사항</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        마케팅 동의
		        기타 등등
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
		<input type="submit" value="가입하기">
		<input type="reset" value="초기화">
	</form>

	<jsp:include page="Footer.jsp"></jsp:include>
	<script type="text/javascript">
	let form = document.member;
	let regExpId = /^[a-z|A-Z|0-9]*$/;
	let regExpName = /^[가-힣]*$/;
	let regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/;
	let regExpEmail = /^[0-9|a-z|A-Z]([-_.]?[0-9|a-z|A-Z])*@[0-9|a-z|A-Z]([-_.]?[0-9|a-z|A-Z])*\.[a-z|A-Z]{2,3}$/i;
	
	function validateForm(form) {
		if(form.user_id.value== ""){
			alert("아이디를 입력하세요");
			form.user_id.focus();
			return false;
		}if(!regExpId.test(form.user_id.value)){
			alert("아이디는 숫자와 영문으로만 구성할 수 있습니다.");
			form.user_id.focus();
			return false;
		}if(form.user_pw.value== ""){
			alert("비밀번호를 입력하세요");
			form.user_pw.focus();
			return false;
		}if(form.pw_re.value== ""){
			alert("비밀번호 재확인을 입력하세요");
			form.pw_re.focus();
			return false;
		}if(form.user_name.value== ""){
			alert("이름을 입력하세요");
			form.user_name.focus();
			return false;
		}if(!regExpName.test(form.user_name.value)){
			alert("이름은 한글로만 입력해주세요");
			form.user_name.focus();
			return false;
		}
		if(form.birthyear.value== ""){
			alert("생일을 입력하세요");
			form.birthyear.focus();
			return false;
		}if(form.birthday.value== ""){
			alert("생일을 입력하세요");
			form.birthday.focus();
			return false;
		}if(form.user_tel.value== ""){
			alert("전화번호를 입력하세요");
			form.user_tel.focus();
			return false;
		}if(!regExpPhone.test(form.user_tel.value)){
			alert("전화번호 형식에 알맞지 않습니다");
			form.user_tel.focus();
			return false;
		}if(form.user_email.value== ""){
			alert("이메일을 입력하세요");
			form.user_email.focus();
			return false;
		}if(!regExpEmail.test(form.user_email.value)){
			alert("이메일 형식에 맞는지 확인해주세요");
			form.user_email.focus();
			return false;
		}if(form.user_pw.value != form.pw_re.value){
			alert("비밀번호 값이 다릅니다.");
			form.user.pw.value="";
			form.pw_re.value="";
			form.user_pw.focus();
			return false;
		}
	}
	</script>
</body>
</html>