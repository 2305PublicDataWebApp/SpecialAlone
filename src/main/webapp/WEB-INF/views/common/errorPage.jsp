<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>서비스 결과 실패</title>
	</head>
	<body>
		<script>
			alert("${requestScope.msg}");
			location.href = "${url}";
		</script>
	</body>
</html>