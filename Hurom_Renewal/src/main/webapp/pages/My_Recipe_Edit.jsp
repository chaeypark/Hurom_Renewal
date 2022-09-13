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

<link href="../css/My_Recipe_Edit.css?v=1" rel="stylesheet">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="recipeEditWrap">
		<form name="recipeEditFrm" onsubmit="return validateForm(this)" action="../recipeboard/edit.do?idx=${dto.idx }" method="post">
			<input type="hidden" name="writer_id" value="${sessionScope.memberid}">
			<div class="recipeEditTitle">
				<input type="text" name="title" value="${dto.title }">
			</div>
			<div class="recipeEditContent">
			<textarea name="content">${dto.content }</textarea>
			</div>
			<div id="recipeEditBottom">
				<button type="submit" id="submit">저장하기</button>
				<button type="button" onclick="window.location.reload(true);" id="reset">초기화</button>
				<select name="productName">
					<%
						for (int i = 0; i<totalProduct.size(); i++){
							String productName = totalProduct.get(i).getProduct_name();
					%>		<option value="<%=productName%>"><%=productName%></option>
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
			if(form.title.value == ""){
				alert("제목을 입력하세요.");
				form.title.focus();
				return false;
			}
			
			if(form.content.value == ""){
				alert("내용을 입력하세요.");
				form.content.focus();
				return false;
			}
		}
	</script>
</body>
</html>