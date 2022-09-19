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
<link href="../css/My_Recipe_View.css?v=1" rel="stylesheet">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="recipe_View_Wrap">
		<h2>고객레시피</h2>
		<table class="viewtable">
			<tr style="border-bottom: 1px solid lightgray;">
				<td id="idx_title">
					<span style="color: rgb(59, 58, 58);">[${Tdto.idx }]</span>
					<span id="separtor"></span>
					${Tdto.title }
				</td>
			</tr>
			<tr style="border-bottom: 1px solid lightgray;">
				<td id="writer_id_postdate">
					<span id="writer_id">${Tdto.writer_id }</span>
					<span id="separtor"></span>
					<span style="font-size: 10px;">&nbsp;${Tdto.postdate }</span>
				</td>
			</tr>
			<tr style="border-bottom: 1px dotted lightgray;">
				<td class="product_img">
					<a href="../pages/Goods.jsp?product_num=${pdto.product_num }"><img src="../image/Main/${Tdto.product_name }.webp" alt="."></a>
					&nbsp;&nbsp;
					<span style="vertical-align: top;">
						<span style="font-weight: 800;">${Tdto.product_name }</span>
						모델을 이용한 나만의 레시피
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center" id="images">
					<c:forEach items="${imgview}" var="images">
						<img src="../Upload/${images.newname }">
					</c:forEach>
				</td>
			</tr>
			<tr style="border-bottom: 1px solid lightgray;">
				<td id="content">${Tdto.content }<td>
			</tr>
			<tr>
				<td colspan="4" align="right" id="buts">
					<button type="button" id="edit" onclick="location.href='../recipeboard/edit.do?idx=${Tdto.idx}'">수정</button>
					<button type="button" id="delete" onclick="location.href='../recipeboard/delete.do?idx=${Tdto.idx }'">삭제</button>
					<button type="button" id="list" onclick="location.href='../recipe/main.do?'">목록</button>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>