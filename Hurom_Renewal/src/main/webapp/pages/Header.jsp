<%@page import="java.util.List"%>
<%@page import="product.ProductDAO"%>
<%@page import="product.ProductDTO"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, height=device-height">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/header.css">
</head>
<body>
	    <nav class="navbar">
        <a href="../pages/index.jsp" class="logo">로고</a>
        <div class="middle_menu">
            <ul>
                <p type="button" data-bs-toggle="offcanvas" data-bs-target="#product">제품</p>
                <p type="button" data-bs-toggle="offcanvas" data-bs-target="#comunity">커뮤니티</p>
                <p type="button" data-bs-toggle="offcanvas" data-bs-target="#service"  >고객센터</p> 
                <!-- <button type="button" onclick="location.href='../mvc2/list.do';">고객센터</button> -->
            </ul>
        </div>
        <div class="login_section">
            <ul>
            	<c:choose>
            		<c:when test="${sessionScope.memberid eq null }">
            			<li><a class="login" data-bs-toggle="modal" data-bs-target="#staticBackdrop" title="로그인">로그인</a></li>
            			<li><a class="signup" href="../membership/signUp.do" title="회원가입">회원가입</a></li>
            		</c:when>
            		<c:otherwise>
            			<li><a class="logout" href="../membership/logout.do" onclick="return confirm('로그아웃하시겠습니까?');">로그아웃</a></li>
            			<li><a class="mypage" href="../mypageCon/mypage.do">마이페이지</a></li>
            			<li><a class="order" href="cart.html" title="장바구니">장바구니</a></li>
            		</c:otherwise>
            	</c:choose>
                
            </ul>
        </div>
        <div class="search_box">
            <form action="">
                <input type="text" name="search">
                <input type="submit" value="submit">
            </form>
        </div>
    </nav>
	<div class="offcanvas offcanvas-top" data-bs-scroll="true"
		data-bs-backdrop="false" tabindex="-1" id="product"
		aria-labelledby="offcanvasScrollingLabel">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title" id="offcanvasScrollingLabel">제품</h5>
		</div>
		<ul>
			<li>
				<p data-bs-toggle="collapse" href="#juice_maker" role="button"
					aria-expanded="false" aria-controls="juice_maker">
					착즙기
				</p>
				<div class="depths_2 collapse" id="juice_maker" data-bs-parent="#product"> <!-- 이거도 쿼리문 생성해서 반복문으로 받아주는게 좋을듯 -->
					<ul>
					<%
						ProductDAO Jdao = new ProductDAO(application);
						List juice_maker = Jdao.totalCount("juice_maker");
						for (int i = 0; i< juice_maker.size(); i++){
							ProductDTO b = (ProductDTO) juice_maker.get(i);
							%><li><a href="../pages/Goods.jsp?product_num=<%=b.getProduct_num()%>"><%=b.getProduct_name()%></a></li><%
							if (i == 4){
								%><li><a href="../pages/Allproduct.jsp?p_cat=juice_maker">더 보기</a></li><%
								break;
							}
						}
						Jdao.close();
					%>
					</ul>
				</div>
			</li>
			<li>
				<p data-bs-toggle="collapse" href="#steam_pot" role="button"
					aria-expanded="false" aria-controls="steam_pot">스팀팟</p>
				<div class="depths_2 collapse" id="steam_pot"
					data-bs-parent="#product">
					<ul>
					<%
						ProductDAO Sdao = new ProductDAO(application);
						List steam_pot = Sdao.totalCount("steam_pot");
						for (int i = 0; i< steam_pot.size(); i++){
							ProductDTO b = (ProductDTO) steam_pot.get(i);
							%><li><a href="Goods.jsp?product_num=<%=b.getProduct_num()%>"><%=b.getProduct_name()%></a></li><%
							if (i == 4){
								%><li><a href="더보기링크">더 보기</a></li><%
								break;
							}
						}
						Sdao.close();
					%>

					</ul>
				</div>
			</li>
			<li>
				<p data-bs-toggle="collapse" href="#air_fryer" role="button"
					aria-expanded="false" aria-controls="air_fryer">
					<a href="에어프라이어 상품이 하나뿐">에어프라이어</a>
				</p>
			</li>
			<li>
				<p data-bs-toggle="collapse" href="#blender" role="button"
					aria-expanded="false" aria-controls="blender">블렌더</p>
				<div class="depths_2 collapse" id="blender"
					data-bs-parent="#product">
					<ul>
						<li><a href="해당상품">M100</a></li>
						<li><a href="해당상품">미니블렌더</a></li>
					</ul>
				</div>
			</li>
			<li>
				<p data-bs-toggle="collapse" href="#tea_maker" role="button"
					aria-expanded="false" aria-controls="tea_maker">티전문가전</p>
				<div class="depths_2 collapse" id="tea_maker"
					data-bs-parent="#product">
					<ul>
					<%
						ProductDAO Tdao = new ProductDAO(application);
						List tea_maker = Tdao.totalCount("tea_maker");
						for (int i = 0; i< tea_maker.size(); i++){
							ProductDTO b = (ProductDTO) tea_maker.get(i);
							%><li><a href="Goods.jsp?product_num=<%=b.getProduct_num()%>"><%=b.getProduct_name()%></a></li><%
							if (i == 4){
								%><li><a href="더보기링크">더 보기</a></li><%
								break;
							}
						}
						Tdao.close();
					%>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="offcanvas offcanvas-top" data-bs-scroll="true"
		data-bs-backdrop="false" tabindex="-1" id="comunity"
		aria-labelledby="offcanvasScrollingLabel">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title" id="offcanvasScrollingLabel">커뮤니티</h5>
		</div>
		<div class="offcanvas-body">
			<ul>
				<li><a href="">이벤트</a></li>
				<li><a href="../recipe/main.do">나만의 레시피</a></li>
				<li><a href="">공식 인스타그램</a></li>
			</ul>
			
			<div class="imageSet">
                <img src="../image/header_img/286228903_1067065307251733_8529393103075981183_n.jpg" alt="" style="width: auto"; height="100%">
                <img src="../image/header_img/288137926_1729399867427509_4851823302726217234_n.jpg" alt="" style="width: auto"; height="100%">
                <img src="../image/header_img/d81bff6c04de2bb6ba54a38d48ac55fe_96379.png" alt="" style="width: auto"; height="100%">
            </div>
			
		</div>
	</div>
	<div class="offcanvas offcanvas-top" data-bs-scroll="true"
		data-bs-backdrop="false" tabindex="-1" id="service"
		aria-labelledby="offcanvasScrollingLabel">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title" id="offcanvasScrollingLabel">고객센터</h5>
			<button type="button" class="btn-close text-reset"
				data-bs-dismiss="offcanvas" aria-label="Close"></button>
		</div>
		<div class="offcanvas-body">
			<ul>
				<li><a href="../pages/manual.jsp">메뉴얼 동영상</a></li>
				<li><a href="../mvc2/listQNA.do">1:1 질문</a></li>
				<li><a href="../mvc2/listAS.do">a/s 부품신청</a></li>
				<li><a href="../mvc2/list.do">고객센터</a></li>
				
			</ul>
		</div>
	</div>	
	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">로그인</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <form action="../membership/login.do" method="post">
		      <div class="modal-body">
		      	<ul>
		      		<li>
		      			<label><p>아이디</p><input type="text" name="user_id"></label>
		      		</li>
		      		<li>
		      			<label><p>비밀번호</p><input type="password" name="user_pw"></label>
		      		</li>
		      	</ul>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		        <input type="submit" class="btn btn-primary" value="로그인">
		      </div>
	      </form>
	    </div>
	  </div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>