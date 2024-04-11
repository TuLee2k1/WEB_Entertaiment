<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation"><a
			class="nav-link active" id="videoEditing-tab" data-toggle="tab"
			data-target="#videoEditing" type="button" role="tab"
			aria-controls="videoEditing" aria-selected="true">Favorites</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="videoList-tab" data-toggle="tab" data-target="#videoList"
			type="button" role="tab" aria-controls="videoList"
			aria-selected="false">Favorites Users</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="shareFriends-tab" data-toggle="tab" data-target="#shareFriends"
			type="button" role="tab" aria-controls="shareFriends"
			aria-selected="false">Share Friends</a></li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoEditing"
			role="tabpanel" aria-labelledby="videoEditing-tab">
			<table class="table table-bordered mt-3">
				<tr>
					<td>Video Title</td>
					<td>Favorites Count</td>
					<td>Lasted Date</td>
					<td>Oldest Date</td>
				</tr>
				<tr>
					<td>Java Programming</td>
					<td>102</td>
					<td>02/04/2024</td>
					<td>02/04/2024</td>
				</tr>
			</table>
		</div>

		<div class="tab-pane fade" id="videoList" role="tabpanel"
			aria-labelledby="videoList-tab">
			<form action="" method="get">
				<div class="row mt-3">
					<div class="col">
						<div class="form-inline">
							<div class="form-group">
								<label>Video Title <select name="videoUserId" id="videoUserId"
									class="form-control ml-3">
									<c:forEach var="item" items="${vidList }">
										<option value="${item.videoId }" 
										${item.videoId == videoUserId?'selected':'' }>${item.title }</option>
										</c:forEach>
								</select>
								</label>
								<button class="btn btn-info ml-2">Report</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col mt-3">
						<table class="table table-bordered">
							<tr>
								<td>Username</td>
								<td>Fullname</td>
								<td>Email</td>
								<td>Favorite Date</td>
							</tr>
							<c:forEach var="item" items="${favUsers }">
							<tr>
								<td>${item.username }</td>
								<td>${item.fullname }</td>
								<td>${item.email }</td>
								<td>${item.likeDate }</td>
							</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</form>
		</div>

		<div class="tab-pane fade" id="shareFriends" role="tabpanel"
			aria-labelledby="shareFriends-tab">
			<form action="" method="get">
				<div class="row mt-3">
					<div class="col">
						<div class="form-inline">
							<div class="form-group ml-3">
								<label>Video Title 
								<select name="videoUserId" id="videoUserid"
									class="form-control ml-3">
									<c:forEach var="item" items="${vidList }">
										<option value="${ item.videoId}" 
										${item.videoId == videoUserId?'selected' :'' }>${item.title }</option>
									</c:forEach>
								</select>
								</label>
								<button class="btn btn-info ml-2">Report</button>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col mt-3">
						<table class="table table-bordered">
							<tr>
								<td>Userame</td>
								<td>Fullname</td>
								<td>Email</td>
								<td>Favorite Date</td>
							</tr>
							<c:forEach var="item" items="${favUsers }">
							<tr>
								<td>${item.username }</td>
								<td>${item.fullname }</td>
								<td>${item.email }</td>
								<td>${item.likeDate }</td>
							</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>