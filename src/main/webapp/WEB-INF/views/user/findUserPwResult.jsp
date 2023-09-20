<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
      	<link rel="stylesheet" href="/resources/css/reset.css">
      	<link rel="stylesheet" href="/resources/css/header.css">
      	<link rel="stylesheet" href="/resources/css/footer.css">
      	<link rel="stylesheet" href="/resources/css/user/findUserPwResult.css">
		<title>SpecialAlone 비밀번호 찾기 결과</title>
	</head>
	<body>
		<div class="container">
		<!-- header -->
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
			
		<!-- main -->
		<main>
			<div class="container-main">
				<section>
					<h1>Special Alone</h1><br>
					<h3>더 특별한 혼자가 되기를 바랍니다.</h3><br>
					<h2>비밀번호 찾기 결과</h2><br><br><br>
					<div class="findUserPwArea">
						<c:if test="${not empty userPw}">
							<p>회원 비밀번호 : ${userPw["USER_PW"]}</p><br><br><br>
						</c:if>
						<c:if test="${empty userPw}">
							<p>입력하신 아이디 또는 이메일로</p>
							<p>가입된 아이디가 없습니다</p><br><br><br>
						</c:if>
						<a href="/user/login.do" class="button">로그인 하러가기</a>
					</div>
				</section>
			</div>
		</main>
			
		<!-- footer -->
			<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		</div>
	</body>
</html>