<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/common/footer.css">
<link rel="stylesheet" href="/resources/css/common/header.css">
<link rel="stylesheet" href="/resources/css/common/reset.css">
<link rel="stylesheet" href="/resources/css/food/diner/dinerDetail.css">
</head>
	<body>
      <div class="container">
         <!-- header -->
            <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
         <!-- main -->
        <main>
            <section>                
                <h1>상품 상세정보</h1><br><hr><br>
                                 
                <h2>${diner.fDinerType }</h2>
                <h3>${diner.fDinerName }</h3>
				<a href="/diner/showRevInfoRegForm.do?fDinerId=${diner.fDinerId}">리뷰 작성</a>
                <br>       
                <br><br>
                <div id="product_list_first" class="product_list">
                    <div id="product_item1" class="product_item">
                        <div id="image_container1" class="image_thumbnail">
                            <img src="${dFileList[0].fDinerFilepath }" alt="${dFileList[0].fDinerFilename }">
                        </div>
                    </div>
                    <div id="product_item2" class="product_item">
                        <div id="image_container2" class="image_thumbnail">
                            <img src="${dFileList[1].fDinerFilepath }" alt="${dFileList[1].fDinerFilename }">
                        </div>
                    </div>
                    <div id="product_item3" class="product_item">
                        <div id="image_container3" class="image_thumbnail">
                            <img src="${dFileList[2].fDinerFilepath }" alt="${dFileList[2].fDinerFilename }">
                        </div>
                    </div>
                </div>
                <br><br>
                <hr>
                <br><br>
                <div id="product_detail_container">
                    <div id="product_recommendation">
                        <h2>${diner.fDinerName }</h2><br><br>
                        <span>★ ★ ★ ★ ★</span>
                        <span>좋아요</span>
                    </div>
                    <div id="product_starRev">
                        <h2>대표리뷰</h2> <br><br>
                        <p>리뷰내용</p>
                        <!-- 리뷰 가지고오기 -->
                        <button onclick="toReviewList(${diner.fDinerId})">리뷰로 이동</button>
                    </div>
                </div>
                <br><br>
                <div id="product_detail_container">
                    <div id="product_recommendation">
                        <h2>상세정보</h2><br><br>
                        <p>${diner.fDinerNote }</p>
                    </div>
                    <div id="product_starRev">
                        <div id="menu_h2">
                            <h2>메뉴</h2> <br><br>
                        </div>
                        <div id="menu-images">
                            <div id="product_item1" class="product_item_menu">
                                <div id="image_container1" class="image_thumbnail">
                                    <img src="${dFileList[3].fDinerFilepath }" alt="${dFileList[3].fDinerFilename }">
                                </div>
                            </div>
                            <div id="product_item2" class="product_item_menu">
                                <div id="image_container2" class="image_thumbnail">
                                    <img src="${dFileList[4].fDinerFilepath }" alt="${dFileList[4].fDinerFilename }">
                                </div>
                            </div>
                            <div id="product_item3" class="product_item_menu">
                                <div id="image_container3" class="image_thumbnail">
                                    <img src="${dFileList[5].fDinerFilepath }" alt="${dFileList[5].fDinerFilename }">
                                </div>
                            </div>
                            <div id="product_item4" class="product_item_menu">
                                <div id="image_container4" class="image_thumbnail">
                                    <img src="${dFileList[6].fDinerFilepath }" alt="${dFileList[6].fDinerFilename }">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>   
         <!-- footer -->
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
      </div>	
	<script>
	function toReviewList(fDinerId) {
	    // 리뷰 목록 페이지 URL을 생성하고 상품 ID를 쿼리 문자열로 추가
	    var url = '/diner/revList.do?fDinerId=' + fDinerId;		
	    // 새로운 URL로 이동
	    window.location.href = url;
	}		
	</script>      
	</body>
</html>