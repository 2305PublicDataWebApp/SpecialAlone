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
                <h2>${diner.fDinerType }</h2>
                <h3>${diner.fDinerName }</h3>
                <br>       
                <br><br>
			<div class="product_list">
                <!-- 상품 리스트를 반복하여 출력합니다 -->
                <c:forEach var="dinerRevSet" items="${dinerRevSet}">
                    <div class="product_item">
                        <div class="image_thumbnail">
                            <img src="${dinerRevSet.dRevFileList[0].fDinerRevFilepath}" alt="${dinerRevSet.dRevFileList[0].fDinerRevFilename}" >
                        </div>
                        <div class="image_thumbnail">
                            <img src="${dinerRevSet.dRevFileList[1].fDinerRevFilepath}" alt="${dinerRevSet.dRevFileList[1].fDinerRevFilename}" >
                        </div>
						<div id="product_item2">
							<!-- 작성자와 별점 표시 -->
							<span>아이디: ${dinerRevSet.dinerRev.fUserId}</span> 
							<span>제목: ${dinerRevSet.dinerRev.fDinerRevTitle}</span> 
							<span>${dinerRevSet.dinerRev.fDinerRevStar}</span><br>
							<br> <br>
							<!-- 리뷰 내용 표시 -->
							<p>${dinerRevSet.dinerRev.fDinerRevContent}</p>							
						</div>
                    </div>
                </c:forEach> 
              </div>  
                <br><hr>
                <br><br>
				
			<div class="pagination">
				<c:if test="${dinerRevSet.size() > 0}">
				    <ul>
				        <c:if test="${pInfo.startNavi > 1}">
				            <li><a href="/diner/revList.do?page=1">처음</a></li>
				        </c:if>
				        <c:if test="${pInfo.currentPage > 1}">
				            <li><a href="/diner/revList.do?page=${pInfo.currentPage - 1}">이전</a></li>
				        </c:if>
				        <c:forEach begin="${pInfo.startNavi}" end="${pInfo.endNavi}" var="i">
				            <c:if test="${pInfo.currentPage == i}">
				                <li class="active"><span>${i}</span></li>
				            </c:if>
				            <c:if test="${pInfo.currentPage != i}">
				                <li><a href="/diner/revList.do?page=${i}">${i}</a></li>
				            </c:if>
				        </c:forEach>
				        <c:if test="${pInfo.currentPage < pInfo.naviTotalCount}">
				            <li><a href="/diner/revList.do?page=${pInfo.currentPage + 1}">다음</a></li>
				        </c:if>
				        <c:if test="${pInfo.endNavi < pInfo.naviTotalCount}">
				            <li><a href="/diner/revList.do?page=${pInfo.naviTotalCount}">끝</a></li>
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