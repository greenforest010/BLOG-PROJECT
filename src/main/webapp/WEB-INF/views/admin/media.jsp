<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!-- Dropzone  -->
<script src="/resources/admin/vendors/dropzone/dist/min/dropzone.min.js"></script>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>미디어</h3>
			</div>
		</div>

		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>파일 업로드</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
							</li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false"><i
									class="fa fa-wrench"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Settings 1</a></li>
									<li><a href="#">Settings 2</a></li>
								</ul></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form class="dropzone" action="/attachments" method="post"
							enctype="multipart/form-data" style="border: 2px dashed;">
							<sec:csrfInput />
							
							<div class="dz-message" data-dz-message>
								<h4>
									<span>이 영역을 <strong>Click</strong> 또는 파일을 <strong>Drag
											and Drop</strong> 하면 업로드 할 수 있습니다.
									</span>
								</h4>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>미디어 리스트</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
							</li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false"><i
									class="fa fa-wrench"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Settings 1</a></li>
									<li><a href="#">Settings 2</a></li>
								</ul></li>
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div
							class="col-md-offset-8 col-md-4 col-sm-4 col-xs-12 form-group pull-right top_search">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="Search for..."> <span
									class="input-group-btn">
									<button class="btn btn-default" type="button">Go!</button>
								</span>
							</div>
						</div>

						<c:forEach items="${list}" var="attachmentVO">
							<div class="col-md-55">
								<div class="thumbnail">
									<div class="image view view-first">
										<img style="width: 100%; display: block;"
											src="/resources/upload${attachmentVO.fullName}" alt="image" />
										<div class="mask">
											<p>Your Text</p>
											<div class="tools tools-bottom">
												<a href="#mediaDetailModal" data-toggle="modal"
													data-attachmentid="${attachmentVO.id}"><i
													class="fa fa-pencil"></i></a> <a class="mediaDelete" href="#"
													onclick="return false;"
													data-attachmentid="${attachmentVO.id}"><i
													class="fa fa-times"></i></a>
											</div>
										</div>
									</div>
									<div class="caption">
										<p>
											<strong>${attachmentVO.fullName}</strong>
										</p>
									</div>
								</div>
							</div>
						</c:forEach>


						<!-- Small modal -->
						<div class="modal fade" id="mediaDetailModal" tabindex="-1"
							role="dialog" aria-labelledby="mediaDetailModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="mediaDetailModalLabel">파일 상세</h4>
									</div>

									<div class="modal-body">
										<div class="container-fluid">
											<div class="row">
												<div class="col-md-5">
													<img style="width: 100%; display: block;" alt="image" />
												</div>
												<div class="col-md-6 col-md-offset-1">
													<div class="form-horizontal">
														<div class="form-group">
															<label for="fullName" class="col-md-3 control-label">파일명</label>
															<div class="col-md-9">
																<input class="form-control" type="text" readonly />
															</div>
														</div>
														<div class="form-group">
															<label for="fileUrl" class="col-md-3 control-label">URL</label>
															<div class="col-md-9">
																<input class="form-control" type="text" readonly />
															</div>
														</div>
														<div class="form-group">
															<label for="mimeType" class="col-md-3 control-label">파일
																형식</label>
															<div class="col-md-9">
																<input class="form-control" type="text" readonly />
															</div>
														</div>
														<div class="form-group">
															<label for="registered" class="col-md-3 control-label">등록
																날짜</label>
															<div class="col-md-9">
																<input class="form-control" type="text" readonly />
															</div>
														</div>
														<div class="form-group">
															<label for="updated" class="col-md-3 control-label">변경
																날짜</label>
															<div class="col-md-9">
																<input class="form-control" type="text" readonly />
															</div>
														</div>
														<div class="form-group">
															<label for="alternateText" class="col-md-3 control-label">대체
																텍스트</label>
															<div class="col-md-9">
																<input class="form-control" type="text" />
															</div>
														</div>

														<div class="form-group">
															<label for="description" class="col-md-3 control-label">설명</label>
															<div class="col-md-9">
																<input class="form-control" type="text" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											id="fileDetailUpdateButton" data-dismiss="modal">확인</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->

<script src="/resources/admin/vendors/jquery/dist/jquery.min.js"></script>

<script type="text/javascript">
	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
</script>

<script type="text/javascript">
	var attachmentId = 0;

	$(function() {
		$("#mediaDetailModal")
				.on(
						"show.bs.modal",
						function(event) {
							attachmentId = $(event.relatedTarget).data(
									'attachmentid');

							$
									.getJSON(
											"/attachments/" + attachmentId,
											function(data) {
												$
														.each(
																data,
																function(key,
																		value) {
																	function findInputOfMediaDetailModal(
																			mediaDetailAttribute) {
																		return $(
																				".modal-body")
																				.find(
																						'label[for="'
																								+ mediaDetailAttribute
																								+ '"]')
																				.parent()
																				.find(
																						"input");
																	}

																	function makeDate(
																			dateNumber) {
																		var dateObj = new Date(
																				dateNumber);
																		var year = dateObj
																				.getFullYear();
																		var month = dateObj
																				.getMonth() + 1;
																		var date = dateObj
																				.getDate();
																		var hours = dateObj
																				.getHours();
																		var minutes = dateObj
																				.getMinutes();
																		var seconds = dateObj
																				.getSeconds();

																		var result = year
																				+ "."
																				+ month
																				+ "."
																				+ date
																				+ "."
																				+ " "
																				+ hours
																				+ ":"
																				+ minutes
																				+ ":"
																				+ seconds;

																		return result;
																	}

																	if (key == 'fullName') {
																		var fileUrl = "/resources/upload"
																				+ value;

																		$(
																				".modal-body")
																				.find(
																						"img")
																				.attr(
																						"src",
																						function() {
																							return fileUrl;
																						});

																		findInputOfMediaDetailModal(
																				'fileUrl')
																				.val(
																						fileUrl);
																	} else if (key == 'registered') {
																		var registered = makeDate(value);

																		value = registered;
																	} else if (key == 'updated') {
																		var updated = makeDate(value);

																		value = updated;
																	}

																	findInputOfMediaDetailModal(
																			key)
																			.val(
																					value);

																	console
																			.log("key: "
																					+ key
																					+ ", value: "
																					+ value);
																});
											}).fail(
											function(jqxhr, textStatus, error) {
												var err = textStatus + ", "
														+ error;

												console.log("Request Failed: "
														+ err);
											});
						});
	});

	$(function() {
		$("#fileDetailUpdateButton").click(
				function(event) {
					if (attachmentId != 0) {
						var alternateText = $(".modal-body").find(
								'label[for="alternateText"]').parent().find(
								"input").val();
						var description = $(".modal-body").find(
								'label[for="description"]').parent().find(
								"input").val();

						$.ajax({
							method : 'PUT',
							url : "/attachments/" + attachmentId,
							contentType : 'application/json',
							data : JSON.stringify({
								alternateText : alternateText,
								description : description
							})
						}).done(function(data) {
							alert("파일 정보를 변경했습니다.");
						}).fail(function(jqxhr, textStatus, error) {
							var err = textStatus + ", " + error;

							console.log("Request Failed: " + err);
						});
					}
				});
	});
</script>

<script type="text/javascript">
	$(".mediaDelete").click(function(event) {
		attachmentId = $(this).data("attachmentid");

		$.ajax({
			method : 'DELETE',
			url : "/attachments?ids=" + attachmentId,
		}).done(function() {
			$(event.target).parents(".col-md-55").remove();

			alert("해당 파일을 삭제했습니다.");
		}).fail(function(jqxhr, textStatus, error) {
			var err = textStatus + ", " + error;

			console.log("Request Failed: " + err);
		});
	});
</script>