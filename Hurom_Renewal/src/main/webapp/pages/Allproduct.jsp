<%@page import="common.BoardPage"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="product.ProductDTO"%>
<%@page import="product.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../css/Allproduct.css">

</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>
	<jsp:include page="./slide.jsp"></jsp:include>
	
      <div class="products">
		<div class="side">
			<form method="get">
				<ul>
					<li>착즙기<input type ="radio" name="p_cat" value="juice_maker"></li>
					<li>스팀팟<input type ="radio" name="p_cat" value="steam_pot"></li>
					<li>티전문가전<input type ="radio" name="p_cat" value="tea_maker"></li>
					<li>에어프라이어<input type ="radio"name="p_cat" value="air_fryer"></li>
					<li>블렌더<input type ="radio"name="p_cat" value="blender"></li>
					<li><input type="submit" value="검색하기"></li>
				</ul>
			</form>
		</div><!-- side -->
        <div class="AllProductsWrap">
        	<div class="row row-cols-1 row-cols-md-4 g-4">
        	  <%ProductDAO dao = new ProductDAO(application);
          	ProductDTO dto = new ProductDTO();
          	List<Object> CatList = new Vector<Object>();//이걸 옵션에서 받아와야함
          	String[] b = request.getParameterValues("p_cat");
          	if (b != null){
              	for (int i=0; i<b.length; i++){
              		CatList.add(b[i].toString());
          		}
          	}
          	Map<String, Object> param = new HashMap<String, Object>();
          	
        	int pageSize = 8;
        	int blockPage = 5;
        	//결과가 나누어 떨어지지 않으면 반드시 올림처리 해서 나머지를 표시해야함
        	
			int totalCount = dao.totalproductNumber(CatList);
        	
        	int totalPage = (int)Math.ceil((double)totalCount/pageSize);
        	
        	//현재 페이지 확인
        	
        	int pageNum = 1;
        	String pageTemp =request.getParameter("pageNum");
        	if (pageTemp != null && !pageTemp.equals("")){
        		pageNum = Integer.parseInt(pageTemp);
        	}
        	
        	//목록에 출력할 게시물의 범위 확인
        	int start = (pageNum - 1)* pageSize + 1; //첫 게시물 번호
        	int end = pageNum * pageSize; // 마지막 게시물 번호
          	
        	param.put("start", start);
        	param.put("end" , end);
        	
          	List<ProductDTO> totalProduct = dao.totalProduct(CatList, param);
          	if (totalProduct.size() == 0){
          		%><h2> 상품이 없습니다.</h2><%
          	}else{
          	
          	for (int i = 0; i<totalProduct.size(); i++){
          		String productName = totalProduct.get(i).getProduct_name();
          		int productPrice = totalProduct.get(i).getPrice();
          		String productNum = totalProduct.get(i).getProduct_num();
          		String productCat = totalProduct.get(i).getP_cat();
          		
          		%>
		          <div class="col">
		            <div class="card border-white h-100">
		              <div class="hover_item">
		                <a href="Goods.jsp?product_num=<%=productNum%>" class="hovertest"><img src="../image/Main/<%=productName%>.webp" class="card-img-top" alt="..."></a>
		                <div class="over">
		                  <div class="over_in">
		                    <p><a href="추후 장바구니 구현 후 해당 상품 장바구니 이동 코드 삽입" style="border-right: solid 1px rgba(238, 229, 229, 0.479);"><img src="../image/AllProduct/cart.png" alt="장바구니담기"></a></p>
		                    <p><a href="Goods.jsp?product_num=<%=productNum%>"><img src="../image/AllProduct/credit-card.png" alt="구매하기"></a></p>
		                  </div>
                 		 </div>
		              </div>
		              <div class="card-body">
		                <h5 class="card-title"><%=productName %></h5>
		                <span style="background-color: lightgray;"></span>
		                <span style="background-color: #111;"></span>
		                <span class="color_bar_white"></span>
		                <span style="background-color: red;"></span>
		                <span style="background-color: beige;"></span>
		                <span style="background-color: darkgreen;"></span>
		                <p class="card-text"><%=productPrice %>원</p>
		              </div>
		            </div>
		          </div>
          		<%
          	}
          	}
          	dao.close();
          %>
          </div>
         </div>
	          <div class="ProductPaging">
	          	<%=BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI(), CatList) %>
	          </div>
         </div>
	<jsp:include page="./Footer.jsp"></jsp:include>
</body>
</html>