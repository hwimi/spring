<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div  class="container-md">
<h1>Board detail page</h1>
 
 	<div class="mb-3">
  		<label for="n" class="form-label">bno</label>
  		<input type="text" class="form-control" name="bno" id="n" value="${bvo.bno }" readonly="readonly" placeholder="bno...">
	</div>
 	<div class="mb-3">
  		<label for="t" class="form-label">title</label>
  		<input type="text" class="form-control" name="title" id="t" value="${bvo.title }"  readonly="readonly" placeholder="title...">
	</div>
	
		<div class="mb-3">
  			<label for="w" class="form-label">writer</label>
  			<input type="text" class="form-control"  name="writer" id="w" value="${bvo.writer }" readonly="readonly" placeholder="writer...">
		</div>
		<div class="mb-3">
  		<label for="t" class="form-label">reg_date</label>
  		<input type="text" class="form-control" name="reg_date" id="r" value="${bvo.reg_date }" readonly="readonly" placeholder="reg_date...">
	</div>
	<div class="mb-3">
 	 <label for="c" class="form-label">content</label>
  	<textarea class="form-control"  id="c"  name="content"   aria-label="With textarea" readonly="readonly">${bvo.content }</textarea>
	</div>
	
	<br>
	<hr>
	
	<!-- comment line -->
	<!-- 댓글등록라인 -->
	<div class="input-group mb-3">
  		<span class="input-group-text" id="cmtWriter">${ses.id }</span>
  		<input type="text" id="cmtText" class="form-control" placeholder="Add Comment..." aria-label="Username" aria-describedby="basic-addon1">
  		<button type="button" id="cmtAddBtn" class="btn btn-secondary">post</button>
	</div>
	
	<!-- 출력하는 라인 -->
<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        cno,writer,reg_date
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>댓글 내용 표시</strong>
      </div>
    </div>
  </div>
</div>
<br>
<hr>

	
	
	<c:if test="${ses.id eq bvo.writer }">
<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-warning">수정</button></a>
<a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-danger">삭제</button></a>
	</c:if>
<a href="/board/list"><button type="button" class="btn btn-primary">list</button></a>
 
</div>
 <script type="text/javascript">
 	const bnoVal=`<c:out value="${bvo.bno}"/>`
 	const id=`<c:out value="${ses.id}"/>`
 </script>
 

<script type="text/javascript" src="/resources/js/boardDetailComment.js">

</script>
<script type="text/javascript">
//화면에 뿌리기
                spreadCommentList(bnoVal);
</script>

 <jsp:include page="../layout/footer.jsp"></jsp:include>
    