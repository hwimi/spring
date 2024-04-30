<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div  class="container-md">
<h1>Board register page</h1>
 <form action="/board/insert" method="post" enctype="multipart/form-data">
 	<div class="mb-3">
  		<label for="t" class="form-label">title</label>
  		<input type="text" class="form-control" name="title" id="t" placeholder="title...">
	</div>
	
		<div class="mb-3">
  			<label for="w" class="form-label">writer</label>
  			<input type="text" class="form-control"  name="writer" id="w" value="${ses.id }"  readonly="readonly" placeholder="writer...">
		</div>
		
	<div class="mb-3">
 	 <label for="c" class="form-label">content</label>
  	<textarea class="form-control"  id="c" name="content" aria-label="With textarea"></textarea>
	</div>
	
	<!-- 파일 입력 라인 추가 -->
	<!-- multiple 하나의 여러개 파일 추가 가능  -->
	<div class="mb-3">
  			<label for="file" class="form-label">files..</label>
  			<input type="file" class="form-control"  name="files" id="file" multiple="multiple" style="display:none"> <br>
  			<button type="button" class="btn btn-info" id="trigger">fileUpload..</button> 
		</div>
		
		<!-- 파일목록 표시라인 -->
		<div class="mb-3" id="fileZone" >
		</div>
		
		
	
<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
 </form>
 <jsp:include page="../layout/footer.jsp"></jsp:include>
</div>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>