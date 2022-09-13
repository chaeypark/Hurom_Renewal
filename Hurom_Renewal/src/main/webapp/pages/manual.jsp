<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="../css/manual.css">

</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>

    <main>
        <div class="manual_n_video"><p>메뉴얼&동영상</p></div>
        <!--픽토그램 아이콘 4가지-->
        <div class="icons">
            <div class="icons_frame"><a href="../mvc2/listJuicer.do">
                <img src="../image/customer_service/juicer.png" alt="juicer">
                <p>착즙전문가전</p>
            </a></div>
            <div class="icons_frame"><a href="./cooking_gadgets.jsp">
                <img src="../image/customer_service/chef.png" alt="cooking_gadgets">
                <p>요리전문가전</p>
            </a></div>
            <div class="icons_frame"><a href="../mvc2/listTM.do">
                <img src="../image/customer_service/herbal-tea.png" alt="teakettle">
                <p>티전문가전</p>
            </a></div>
            <div class="icons_frame"><a href="https://www.youtube.com/user/huromcokr">          
                <img src="../image/customer_service/youtube.png" alt="youtube_logo">
                <p>휴롬 유튜브 페이지</p>                      
            </a></div>
        </div>
    </main>
    <jsp:include page="./Footer.jsp"></jsp:include>
    <script src="../JS/manual.js"></script>
</body>
</html>