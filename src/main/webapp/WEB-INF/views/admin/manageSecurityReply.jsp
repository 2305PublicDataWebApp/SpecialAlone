<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<link rel="stylesheet" href="/resources/css/reset.css">
	    <link rel="stylesheet" href="/resources/css/header.css">
	    <link rel="stylesheet" href="/resources/css/footer.css">
	    <link rel="stylesheet" href="/resources/css/admin/manageBoard.css">
	    <link rel="stylesheet" href="/resources/css/admin/main.css">
		<script src="https://kit.fontawesome.com/dbb376a4c5.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<title>��� ����</title>
	</head>
	<body>
		<!-- header -->
        <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
        <!-- main -->
        <main>
        	<div id="main_left">
                <table>
                    <tr>
                        <td><a href="/member/list.do">ȸ������</a></td>
                    </tr>
                    <tr>
                        <td><a href="/singo/list.do">�Ű�ȸ��</a></td>
                    </tr>
                    <tr>
                        <td><a href="/manageBoard.do?selectedValue=notice">�Խñ�/���� ����</a></td>
                    </tr>
                    <tr>
                        <td style="background-color: black;"><a href="/manageReply.do?selectedValue=hobby" style="color:white;">��� ����</a></td>
                    </tr>
                </table>
            </div>
            <div id="main_middle">
            	<h2><b>��� ����</b></h2>
                <hr>
                <div class="search">
                	<select id="boardType">
                		<option value="hobby">��� ���</option>
                		<option value="security" selected>���� ���</option>
                	</select>
                	<br>
                    <form action="/manageReply/search.do" method="get">
                    	<input type="hidden" name="selectedValue" value="security">
						<input type="text" name="searchKeyword" placeholder="�ۼ��ڸ� �Է��ϼ���." style="width:30%">
						<button id="findProduct" type="submit"><i class="fa-solid fa-magnifying-glass" style="color: blue;"></i></button>
					</form>	
                </div>
	            <table class="table table-striped table-hover">
	            		<colgroup>
							<col width="10%"></col>
							<col width="40%"></col>
							<col width="20%"></col>
							<col width="10%"></col>
						</colgroup>
	                    <thead>
	                        <tr style="text-align:center">
	                            <th>��ȣ</th>
	                            <th>����</th>
	                            <th>�ۼ���</th>
	                            <th>����</th>
	                        </tr>
	                    </thead>
	                    <tbody class="table-group-divider">
							<c:forEach var="reply" items="${sList}">
								<tr>
									<td style="text-align:center">${reply.sCommentNo }</td>
									<td><a href="/noticeEvent/detail.do?boardNo=">${reply.sCommentContent }</a></td>
									<td style="text-align:center">${reply.userNo }</td>
									<td style="text-align:center">
										<button onclick="if (confirm('���� �Ͻðڽ��ϱ�?')) { location.href = '/noticeEvent/delete.do?boardNo=${reply.hReplyNo }'; window.location.href = window.location.href; }">����</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr align="center">
								<td colspan="4">
								<c:if test="${pInfo.currentPage != 1 }">
									<a href="/manageReply.do?selectedValue=security&currentPage=${pInfo.currentPage - 1 }">&lt;</a>&nbsp;
								</c:if>
								<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
									<c:url var="pageUrl" value="/manageReply.do">
										<c:param name="selectedValue" value="security"></c:param>
										<c:param name="currentPage" value="${p }"></c:param>
									</c:url>
									<c:if test="${pInfo.currentPage == p }"><a href="${pageUrl }" style="color:black;">${p }</a>&nbsp;</c:if>
									<c:if test="${pInfo.currentPage != p }"><a href="${pageUrl }">${p }</a>&nbsp;</c:if>
								</c:forEach>
								<c:if test="${pInfo.currentPage ne pInfo.naviTotalCount }">
									<a href="/manageReply.do?selectedValue=security&currentPage=${pInfo.currentPage + 1 }">&gt;</a>
								</c:if>
								</td>
							</tr>
						</tfoot>
	            </table>
            </div>
        	<div id="main_right"></div>
        </main>
        <!-- footer -->
        <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
        <script>
        	const selectElement = document.getElementById("boardType");
        	selectElement.addEventListener("change", function() {
        		const selectedValue = selectElement.value;
				location.href="/manageReply.do?selectedValue="+selectedValue;
        	});
        	
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	</body>	
</html>