<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col">
	<div class="row p-2">
	
		<c:forEach var="item" items="${videos }">
		<div class="col-3 mt-2">
			<div class="card text-center">
				<div class="card-body">
				<img src="${item.poster }" width="90%" alt="" class="img-fluid">
					<div class="row">
						<b>${item.title}</b>
					</div>
				</div>
				<div class="card-footer text-muted">
					<a href="UnLikeVideo?videoId=${item.videoId}" class="btn btn-success">Unlike</a> <a href="ShareVideo?videoId=${item.videoId }"
						class="btn btn-info">Share</a>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>
	<div class="row">
		<div class="col">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="page-item active"><a class="page-link" href="#">&lt;</a></li>
					<li class="page-item"><a class="page-link" href="#">&gt;</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>