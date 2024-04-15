<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-9">	
	<div class="row p-2">
	<c:forEach var="item" items="${videos }">
		<div class="col-3 mt-2">
			<div class="card text-center">
				<div class="card-body">
					<a href= "Detail?videoId=${item.videoId }"><img src="${empty item.poster ? 'images/logo.png':item.poster}" width="90%" alt="" class="img-fluid"></a>
					<div class="row border-top mt-2">
						<b>${item.title }</b>
					</div>
				</div>
				<div class="card-footer">
					<a href="LikeVideo?videoId=${item.videoId }" class="btn btn-success">Like</a> <a href="ShareVideo?videoId=${item.videoId }"
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
            <c:if test="${currentPage > 1}">
                <li class="page-item"><a class="page-link" href="Homepage?page=${currentPage - 1}" aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span></a></li>
            </c:if>
            <c:forEach var="i" begin="1" end="${noOfPages}">
                <c:choose>
                    <c:when test="${i >= currentPage - 2 && i <= currentPage + 2}">
                        <c:choose>
                            <c:when test="${i == currentPage}">
                                <li class="page-item active"><a class="page-link" href="Homepage?page=${i}">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="Homepage?page=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < noOfPages}">
                <li class="page-item"><a class="page-link" href="Homepage?page=${currentPage + 1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span></a></li>
            </c:if>
        </ul>
    </nav>
</div>
</div>
</div>
<div class="col-3">
	<div class="row mt-3 mb-3">
		<div class="col">
			<div class="card">
				<div class="card-header">
					<i class="fa fa-thumbs-up" aria-hidden="true"></i>Favorites
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Laptop</li>
					<li class="list-group-item">Computer</li>
					<li class="list-group-item">Ipad</li>
					<li class="list-group-item">Iphone</li>
					<li class="list-group-item">Imac</li>

				</ul>
			</div>
		</div>
	</div>
</div>