<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추천식당 목록</title>
<link rel="stylesheet" href="/resources/css/common/footer.css">
<link rel="stylesheet" href="/resources/css/common/header.css">
<link rel="stylesheet" href="/resources/css/common/reset.css">
<link rel="stylesheet" href="/resources/css/food/diner/dinerList.css">
</head>
<body>
	<div class="container">
		<!-- header -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

		<!-- main -->						
		<main>
			<section>
                <h1>식당 리뷰</h1><br><hr><br>                 
                <h2>지역</h2>
                <h3>식당명</h3>
                <br>       
                <br><br>
                <div id="product_list_first" class="product_list">
                    <div id="product_item1" class="product_item">
                        <div id="image_container1" class="image_thumbnail">
                            <img src="../CSS_Basic/images/link.png" alt="">
                        </div>
                        <div id="image_container2" class="image_thumbnail">
                            <img src="../CSS_Basic/images/link.png" alt="">
                        </div>
                    </div>
                    <div id="product_item2">
                        <span>아이디: khuser01</span>
                        <span>★ ★ ★ ★ ★</span><br><br><br>  <!-- 오른쪽으로 붙히기-->
                        <p>
                            리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        </p>
                    </div>
                </div>
                <br><br>
                <div id="product_list_first" class="product_list">
                    <div id="product_item1" class="product_item">
                        <div id="image_container1" class="image_thumbnail">
                            <img src="../CSS_Basic/images/link.png" alt="">
                        </div>
                        <div id="image_container2" class="image_thumbnail">
                            <img src="../CSS_Basic/images/link.png" alt="">
                        </div>
                    </div>
                    <div id="product_item2">
                        <span>아이디: khuser01</span>
                        <span>★ ★ ★ ★ ★</span><br><br><br>  <!-- 오른쪽으로 붙히기-->
                        <p>
                            리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        </p>
                    </div>
                </div>
                <div id="product_list_first" class="product_list">
                    <div id="product_item1" class="product_item">
                        <div id="image_container1" class="image_thumbnail">
                            <img src="../CSS_Basic/images/link.png" alt="">
                        </div>
                        <div id="image_container2" class="image_thumbnail">
                            <img src="../CSS_Basic/images/link.png" alt="">
                        </div>
                    </div>
                    <div id="product_item2">
                        <span>아이디: khuser01</span>
                        <span>★ ★ ★ ★ ★</span><br><br><br>  <!-- 오른쪽으로 붙히기-->
                        <p>
                            리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        </p>
                    </div>
                </div>
                <div id="product_list_first" class="product_list">
                    <div id="product_item1" class="product_item">
                        <div id="image_container1" class="image_thumbnail">
                            <img src="../CSS_Basic/images/link.png" alt="">
                        </div>
                        <div id="image_container2" class="image_thumbnail">
                            <img src="../CSS_Basic/images/link.png" alt="">
                        </div>
                    </div>
                    <div id="product_item2">
                        <span>아이디: khuser01</span>
                        <span>★ ★ ★ ★ ★</span><br><br><br>  <!-- 오른쪽으로 붙히기-->
                        <p>
                            리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        <br>리뷰내용~~~~~~~~~~~~~~~~~~~~~~~
                        </p>
                    </div>
                </div>
                <br>
                <div style="text-align: center;">
                    <span>이전 1  2  3  4  5 다음</span>
                </div>
                <br><hr>
                <br><br>
				
			<div class="pagination">
				<c:if test="${dinerSetList.size() > 0}">
				    <ul>
				        <c:if test="${pInfo.startNavi > 1}">
				            <li><a href="/diner/list.do?page=1">처음</a></li>
				        </c:if>
				        <c:if test="${pInfo.currentPage > 1}">
				            <li><a href="/diner/list.do?page=${pInfo.currentPage - 1}">이전</a></li>
				        </c:if>
				        <c:forEach begin="${pInfo.startNavi}" end="${pInfo.endNavi}" var="i">
				            <c:if test="${pInfo.currentPage == i}">
				                <li class="active"><span>${i}</span></li>
				            </c:if>
				            <c:if test="${pInfo.currentPage != i}">
				                <li><a href="/diner/list.do?page=${i}">${i}</a></li>
				            </c:if>
				        </c:forEach>
				        <c:if test="${pInfo.currentPage < pInfo.naviTotalCount}">
				            <li><a href="/diner/list.do?page=${pInfo.currentPage + 1}">다음</a></li>
				        </c:if>
				        <c:if test="${pInfo.endNavi < pInfo.naviTotalCount}">
				            <li><a href="/diner/list.do?page=${pInfo.naviTotalCount}">끝</a></li>
				        </c:if>
				    </ul>
				</c:if>

			</div>				
				<br>
				<br>
				<br>

			</section>
		</main>

		<!-- footer -->
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>

</body>
</html>