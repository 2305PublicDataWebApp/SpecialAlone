<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>추천상품 상세정보</title>
		<link rel="stylesheet" href="/resources/css/common/footer.css">
        <link rel="stylesheet" href="/resources/css/common/header.css">
        <link rel="stylesheet" href="/resources/css/common/reset.css">
        <link rel="stylesheet" href="/resources/css/food/product/productDetail.css">
	</head>
	<body>
      <div class="container">
         <!-- header -->
            <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
         <!-- main -->
        <main>
            <section>                
                <h1>상품 상세정보</h1><br><hr><br>
                <button onclick="deleteProduct(${foodProduct.fProductId})">상품정보 삭제</button>  
                <button onclick="toModifyForm(${foodProduct.fProductId})">상품정보 수정</button>                 
                <h2>${foodProduct.fProductCompany }</h2>
                <h3>${foodProduct.fProductName }</h3>
                <br>       
                <br><br>
                <div id="product_list_first" class="product_list">
                    <div id="product_item1" class="product_item">
                        <div id="image_container1" class="image_thumbnail">
                            <img src="${fPFileList[0].fProductFilepath }" alt=""${fPFileList[0].fProductFilerename }">
                        </div>
                    </div>
                    <div id="product_item2" class="product_item">
                        <div id="image_container2" class="image_thumbnail">
                            <img src="${fPFileList[1].fProductFilepath }" alt="${fPFileList[1].fProductFilerename }">
                        </div>
                    </div>
                    <div id="product_item3" class="product_item">
                        <div id="image_container3" class="image_thumbnail">
                            <img src="${fPFileList[2].fProductFilepath }" alt="${fPFileList[2].fProductFilerename }">
                        </div>
                    </div>
                </div>
                <br><br>
                <hr>
                <a href="/foodProduct/photoRevInfoRegister.do?fProductId=${foodProduct.fProductId}">리뷰작성</a>
                <br><br>
                <div id="product_detail_container">
                    <div id="product_recommendation">
                        <span>상세정보</span> <br><br><br>
                        <span>상품 추천 이유</span>
                        <br><br><br>
                        <p>${foodProduct.fProductDetail }</p>
                        <br><br><br><br><br><br><br>
                        <button>판매사이트로 이동</button>
                    </div>
                    <div id="product_starRev">
                        <span>추천점수 : 3.7/5.0</span><br><br>
                        <span>한줄평 및 별점</span>
                        <br><br>                        
                        <span>1.${fPOneRevList[0].fProductOneRevWriter} :</span>
                        <span>${fPOneRevList[0].fProductOneRevStar}</span><br><br><br> 
                        <span>2.${fPOneRevList[1].fProductOneRevWriter} :</span>
                        <span>${fPOneRevList[1].fProductOneRevStar}</span><br><br><br> 
                        <span>3.${fPOneRevList[2].fProductOneRevWriter} :</span>
                        <span>${fPOneRevList[2].fProductOneRevStar}</span><br><br><br>                        
                        <button onclick="toReviewList(${foodProduct.fProductId})">리뷰로 이동</button>
                    </div>
                </div>
                <div id="product_nutrient_info">
                    <h3>식품정보</h3>
                    <p>${foodProduct.fProductInfo }</p>
                </div>
                <br><br>
            </section>
        </main>  
         <!-- footer -->
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
      </div>
      <script>
		function toReviewList(fProductId) {
		    // 리뷰 목록 페이지 URL을 생성하고 상품 ID를 쿼리 문자열로 추가
		    var url = '/foodProduct/revlist.do?fProductId=' + fProductId;		
		    // 새로운 URL로 이동
		    window.location.href = url;
		}
		function toModifyForm(fProductId) {
		    // 리뷰 목록 페이지 URL을 생성하고 상품 ID를 쿼리 문자열로 추가
		    var url = '/foodProduct/modifyInfo.do?fProductId=' + fProductId;		
		    // 새로운 URL로 이동
		    window.location.href = url;
		}		
		function deleteProduct(fProductId){
			var confirmation = confirm("상품 정보를 삭제하시겠습니까?");
			if(confirmation){
				var url = '/foodProduct/deleteProduct.do?fProductId=' + fProductId;
				window.location.href = url;				
			}else{
				return;
			}			
		}
		</script>		
	</body>
</html>