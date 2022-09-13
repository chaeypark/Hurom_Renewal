function validateForm(form) {
			if(form.name.value == "") {
				alert("이름을 입력하세요");
				form.name.focus();
				return false;
			}
			if(form.title.value == "") {
				alert("제목을 입력하세요");
				form.title.focus();
				return false;
			}
			if(form.content.value == "") {
				alert("내용을 입력하세요");
				form.content.focus();
				return false;
			}
			if(form.pass.value == "") {
				alert("비밀번호를 입력하세요");
				form.pass.focus();
				return false;
			}
		}