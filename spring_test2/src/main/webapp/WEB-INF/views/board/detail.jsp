<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div  class="container-md">
<h1>Board detail page</h1>
 <c:set value="${bdto.bvo}" var="bvo"></c:set> 

 	<div class="mb-3">
  		<label for="n" class="form-label">bno</label>
  		<input type="text" class="form-control" name="bno" id="n" value="${bvo.bno }" readonly="readonly" placeholder="bno..">
	</div>
 	<div class="mb-3">
  		<label for="t" class="form-label">title</label>
  		<input type="text" class="form-control" name="title" id="t" value="${bvo.title }"  readonly="readonly" placeholder="title..">
	</div>
	
		<div class="mb-3">
  			<label for="w" class="form-label">writer</label>
  			<input type="text" class="form-control"  name="writer" id="w" value="${bvo.writer }" readonly="readonly" placeholder="writer.....">
		</div>
		<div class="mb-3">
  		<label for="t" class="form-label">reg_date</label>
  		<input type="text" class="form-control" name="reg_date" id="r" value="${bvo.regDate }" readonly="readonly" placeholder="reg_date..">
	</div>
	<div class="mb-3">
 	 <label for="c" class="form-label">content</label>
  	<textarea class="form-control"  id="c"  name="content"   aria-label="With textarea" readonly="readonly">${bvo.content }</textarea>
	</div>
	
   	<!-- file uplad 표시라인 -->
	<c:set value="${bdto.flist }" var="flist"></c:set>
	<div class="mb-3">
	<ul class="list-group list-group-flush">
	<!-- 파일 개수만큼 li를 반복하여 파일 표시 타입이 1인경우만 표시  -->
	<!-- =>div>파일이름 작성일 span size -->
	<c:forEach items="${flist }" var="fvo">

  <li class="list-group-item">
  		<c:choose>
  			<c:when test="${fvo.fileType>0 }">
  				<div>
  					<img alt="" src="/up/${fvo.saveDir}/${fvo.uuid }_${fvo.fileName}">
  				</div>
  			</c:when>
  			<c:otherwise>
  				<div>
  					<!-- 파일타입이 0인경우 아이콘 모양하나 가져와서 넣기 -->
  					<a href="/up/${fvo.saveDir}/${fvo.uuid }_${fvo.fileName}" download="${fvo.fileName}">
  					<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-file-earmark-arrow-down" viewBox="0 0 16 16">
  						<path d="M8.5 6.5a.5.5 0 0 0-1 0v3.793L6.354 9.146a.5.5 0 1 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0-.708-.708L8.5 10.293z"/>
  						<path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2M9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5z"/>
					</svg>	
  					</a>
  				</div>
  			</c:otherwise>
  		</c:choose>
  		 <div>
 			<!-- 파일이름 작성일 -->
			<div>${fvo.fileName }</div>
			${fvo.regDate }
			<span class="badge rounded-pill text-bg-warning">${fvo.fileSize }Byte</span>  		
  		</div>
  </li> 
	</c:forEach>

  </ul>
	</div> 
	
	<br>
	<hr>
	 <sec:authorize access="isAuthenticated()">
	 <sec:authentication property="principal.uvo.nickName" var="authNick"  />
	 </sec:authorize>
	<!-- comment line -->
	<!-- 댓글등록라인 -->
	<div class="input-group mb-3">
  		<span class="input-group-text" id="cmtWriter">
  		<c:if test="${authNick eq null }">
  		<span class="input-group-text" id="cmtWriter">손님</span>
  		</c:if>
  		${authNick }
  		</span>
  		<input type="text" id="cmtText" class="form-control" placeholder="Add Comment..." aria-label="Username" aria-describedby="basic-addon1">
  		<button type="button" id="cmtAddBtn" class="btn btn-secondary">post</button>
	</div>
	
	<!-- 출력하는 라인 -->
	<ul class="list-group list-group-flush" id="cmtListArea">
  		<li class="list-group-item">
  			<div class="input-group mb-3">
  				<div class="fw-bold">Writer</div>
  				content
  			</div>
  			<span class="badge rounded-pill text-bg-warning">regDate</span>
  		</li>
	</ul>
	
	<!-- 댓글 더보기 버튼  -->
	<div>
		<button type="button" id="moreBtn" data-page="1" class="btn btn-dark" style="visibility:hidden"> MOER + </button>
	</div>
	<!-- 모달창 라인 -->
	<div class="modal" id="myModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Writer</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <input type="text" class="form-control" id="cmtTextMod">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="cmtModBtn"  data-bs-dismiss="modal">modify</button>
      </div>
    </div>
  </div>
</div>

<br>
<hr>
 
	
	
	
<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-warning">수정</button></a>
<a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-danger">삭제</button></a>
	
<a href="/board/list"><button type="button" class="btn btn-primary">list</button></a>
 
</div>
 <script type="text/javascript">
 	const bnoVal=`<c:out value="${bvo.bno}"/>`
 	/* const id=`<c:out value="${ses.id}"/>` */
 </script>
 
<script type="text/javascript" src="/re/js/boardDetailComment.js"></script>

<script type="text/javascript">
//화면에 뿌리기
                 spreadCommentList(bnoVal);
</script>

 <jsp:include page="../layout/footer.jsp"></jsp:include>
    