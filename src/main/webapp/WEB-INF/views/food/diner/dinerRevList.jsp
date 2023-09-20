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
							<button onclick="deleteReview(${dinerRevSet.dinerRev.fDinerRevId},${dinerRevSet.dinerRev.refFDinerId },'${dinerRevSet.dinerRev.fUserId }')">리뷰 삭제</button>					
						</div>
                    </div>
                </c:forEach> 
              </div>  
                <br><hr>
                <br><br>
				
				<div class="pagination">
				    <c:if test="${dinerRevSet.size() > 0}">
				        <ul>
				            <c:if test="${pInfo.startNavi != 1 }">
				                <c:url var="prevUrl" value="/diner/revList.do">
				                    <c:param name="fDinerId" value="${diner.fDinerId}" />
				                    <c:param name="page" value="${pInfo.startNavi - 1}" />
				                </c:url>
				                <a href="${prevUrl }">[이전]</a>
				            </c:if>
				            <c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
				                <c:url var="pageUrl" value="/diner/revList.do">
				                    <c:param name="fDinerId" value="${diner.fDinerId}" />
				                    <c:param name="page" value="${p }" />
				                </c:url>
				                <a href="${pageUrl }">${p }</a>&nbsp;
				            </c:forEach>
				            <c:if test="${pInfo.endNavi != pInfo.naviTotalCount }">
				                <c:url var="nextUrl" value="/diner/revList.do">
				                    <c:param name="fDinerId" value="${diner.fDinerId}" />
				                    <c:param name="page" value="${pInfo.endNavi + 1 }" />
				                </c:url>
				                <a href="${nextUrl }">[다음]</a>
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
	<script>
		function deleteReview(fDinerRevId,refFDinerId,fUserId) {
		    var deleteUrl = '/diner/deleteDinerRev.do?fDinerRevId=' + fDinerRevId + '&refFDinerId=' + refFDinerId;
		    console.log(deleteUrl);
		    var userId = '<%= (String)session.getAttribute("userId") %>';	    
		    // 삭제 요청 실행
			if (userId === fUserId) {
			        window.location.href = deleteUrl;
			    } else {
			        alert("본인 작성리뷰만 삭제 가능합니다!");
			    }		
		}
	</script>
</body>
</html>