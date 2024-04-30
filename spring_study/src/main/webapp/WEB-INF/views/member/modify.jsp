<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div  class="container-md">
<form action="/member/modify" method="post">
<h1>modify page</h1>
 	<div class="mb-3">
  		<label for="i" class="form-label">ID</label>
  		<input type="text" class="form-control" name="id" readonly="readonly" value="${ses.id }" id="i" placeholder="ID...">
	</div>
		<div class="mb-3">
  		<label for="i" class="form-label">reg_date</label>
  		<input type="text" class="form-control" name="reg_date" readonly="readonly" value="${ses.reg_date }"  id="i" placeholder="ID...">
	</div>
		<div class="mb-3">
  		<label for="i" class="form-label">last_login</label>
  		<input type="text" class="form-control" name="last_login" readonly="readonly" value="${ses.last_login }" id="i" placeholder="ID...">
	</div>
	<div class="mb-3">
  		<label for="p" class="form-label">Password</label>
  		<input type="password" class="form-control" name="pw" id="p" placeholder="PW...">
	</div>
	<div class="mb-3">
  		<label for="n" class="form-label">Name</label>
  		<input type="text" class="form-control" name="name" id="n" value="${ses.name }"  placeholder="Name...">
	</div>
	<div class="mb-3">
  		<label for="e" class="form-label">E-mail</label>
  		<input type="email" class="form-control" name="email" id="e" value="${ses.email }" placeholder="E-mail...">
	</div>
	<div class="mb-3">
  		<label for="i" class="form-label">Home</label>
  		<input type="text" class="form-control" name="home" id="h" value="${ses.home }" placeholder="Home...">
	</div>
	<div class="mb-3">
  		<label for="i" class="form-label">Age</label>
  		<input type="text" class="form-control" name="age" id="a" value="${ses.age }" placeholder="Age...">
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
  			<c:when test="${fvo.file_type>0 }">
  				<div>
  					<img alt="" src="/upload/${fvo.save_dir}/${fvo.uuid }_${fvo.file_name}">
  				</div>
  			</c:when>
  			<c:otherwise>
  				<div>
  					<!-- 파일타입이 0인경우 아이콘 모양하나 가져와서 넣기 -->
  				</div>
  			</c:otherwise>
  		</c:choose>
  		<div>
			<!-- 파일이름 작성일 -->
			<div>${fvo.file_name }</div>
			${fvo.reg_date }
			<span class="badge rounded-pill text-bg-warning">${fvo.file_size }Byte</span>  		
  		</div>
  </li>
	</c:forEach>

  </ul>
	</div>
	
	<button type="submit" class="btn btn-danger">수정</button>
	<a href="/member/remove"> <button type="button" class="btn btn-info">회원탈퇴</button> </a>
</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>