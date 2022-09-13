<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/mypage.css">
</head>
<body>
<jsp:include page="../pages/Header.jsp"></jsp:include>

	<div class="my_wrap">
		<header class="mypage_top"></header>         
		   
        <h3><input type="text" name="name" style="width:150px; border:none;" value="${dto.membername }" class="myh3">님의 마이페이지</h3>   
         
        <div class="my_con">        	

            <div class="my_list">
                <p><a href="">장바구니</a></p>                    
                <p><a href="">주문내역</a></p>                               
                <p><a href="">1:1 문의</a></p>
                <p><a href="">사용문의</a></p>
                <p><a href="">사용후기</a></p>
                <p><a href="../mypageCon/userEdit.do">회원정보수정</a></p>                
                <p><a href="../mypageCon/userDel.do">회원탈퇴</a></p>
            </div>
            
            <div class="my_content">
                <div class="myCon_text">
                    <div>
                        <p>주문내역 <span><a href="">+</a></span></p>                        
                        <hr>
                        <div>주문내역이 없음</div>                       
                        
                    </div>
                    
                    <div>
                        <p>1:1 상담 <span><a href="../mvc2/listQNA.do">+</a></span></p>

                        <hr>					     
					     <table width="90%">
					     
					     <!-- 게시물이 없으면 -->
					     <c:choose>    	
					     	<c:when test="${empty boardLists}">
					     		<tr>
					     			<td colspan="6" align="center">등록된 게시물이 없습니다</td>
					     		</tr>
					     	</c:when>
					     	
					     	<c:otherwise>
					     		<c:forEach items="${boardLists}" var="row" varStatus="loop">
					     			<tr align="center">
					     				<%-- <td>     					
					     					${map.totalCount - (((map.pageNum-1)*map.pageSize) +loop.index)}
					     				</td> --%>
					     				<td>
					     					<!--상세보기1. 제목을 클릭하면  -->

					     					<a href="../mvc2/view.do?idx=${ row.idx }">${ row.title }</a>

					     				</td>
					     				<td>${row.name}</td>
					     				<td>${row.visitcount}</td>
					     				<td>${row.postdate}</td>
					     				<td>
					     					<c:if test="${not empty row.ofile}">
					     						<a href="../model2/download.do?ofile=${row.ofile }&sfile=${row.sfile}&idx=${row.idx}">[down]</a>
					     					</c:if>
					     				</td>
					     			</tr>
					     		</c:forEach>
					     	</c:otherwise>
					     </c:choose>
					     </table>

                    </div>
                    

                </div>
                
                <div class="myCon_text">
                    <div>
                        <p>장바구니 <span><a href="">+</a></span></p>
                        <hr>
                        <div>장바구니 없음</div>  
                    </div>
                                  
                    <div>

                        <p>A/S 신청 <span><a href="../mvc2/listAS.do">+</a></span></p>

                        <hr>
                         <table width="90%">
					     
					     <!-- 게시물이 없으면 -->
					     <c:choose>    	
					     	<c:when test="${empty boardListsc}">
					     		<tr>
					     			<td colspan="6" align="center">등록된 게시물이 없습니다</td>
					     		</tr>
					     	</c:when>
					     	
					     	<c:otherwise>
					     		<c:forEach items="${boardListsc}" var="row" varStatus="loop">
					     			<tr align="center">
					     				<%-- <td>     					
					     					${mapc.totalCountc - (((mapc.pageNumc-1)*mapc.pageSizec) +loop.index)}
					     				</td> --%>
					     				<td>
					     					<!--상세보기1. 제목을 클릭하면  -->

					     					<a href="../mvc2/view2.do?idx=${ row.idx }">${ row.title }</a>

					     				</td>	
					     				<td>${row.postdate}</td>				     				
					     			</tr>
					     		</c:forEach>
					     	</c:otherwise>
					     </c:choose>
					     </table>
                    </div>                    
                </div>  
          
            </div>
        </div>

	</div>
<jsp:include page="../pages/Footer.jsp"></jsp:include>        

</body>
</html>