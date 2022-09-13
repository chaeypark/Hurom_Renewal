<%@page import="membership.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.delwrap{
		width: 700px;
		margin: 0 auto;
		padding-top: 100px;
		padding-bottom: 100px;
		padding-left: 50px;
		
	}
	
	.delwrap th{
		line-height: 50px;
	}
	/* .editBirthCss{		
		display : flex;		
		padding-top: 10px;		
	} */
</style>
</head>

<body>
	<jsp:include page="../pages/Header.jsp"></jsp:include>
	
	<div class="delwrap" id="delwrap">
		<form class="signup_frm" method="post" onsubmit="return validateForm(this)"
			action="../mypageCon/userEdit.do" accept-charset="utf-8">
			<h2>회원정보</h2>
			<table style="width:100%;" id="table">				
				<tr>
					<th>아이디</th>
					<td><input type="text" name="user_id" value="${dto.memberid }" readonly><br></td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="user_name" value="${dto.membername }"></td>
				</tr>
								
				<tr>
					<th>생년월일</th>
					<td>
						<input type="text" name="birthdate" id="birthdate" value="${dto.birthdate}"><br>		
							
						<input type="text" name="birthyear" id="birthyear"> 
						
						<select name="birthmonth" id="birthmonth">
							<option value="1" >1</option>
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
									
						<input type="text" name="birthday" id="birthday">					
					</td> 
				</tr> 
				<tr>
					<th>성별</th>
					<td>
						<input type="hidden" value="${dto.gender}">	
						
						<select name="gender" id="editGender">								
							<option <c:if test="${dto.gender.equals('남성')}">selected</c:if> value="남성">남성</option>
							<option <c:if test="${dto.gender.equals('여성')}">selected</c:if> value="여성">여성</option>	
						</select>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="user_tel" value="${dto.membertel }"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="user_email" value="${dto.email }"></td>
				</tr>
				
				<tr>
					<th>
						필수사항 <input type="checkbox" name="requiredList" value="${dto.required}" id="requiredList" required
									<c:if test="${dto.required.equals('on')}">checked</c:if>>	
					</th>
					
					<th>
						<input type="hidden" value="${dto.optional}">	
						동의사항2 <input type="checkbox" name="optional"  id="optionalList" onsubmit="optChk();"
									<c:if test="${dto.optional.equals('on')}">checked</c:if>>
					</th>
				</tr>
				
				<tr>
					<th>비밀번호 수정하기</th>
					<td><input type="text" name="passEdit"></td>
				</tr> 
				
				<tr>
					<th>비밀번호 입력</th>
					<td><input type="text" name="boardPass"></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="수정하기"></td>				
				</tr>
			</table>
			
			
	</form>
	
	</div>
	
	<script>			
		var birthVal = document.querySelector("#birthdate").value
		//alert(birthVal);
		let birthArr = [] 
		birthArr = birthVal.split("-");
		
		let birthyear = document.querySelector("#birthyear")
		let birthmonth = document.querySelector("#birthmonth")
		let birthday = document.querySelector("#birthday")
		
		birthyear.value = birthArr[0];
		birthmonth.value = birthArr[1];			
		birthday.value = birthArr[2]; 	
		
		let delwrap = document.querySelector("#delwrap")
		delwrap.addEventListener("change", monfun())
		
		function monfun(){	
			
			for(var i=0 ; i<12; i++){				
				if(parseInt(birthmonth.options[i].value) == parseInt(birthArr[1])){						
					birthmonth.options[i].selected = true;
					break;
					
				}else{					
					birthmonth.options[i].selected = false;
				}
			}	
			
		}   

	
		var opt = document.querySelector("#optionalList");
		
		function optChk(){
            if(opt){            	      	
            	let optional = opt.checked;            	
            } else {
            	let optional = !opt.checked;  
            }
        }		
	</script>
	<jsp:include page="../pages/Footer.jsp"></jsp:include>
</body>
</html>