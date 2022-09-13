function validateForm(form) {
		
			if(form.pass.value == "") {
				alert("비밀번호를 입력하세요");
				form.pass.focus();
				return false;
			}
		}