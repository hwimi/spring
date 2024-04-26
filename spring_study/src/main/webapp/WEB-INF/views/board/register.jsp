<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div  class="container-md">
<h1>Board register page</h1>
 <form action="/board/insert" method="post">
 	<div class="mb-3">
  		<label for="t" class="form-label">title</label>
  		<input type="text" class="form-control" name="title" id="t" placeholder="title...">
	</div>
	
		<div class="mb-3">
  			<label for="w" class="form-label">writer</label>
  			<input type="text" class="form-control"  name="writer" id="w" value="${ses.id }" placeholder="writer...">
		</div>
		
	<div class="mb-3">
 	 <label for="c" class="form-label">content</label>
  	<textarea class="form-control"  id="c" name="content" aria-label="With textarea"></textarea>
	</div>

<button type="submit" class="btn btn-primary">등록</button>
 </form>
 <jsp:include page="../layout/footer.jsp"></jsp:include>
</div>
