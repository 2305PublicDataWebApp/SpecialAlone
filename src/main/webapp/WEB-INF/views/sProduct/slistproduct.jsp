<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 리스트</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
	<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css"> -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/security/productlist.css">
	<!-- <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
<main>	   
	<div class="container">
		<div class="left-ki">
			<div class="recently-viewed-products-title">
				<h3>최근 본 상품</h3>
			</div>
			<div class="recently-viewed-products">
				<div id="recentProductsList" class="recently-viewed-products-box"></div>
			</div>
		</div>

		<div class="middle">
			<div class="product-list">
				<h1>상품 리스트</h1>
			</div>
			<div class="product-list-box">
				<c:forEach var="Product" items="${pList}" varStatus="loop">
					<c:if test="${loop.index % 3 == 0}">
						<div class="product-card-row">
					</c:if>
	
						<div class="product-card">
							<c:url var="detailUrl" value="/product/sdetail.do">
								<c:param name="sProductId" value="${Product.sProductId }"></c:param>
							</c:url>
							<a href="${detailUrl }">
								<img src="${pageContext.request.contextPath}/resources/images/${Product.sFileReName}" class="product-img" alt="상품이미지">
							</a>
							<p>${Product.sProductName}</p>
							<strong class="price-value" style="color:rgb(248, 120, 120); font-size:18px; font-weight:bold;">${Product.sPrice } 원</strong>
							<div class="star-rating" data-rating="${Product.sProductAverageRating}"></div>
							<a href="${detailUrl }"class="btn btn-detail">상세 정보 보기</a>
						</div>
	
					<c:if test="${loop.index % 3 == 2 or loop.last}">
						</div>
					</c:if>
				</c:forEach>
			</div>
			
			<div class="pagination">
				<c:url var="prevUrl" value="/product/slistproduct.do">
				<c:param name="page" value="${pInfo.startNavi - 1 }"></c:param>
			</c:url>
			<c:if test="${pInfo.startNavi != 1 }">
				<a href="${prevUrl }">[이전]</a>
			</c:if>
			
			<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
				<c:url var="pageUrl" value="/product/slistproduct.do">
					<c:param name="page" value="${p }"></c:param>
				</c:url>
				<a href="${pageUrl }">${p }</a>&nbsp;
			</c:forEach>
			
			<c:url var="nextUrl" value="/product/slistproduct.do">
				<c:param name="page" value="${pInfo.endNavi + 1 }"></c:param>
			</c:url>
			<c:if test="${pInfo.endNavi != pInfo.naviTotalCount }">
				<a href="${nextUrl }">[다음]</a>
			</c:if>
		</div>
	</div>
	
	<div class="right-ki">
		<div class="search">
			<form action="/product/search.do" method="get">
				<select name="searchCondition">
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				<input type="text" name="searchKeyword" class="iText"placeholder="검색어를 입력하세요">
				<input type="submit" value="검색">
			</form>
		</div>
		<aside class="ad-container">
			<a href="https://kh-academy.co.kr/main/main.kh">
				<img src="${pageContext.request.contextPath}/resources/images/advertise.png" alt="Advertisement">
			</a>
		</aside>		
	</div>
	</div>
</main>




<jsp:include page="/WEB-INF/views/include/footer.jsp" />
<script>
	document.addEventListener("DOMContentLoaded", function () {
		var starRatingElements = document.querySelectorAll(".star-rating");

		starRatingElements.forEach(function (element) {
			var rating = parseFloat(element.getAttribute("data-rating"));
			element.innerHTML = generateStarRating(rating);
			displayRecentlyViewedProducts();

		});
	});
	function generateStarRating(rating) {
		var stars = "";
		for (var i = 1; i <= 5; i++) {
			if (i <= rating) {
				stars += '<i class="fa fa-star"></i>'; 
			} else {
				stars += '<i class="fa fa-star-o"></i>'; 
			}
		}
		return stars;
	}

	function displayRecentlyViewedProducts() {
		var recentlyViewedProducts = JSON.parse(localStorage.getItem('recentlyViewedProducts')) || [];
		var recentProductsList = document.getElementById('recentProductsList');

		var productListHTML = '';
		var itemCount = Math.min(2, recentlyViewedProducts.length);
		for (var i = 0; i < itemCount; i++) {
			var item = recentlyViewedProducts[i];

		//recentlyViewedProducts.forEach(function (item) {
						//var item = recentlyViewedProducts[i];

//		    	let fileName = 11;
//		    	productListHTML += `<div class='col-md-3'>${fileName}</div>` 
		productListHTML += 
			'<div class="recent">\
				<div class="card">\
					<a href="/product/sdetail.do?sProductId='+item.sProductId+'"> <img src="${pageContext.request.contextPath}/resources/images/'+item.sFileReName+'" alt="${product.sProductName}"></a>\
					<div class="card-body">\
						<p class="card-title">'+item.sProductName+'</p>\
						<div class="star-rating" data-rating="'+item.sProductAverageRating+'"></div>\
						<strong><p class="card-text" style="color:red; font-size:15px; font-weight:bold;">'+item.sPrice+'원</p></strong>\
					</div>\
				</div>\
			</div>';
		}
		//});
	
		recentProductsList.innerHTML = productListHTML;
	}    
	document.addEventListener("DOMContentLoaded", function () {
		var starRatingElements = document.querySelectorAll(".star-rating");

		starRatingElements.forEach(function (element) {
			var rating = parseFloat(element.getAttribute("data-rating"));
			element.innerHTML = generateStarRating(rating);
			displayRecentlyViewedProducts();

		});
	});
	
	function addToRecentlyViewedProducts(product) {
		var recentlyViewedProducts = JSON.parse(localStorage.getItem('recentlyViewedProducts')) || [];

		var existingProductIndex = recentlyViewedProducts.findIndex(p => p.sProductId === product.sProductId);

		if (existingProductIndex === -1) {
			recentlyViewedProducts.unshift(product);
			if (recentlyViewedProducts.length > 5) {
				recentlyViewedProducts.pop(); 
			}

			localStorage.setItem('recentlyViewedProducts', JSON.stringify(recentlyViewedProducts));
		} else {
			
			var existingProduct = recentlyViewedProducts.splice(existingProductIndex, 1)[0];
			recentlyViewedProducts.unshift(existingProduct);

			localStorage.setItem('recentlyViewedProducts', JSON.stringify(recentlyViewedProducts));
		}
	}

	window.addEventListener('load', function () {
		displayRecentlyViewedProducts();
	});
</script>
</body>
</html>