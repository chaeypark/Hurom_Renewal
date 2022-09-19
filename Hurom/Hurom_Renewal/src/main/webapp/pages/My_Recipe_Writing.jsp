<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="product.ProductDTO"%>
<%@page import="product.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
 
<%
	ProductDAO dao = new ProductDAO(application);
	ProductDTO dto = new ProductDTO();
	List<ProductDTO> totalProduct = dao.totalProduct();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/My_Recipe_Writing.css?v=1" rel="stylesheet">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="recipeWritingWrap">
		<form id="recipeFrm" name="recipeFrm" enctype="multipart/form-data" onsubmit="return validateForm(this)" action="../recipe/write.do" method="post">
			<input type="hidden" name="writer_id" value="${sessionScope.memberid}">
			
			<div class="recipeWritingTitle">
				<input type="text" name="title" placeholder="제목">
			</div>
			
			<div class="recipeWritingContent">
				<textarea name="content" id="content" placeholder="내용"></textarea>
			</div>
			
			<div id="imagePreview"><img id="file1"></div>
			
			<div id="recipeWritingBottom">
				<div id="imageUpload">
					<input type="file" id="r_img_upload" accept="image/*" name="file1" onchange="readURL1(this);"><br>
				</div>
				<button type="button" onclick="addInput()" id="butplus">버튼 추가</button>
				<button type="submit" id="submit">저장하기</button>
				<button type="button" onclick="window.location.reload(true);" id="reset">초기화</button>
				
				<select name="productName">
					<%
						for (int i = 0; i < totalProduct.size(); i++){
							String productName = totalProduct.get(i).getProduct_name();
					%>		
							<option value="<%=productName%>"><%=productName%></option>
					<%
			      		} 
					%>
				</select>
			</div>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
	
	<script>
		
		function validateForm(form) {
			if (form.title.value == "") {
				alert("제목을 입력하세요.");
				form.title.focus();
				return false;
			}
			
			if (form.content.value == "") {
				alert("내용을 입력하세요.");
				form.content.focus();
				return false;
			}
			
		   	var fileCheck = document.getElementById("r_img_upload").value;
		  	if (!fileCheck) {
		        alert("이미지 파일을 첨부해 주세요.");
		        return false;
		  	}
		  	
		}
		
		let imageUpload = document.getElementById("imageUpload");
		let imagePreview = document.getElementById("imagePreview");
		let pictureCount = 1;
		
		function addInput(){
			if (pictureCount < 10){
				imageUpload.innerHTML += "<input type='file' id='r_img_upload' accept='image/*' name='file" + ++pictureCount + "' onchange='readURL" +pictureCount+ "(this);'><br>"
				imagePreview.innerHTML += "<img id='file" + pictureCount + "'/>"
			} else {
				alert("이미지는 최대 10개까지 첨부 가능합니다!!");
			}

		}
		
		function readURL1(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file1').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else { 
			    document.getElementById('file1').src = "";
			  }
		}
		function readURL2(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file2').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file2').src = "";
			  }
		}
		function readURL3(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file3').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file3').src = "";
			  }
		}
		function readURL4(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file4').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file4').src = "";
			  }
		}
		function readURL5(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file5').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file5').src = "";
			  }
		}
		function readURL6(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file6').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file6').src = "";
			  }
		}
		function readURL7(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file7').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file7').src = "";
			  }
		}
		function readURL8(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file8').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file8').src = "";
			  }
		}
		function readURL9(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file9').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file9').src = "";
			  }
		}
		function readURL10(input) {
			  if (input.files && input.files[0]) {
			    var reader = new FileReader();
			    reader.onload = function(e) {
			      document.getElementById('file10').src = e.target.result;
			    };
			    reader.readAsDataURL(input.files[0]);
			  } else {
			    document.getElementById('file10').src = "";
			  }
		}
	</script>
</body>
</html>
