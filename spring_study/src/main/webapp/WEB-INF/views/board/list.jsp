<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/header.jsp"></jsp:include>

<div  class="container-md">
<h1>Board list page</h1>
<!-- 검색라인 -->
<!-- <!-- <form class="row row-cols-lg-auto g-3 align-items-center">
  <div class="col-12">
    <label class="visually-hidden" for="inlineFormInputGroupUsername">Username</label>
    <div class="input-group">
      <div class="input-group-text">선택하세요</div>
      <input type="text" class="form-control" id="inlineFormInputGroupUsername" placeholder="Username">
    </div>
  </div> --> -->
<form action="/board/list"method="get" class="row g-3">
<div class="row align-items-center">
    <div class="col-12 col-sm-4">
        <label class="visually-hidden" for="inlineFormSelectPref">Preference</label>
        <select class="form-select" name="type" id="inlineFormSelectPref">
            <option ${ph.pgvo==null? 'selected':'' } selected >Choose...</option>
            <option value="t" ${ph.pgvo.type eq 't'? 'selected':'' }>title</option>
            <option value="w" ${ph.pgvo.type eq 'w'? 'selected':'' }>writer</option>
            <option value="c" ${ph.pgvo.type eq 'c'? 'selected':'' }>content</option>
            <option value="tc" ${ph.pgvo.type eq 'tc'? 'selected':'' }>title&content</option>
            <option value="wc" ${ph.pgvo.type eq 'wc'? 'selected':'' }>writer&content</option>
            <option value="tw" ${ph.pgvo.type eq 'tw'? 'selected':'' }>title&writer</option>
            <option value="twc" ${ph.pgvo.type eq 'twc'? 'selected':'' }>all</option>
        </select>
    </div>
    <div class="col-12 col-sm-4">
        <input type="text" name="keyword" value="${ph.pgvo.keyword }" class="form-control" placeholder="search...">
        <input type="hidden" name="pageNo" value="1">
        <input type="hidden" name="qty" value="10">
    </div>
    <div class="col-12 col-sm-4">
        <button type="submit" class="btn btn-primary position-relative">
            serach
            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                ${ph.totalCount }
                <span class="visually-hidden">unread messages</span>
            </span>
        </button>
    </div>
</div>
</form>
<table class="table table-hover table table-dark table-striped">
	<thead>
		<tr>
			<th>bno</th>
			<th>title</th>
			<th>writer</th>
			<th>reg_date</th>
			<th>read_count</th>			
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${list }" var="bvo">
	<tr>
		<td>
			${bvo.bno }
		</td>
		<td>
			<a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a>
		</td>
		<td>
			${bvo.writer }
		</td>
		<td>
			${bvo.reg_date }
		</td>
		<td>
			${bvo.read_count }
		</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
	<!-- 페이지 네이션 -->
<nav aria-label="Page navigation example">
  <ul class="pagination">
  <c:if test="${ph.prev}">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
        <span aria-hidden="true"> 이전 </span>
      </a>
    </li>
    </c:if>
    	<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
    		<li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
    	</c:forEach>
    <c:if test="${ph.next}">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
        <span aria-hidden="true">다음</span>
      </a>
    </li>
    </c:if>
    
  </ul>
</nav>
<jsp:include page="../layout/footer.jsp"></jsp:include>
</div>

