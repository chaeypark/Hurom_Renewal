<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/index.css">
</head>
<body>
	<jsp:include page="./Header.jsp"></jsp:include>
	<jsp:include page="./slide.jsp"></jsp:include>
    <div class="index_wrap"> 
        <div class="com_int">
          <div class="row featurette ">
            <div class="col-md-5 com_int_left">
              <h2 class="featurette-heading fw-normal lh-1">글로벌 건강 가전 브랜드 <br> 즐거운 도전, 행복한 휴롬</h2>              
              <p class="com_int_logo"><img src="../image/index_img/hurom_logo.jpg" alt=""></p>
              
            </div>

            <div class="col-md-5 com_int_right">
              <h2 class="featurette-heading fw-normal lh-1">휴롬의 비전</span></h2>
              
              <p>
                휴롬은 사람에게 이로움을 주는 기업입니다. <br>
                그 중에서도 사람들의 건강을 위한 기술과 제품으로 기업의 철학을 실현합니다. <br>
                따라서 휴롬은 천연 재료를 직접 요리해 건강한 식습관을 가질 수 있도록 <br>
                건강주스기 '휴롬'을 비롯한 다양한 건강가전과 100% 홈메이드 휴롬주스를 통해 <br>
                인류의 건강과 행복에 기여할 것입니다.
              </p>
            </div>
          </div>          
        </div>     <!-- 회사 소개 끝 -->       
        
        <div class="card border-0">
          <div class="card-body index_hr_green">
          	<h3>휴롬의 소비자중심경영</h3>            
            <h5>              
              쉽고 편리한 제품으로 <br>
              고객의 건강한 미래를 디자인합니다              
            </h5>
          </div>
        </div>

        <div class="index_item">              

          <div class="card border-0 mb-3  ">
            <div class="row g-0 ">
              <div class="col-md-5 order-md-1">
                <img src="../image/index_img/1000000222_detail_030.png" class="img-fluid rounded-start" alt="...">
              </div>
              <div class="col-md-7">
                <div class="card-body order-md-2">
                  <h5 class="card-title">휴롬 프라임 착즙기</h5>
                  <p class="card-text">
                    휴롬만의 영양소 파괴를 최소화한 세계 최초 <br>
                    저속압착기술(SST) <br>
                    * 세계 최초 저속 착즙 기술 (특 제 10-0755440호)
                  </p>
                  <p class="card-text"><a href="#">착즙기 더보기</a></p>
                </div>
              </div>
            </div>
          </div>

          <div class="card border-success border-end-0 border-start-0 mb-3" >
            <div class="row g-0">
              <div class="col-md-5">
                <img src="../image/index_img/1000000082_magnify_048.png" class="img-fluid rounded-start" alt="...">
              </div>
              <div class="col-md-7">
                <div class="card-body">
                  <h5 class="card-title">휴롬 티마스터</h5>
                  <p class="card-text">
                    맛과 향을 좌우하는 최적의 온도와 시간 <br>
                    찻잎이 우러나는 색감을 만끽하기 좋은 유리재질
                  </p>
                </div>
              </div>
            </div>
          </div>

          <div class="card border-0 mb-3">
            <div class="row g-0">
              <div class="col-md-5 order-md-1">
                <img src="../image/index_img/1000000106_detail_041.png" class="img-fluid rounded-start" alt="..." >
              </div>
              <div class="col-md-7" >
                <div class="card-body order-md-2">
                  <h5 class="card-title">휴롬 퀵스퀴저</h5>
                  <p class="card-text">
                    지친 일상의 활기찬 에너지 충전! <br>
                    쉽고 빠른 착즙
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div><!--상품소개-->

        <hr>

        <div class="index_event">
          <div class="eve_title">이벤트</div>
          <div class="eve_line"></div>
          <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col">
              <div class="card">
                <img src="../image/index_img/event4f41e3517ba17253.png" class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">Card title</h5>
                  <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card">
                <img src="../image/index_img/event72e61934beb47bf1.png" class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">Card title</h5>
                  <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card">
                <img src="../image/index_img/event40a8191e5737a518.png" class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">Card title</h5>
                  <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content.</p>
                </div>
              </div>
            </div>            
          </div>
        </div>
      </div><!--본문 끝-->
   <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>