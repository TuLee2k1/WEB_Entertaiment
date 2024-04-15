<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">

	<div class="col-7">
	
		<div class="card text-center mt-3">

			<div class="card-body">
				<div class="row">
					<img src="${video.poster}" width="90%" alt="" class="img-fluid">
				</div>
				<div class="row p-2">
					<b>${video.title }</b>
				</div>
			</div>

			<div class="card-footer text-right">
				<a href="LikeVideo?videoId=${video.videoId }" class="btn btn-success">Like</a> <a href="ShareVideo?videoId=${video.videoId }"
					class="btn btn-info">Share</a>
			</div>
		</div>
	</div>
	<div class="col-5">
		<div class="row border mt-3 mb-3">
			<div class="col-5">
				<img src="${video.poster }" width="100%" alt="" class="img-fluid">
			</div>
			<div class="col-7 border-left">${video.title }</div>
		</div>

		<div class="row border mt-3 mb-3">
			<div class="col-5">
				<img src="${video.poster }" alt="" class="img-fluid">
			</div>
			<div class="col-7 border-left">${video.title }</div>
		</div>

		<div class="row border mt-3 mb-3">
			<div class="col-5">
				<img src="${video.poster }" alt="" class="img-fluid">
			</div>
			<div class="col-7 border-left">${video.title }</div>
		</div>

		<div class="row border mt-3 mb-3">
			<div class="col-5">
				<img src="${video.poster }" alt="" class="img-fluid">
			</div>
			<div class="col-7 border-left">${video.title }</div>
		</div>
	
	</div>
		
</div>
