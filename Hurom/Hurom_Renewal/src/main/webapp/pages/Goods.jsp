<%@page import="product_option.OptionDAO"%>
<%@page import="product_option.OptionDTO"%>
<%@page import="java.util.List"%>
<%@page import="catalog.CatalogDAO"%>
<%@page import="catalog.CatalogDTO"%>
<%@page import="product.ProductDTO"%>
<%@page import="product.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String num = request.getParameter("product_num");
	ProductDAO dao = new ProductDAO(application); // DAO 객체 생성
	CatalogDTO cdto = new CatalogDTO(); // 카탈로그 DTO 생성
	OptionDTO odto = new OptionDTO(); // 옵션 DTO 생성
	ProductDTO dto = dao.productView(num);
	OptionDAO odao = new OptionDAO(application);
	List optionList = odao.Options(dto.getProduct_num());
	dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/Goods.css">
<!-- jquery 연결 -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>	
	<div class="content">
	<form>
        <div class="item_info">
            <div class="left_area">
                <img id="imgchg" class="image" src="../image/Main/<%=dto.getProduct_name()%>.webp" alt="">
                <ul class="smallImg">
				<%
					for (int i = 0; i<optionList.size(); i++){
						OptionDTO options = (OptionDTO)optionList.get(i);
						%><li><img src="../image/Sub/<%=dto.getProduct_name()%>/<%=dto.getProduct_name()%>_<%=options.getOption_name()%>.webp"></li><%
					}
				%>
                </ul>
            </div> <!-- 상단 왼쪽 이미지 -->
            
            <div class="right_area">
                <h3><%=dto.getProduct_name() %></h3>
                <span style="background-color: gray;"></span>
                <span style="background-color: black;"></span>
                <span class="color_bar_white"></span>
                <span style="background-color: red;"></span>
                <span style="background-color: beige;"></span>
                <hr>
                <table>
                    <tr>
                        <td class="point">정가</td>
                        <td><%=dto.getPrice() %>원</td>
                    </tr>
                    <tr>
                        <td class="point">판매가</td>
                        <td><strong style="font-size: 17px; font-weight:800;"><%=dto.getPrice()%></strong>원</td>
                    </tr>
                    <tr>
                        <td class="point">자체상품코드</td>
                        <td><%=dto.getProduct_name() %></td>
                    </tr>
                    <tr>
                        <td class="point">브랜드</td>
                        <td>휴롬</td>
                    </tr>
                    <tr>
                        <td class="point">제조사</td>
                        <td>(주)휴롬</td>
                    </tr>
                    <tr>
                        <td class="point">원산지</td>
                        <td>대한민국</td>
                    </tr>
                    <tr>
                        <td class="point">배송비</td>
                        <td class="effect">0원 / 주문시결제(선결제)
                                <button type="button" id="modal" onclick="dis()">조건별배송<img src="../image/AllProduct/btn_vertical_next.png" alt=""></button>
                                <div id="dis">
                                    <P>금액별배송비
                                        <img src="../image/AllProduct/btn_layer_close.png" alt="">
                                    </P>
                                    <div>
                                        <ul>
                                            <li>
                                                0원 이상 ~ 50,000원 미만
                                                <p>3,100원</p>
                                            </li>
                                            <li>
                                                50,000원 이상
                                                <p>0원</p>
                                            </li>
                                        </ul>
                                        <p class="del_charge">배송비 계산 기준 : 판매가 + 옵션가 + 추가상품가 + 텍스트옵션가 - 상품할인가 - 상품쿠폰할인가</p>
                                    </div>
                                </div>
                            <br>
                            택배
                        </td>
                    </tr>
                    <tr>
                        <td class="point">컬러</td>
                        <td>
                            <select id="selectOption" class="color">
                                <option disabled>=옵션:가격=</option>
									<%
									for (int i =0; i<optionList.size(); i++){
										OptionDTO options = (OptionDTO)optionList.get(i);
										%><option value = "<%=dto.getProduct_name()%>/<%=dto.getProduct_name()%>_<%=options.getOption_name()%>"><%=options.getOption_name()%>
										+<%=options.getPrice_change()%>원</option><%
									}
									%>
                            </select>
                        </td>
                    </tr>
                </table>
                <hr>
                <div class="but">
                    <button class="cart" type="button" onclick="toBasket(this)">장바구니</button>
                    <button class="buy" type="button" onclick="toPurchase(this)">구매하기</button>
                </div>
            </div> <!-- 오른쪽 소개 -->
        </div> <!-- item_info -->
        </form>
        <div class="section">
            <div id="detail">
                <ul>
                    <li class="point2"><a href="#detail" class="point2">상세정보</a></li>
                    <li><a href="#exchange">교환및반품</a></li>
                    <li><a href="#review">상품후기</a></li>
                    <li><a href="#qna">상품문의</a></li>
                </ul>
            </div>
            <div class="det">
				<%
				cdto.setProduct_num(num);
				CatalogDAO cdao = new CatalogDAO(application);
				List catalogList = cdao.getCatalog(cdto.getProduct_num());
				//모든 자료를 DB와 알기쉽게 연결하여 이후 추가와 수정을 쉽도록 했다.
				for (int i=0; i<catalogList.size();i++){
					int catalogNum = (int)catalogList.get(i);
					%> <img src="../image/Catalog/<%=dto.getProduct_name()%>/<%=catalogNum%>.webp"><%
				}
				%>
            </div>
                
            <div id="exchange">
                <ul>
                    <li><a href="#detail">상세정보</a></li>
                    <li class="point2"><a href="#exchange" class="point2">교환및반품</a></li>
                    <li><a href="#review">상품후기</a></li>
                    <li><a href="#qna">상품문의</a></li>
                </ul>
            </div>
            <div class="exc">
                <h4>교환 및 반품안내</h4>
                <p>
                    &lt;교환/반품이 가능한 경우&gt;<br>
                    - 배송된 상품이 주문 내역과 상이하거나 웹 상에서 제공된 정보와 상이함<br>
                    - 파손, 손상되거나 심하게 오염되어 있음<br>
                    - [방문판매에 관한 법]상 표시하여야 항 사항을 표시하지 않은 상태에서 주문이 이루어짐<br>
                    - 교환/반품이 불가능한 경우고객님의 책임 사유 있는 사유로 포장된 상품이 훼손된 경우<br>
                    - 고객님의 사용 또는 일부 소비에 의하여 상품 등의 가치가 현저히 감소한 경우(예 : 신선식품)<br>
                    - 전자상거래 등에서의 소비자보호에 관한 법률이 정하는 소비자 청약철회 제한 내용에 해당되는 경우.
                </p>
                <h4>환불 안내</h4>
                <p>
                    - 제품 이상시 100% 교환, 환불해드립니다. (배송비 본사부담)<br>
                    - 제품 구매 후, 교환 및 환불은 수령 후 7일 이내에 하셔야 합니다.<br>
                    - 고객 변심으로 인한 반품 및 환불 시, 배송비는 고객 부담입니다.<br>
                    - 환불은 반품되는 상품이 도착된 이후 처리해 드립니다.<br>
                    - 이 외 제품/교환.배송/환불에 관한 문의는 고객센터나 휴롬 쇼핑몰 문의 게시판을 이용해 주시기 바랍니다.
                </p>
                <h4>AS 안내</h4>
                <p>
                    - 소비자분쟁해결 기준(공정거래위원회 고시)에 따라 피해를 보상받을 수 있습니다.<br>
                    - A/S는 판매자에게 문의하시기 바랍니다.<br>
                    - 휴롬 고객센터 번호는 1544-7011입니다.
                </p>
                <h4>배송 안내</h4>
                <p>
                    <span style="font-weight: 600;">- 기본배송료는 3,000원이며, 5만원 이상 구매시 무료배송 됩니다.</span><br>
                    - 도서,산간 일부지역은 추가 배송비가 발생 할 수 있습니다.<br>
                    - 본 상품의 평균 배송일은 입금 완료 후 2일 내지 3일입니다.<br>
                    - 평일 12시전 결제완료된 주문은 대부분 당일 발송됩니다.<br>
                    - 제품 재고상황에 따라 발송하지 못한 주문건은 따로 연락드리겠습니다.
                </p>
            </div>

            <div id="review">
                <ul>
                    <li><a href="#detail">상세정보</a></li>
                    <li><a href="#exchange">교환및반품</a></li>
                    <li class="point2"><a href="#review" class="point2">상품후기</a></li>
                    <li><a href="#qna">상품문의</a></li>
                </ul>
            </div>
        
            <div id="qna">
                <ul>
                    <li><a href="#detail">상세정보</a></li>
                    <li><a href="#exchange">교환및반품</a></li>
                    <li><a href="#review">상품후기</a></li>
                    <li class="point2"><a href="#qna" class="point2">상품문의</a></li>
                </ul>
            </div>
        </div> <!-- section -->

    </div> <!-- content -->
	<form>
		<input type="hidden" name="product_name">
		<input type="hidden" name="option_name">
	</form>
	<jsp:include page="./Footer.jsp"></jsp:include>
	<script>
		function toBasket(){
				
		}
	</script>
	<script src="../JS/Goods.js"></script>
</body>
</html>