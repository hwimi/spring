<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="layout/header.jsp"></jsp:include>
<div  class="container-md">
<h1>
	My First Spring Project  
</h1>
<c:if test="${ses.id ne null }">
	<p>${ses.id}님이 로그인하였습니다.!!
	<span class="badge text-bg-info">${ ses.last_login}</span>
</c:if>
</div>

<script type="text/javascript">
 const msg_login=`<c:out value="${msg_login}"/>`;
 console.log(msg_login);
 if(msg_login==1){
	 alert("로그인실패");
 }
 const msg_logout=`<c:out value="${msg_logout}"/>`;
 console.log(msg_logout);
 if(msg_logout==1){
	 alert("로그아웃 되었습니다");
 }
 
 const msg_remove=`<c:out value="${msg_remove}"/>`;
 console.log(msg_remove);
 if(msg_remove==1){
	 alert("삭제되었습니다")
 }
</script>
<jsp:include page="layout/footer.jsp"></jsp:include>
