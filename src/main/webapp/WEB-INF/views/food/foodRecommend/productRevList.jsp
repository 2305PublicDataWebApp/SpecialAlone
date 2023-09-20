<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/resources/css/common/footer.css">
        <link rel="stylesheet" href="/resources/css/common/header.css">
        <link rel="stylesheet" href="/resources/css/common/reset.css">
        <link rel="stylesheet" href="/resources/css/food/product/star.css">
        <link rel="stylesheet" href="/resources/css/food/product/productRevList.css">
        
</head>
<body>
	<main>
		<section>
			<h1>상품 리뷰</h1>
			<br>
			<hr>
			<br>
			<h2>${foodProduct.fProductCompany }</h2>
			<h3>${foodProduct.fProductName }</h3>
			<br> <br> <br>


			<!-- 상품 리뷰 목록 -->
			<c:forEach var="review" items="${foodProductRevSetList}">
				<div id="product_list_first" class="product_list">
					<div id="product_item1" class="product_item">
						<!-- 이미지 표시 -->
						<div id="image_container1" class="image_thumbnail">
							<img src="${review.FPPhotoRevFile[0].fProductRevFilepath}"
								alt="${review.FPPhotoRevFile[0].fProductRevFilename }">
						</div>
						<!-- 다른 이미지 추가 -->
						<div id="image_container2" class="image_thumbnail">
							<img src="${review.FPPhotoRevFile[1].fProductRevFilepath}"
								alt="${review.FPPhotoRevFile[1].fProductRevFilename }">
						</div>
					</div>
					<div id="product_item2">
						<!-- 작성자와 별점 표시 -->
						<span>아이디: ${review.FPPhotoRev.fUserId}</span> <span>제목:
							${review.FPPhotoRev.fProductRevTitle}</span> <span>${review.FPPhotoRev.fProductRevStar}</span><br>
						<br> <br>
						<!-- 리뷰 내용 표시 -->
						<p>${review.FPPhotoRev.fProductRevContent}</p>
					</div>
				</div>
			</c:forEach>

			<!-- 페이징 네비게이션 수정필요 -->
			<div class="pagination">
				<c:if test="${foodProductRevSetList.size() > 0}">
				    <ul>
				        <c:if test="${pInfo.startNavi > 1}">
				            <li><a href="/foodProduct/revlist.do?fProductId=${foodProduct.fProductId}&page=1">처음</a></li>
				        </c:if>
				        <c:if test="${pInfo.currentPage > 1}">
				            <li><a href="/foodProduct/revlist.do?fProductId=${foodProduct.fProductId}&page=${pInfo.currentPage - 1}">이전</a></li>
				        </c:if>
				        <c:forEach begin="${pInfo.startNavi}" end="${pInfo.endNavi}" var="i">
				            <c:if test="${pInfo.currentPage == i}">
				                <li class="active"><span>${i}</span></li>
				            </c:if>
				            <c:if test="${pInfo.currentPage != i}">
				                <li><a href="/foodProduct/revlist.do?fProductId=${foodProduct.fProductId}&page=${i}">${i}</a></li>
				            </c:if>
				        </c:forEach>
				        <c:if test="${pInfo.currentPage < pInfo.naviTotalCount}">
				            <li><a href="/foodProduct/revlist.do?fProductId=${foodProduct.fProductId}&page=${pInfo.currentPage + 1}">다음</a></li>
				        </c:if>
				        <c:if test="${pInfo.endNavi < pInfo.naviTotalCount}">
				            <li><a href="/foodProduct/revlist.do?fProductId=${foodProduct.fProductId}&page=${pInfo.naviTotalCount}">끝</a></li>
				        </c:if>
				    </ul>
				</c:if>

			</div>
			<h2>한줄리뷰</h2>
			<br> 
			<div>
		        <div class="review-input">
					<form id="reviewForm" action="/foodProduct/submitReview.do" method="post">
						<input type="hidden" id="refFProductId" name="refFProductId" value="${foodProduct.fProductId }">
				        <fieldset class="rate">
				            <input type="radio" id="rating10" name="fProductOneRevStar" value="5"><label for="rating10" title="5점"></label>
				            <input type="radio" id="rating9" name="fProductOneRevStar" value="4.5"><label class="half" for="rating9" title="4.5점"></label>
				            <input type="radio" id="rating8" name="fProductOneRevStar" value="4"><label for="rating8" title="4점"></label>
				            <input type="radio" id="rating7" name="fProductOneRevStar" value="3.5"><label class="half" for="rating7" title="3.5점"></label>
				            <input type="radio" id="rating6" name="fProductOneRevStar" value="3"><label for="rating6" title="3점"></label>
				            <input type="radio" id="rating5" name="fProductOneRevStar" value="2.5"><label class="half" for="rating5" title="2.5점"></label>
				            <input type="radio" id="rating4" name="fProductOneRevStar" value="2"><label for="rating4" title="2점"></label>
				            <input type="radio" id="rating3" name="fProductOneRevStar" value="1.5"><label class="half" for="rating3" title="1.5점"></label>
				            <input type="radio" id="rating2" name="fProductOneRevStar" value="1"><label for="rating2" title="1점"></label>
				            <input type="radio" id="rating1" name="fProductOneRevStar" value="0.5"><label class="half" for="rating1" title="0.5점"></label>
				        </fieldset> 
					    <input type="text" name="fProductOneRevContent" placeholder="한줄 리뷰를 작성해주세요!" style="width: 500px;">
					    <input type="submit" value="댓글등록">
					</form>
		        </div>						
			</div>
			<br>
			<br>
			<br>
			<table>
				<colgroup>
					<col width="5%">
					<col width="10%">
					<col width="75%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>작성자</th>
						<th>한줄리뷰</th>
						<th>별점</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="oneReview" items="${fPOneRevList}">
						<tr>
							<td>${oneReview.fProductOneRevNo}</td>
							<td>${oneReview.fProductOneRevWriter}</td>
							<td>${oneReview.fProductOneRevContent}</td>
							<td>${oneReview.fProductOneRevStar}</td>
						</tr>
					</c:forEach>
					 
				</tbody>
			</table>
		</section>
	</main>
</body>
</html>