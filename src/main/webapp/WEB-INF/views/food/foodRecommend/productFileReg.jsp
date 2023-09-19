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
        <link rel="stylesheet" href="/resources/css/food/product/productFileReg.css">
	</head>
	<body>
      <div class="container">
         <!-- header -->
            <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

         <!-- main -->
            <main>
            <section>
                <h1>추천상품 이미지등록</h1><br><hr><br>            
            <br><br>
            <form action="/foodProduct/fileregister.do" method="POST" enctype="multipart/form-data">
                <input type="submit" value="파일등록">
                <br><br><br>
                <div id="image-file-container">
                    <div id="image1" class="imageFile">
                        <div id="image_container1" class="image_thumbnail"></div>
                        <div id="image_reg_btn1" class = "image_reg_btn">
                            <input type="file" id="image1" name="imagename1"/>
                        </div>
                    </div>
                    <div id="image2" class="imageFile">
                        <div id="image_container2" class="image_thumbnail"></div>
                        <div id="image_reg_btn2" class = "image_reg_btn">
                            <input type="file" id="image2" name="imagename2"/>
                        </div>
                    </div>
                    <div id="image3" class="imageFile">
                        <div id="image_container3" class="image_thumbnail"></div>
                        <div id="image_reg_btn3" class = "image_reg_btn">
                            <input type="file" id="image3" name="imagename3"/>
                        </div>
                    </div>
                    <!-- 이미지 썸네일 -->
                </div>
            </form>
            </section>
            </main>

         <!-- footer -->
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
      </div>		
	</body>
</html>