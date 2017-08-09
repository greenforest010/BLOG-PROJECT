<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="page-title">
		<div class="title_left">
			<h3>글</h3>
		</div>
	</div>
	<div class="clearfix"></div>

	<div class="row">
		<div class="col-md-1 col-sm-1 col-xs-1">
			<a class="btn btn-primary"
				href="/admin/post/new?page=${criteria.page}&perPageNum=${criteria.perPageNum}"
				role="button">글 쓰기</a>
		</div>

		<div
			class="col-md-7 col-sm-7 col-xs-7 form-group pull-right top_search">
			<div class="col-md-5">
				<div class="form-group">
					<select id="selectCategoryForSearch" class="form-control">
						<option value="">카테고리로 글을 검색할 수 있습니다.</option>
						<c:forEach items="${categoryList}" var="categoryVO">
							<option value="${categoryVO.slugTerm}">${categoryVO.term}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="col-md-7">
				<div class="input-group">
					<input type="text" class="form-control" name="keyword"
						placeholder="글 제목이나 내용을 입력하세요..." required> <span
						class="input-group-btn">
						<button id="searchButton" class="btn btn-default" type="button">Search!</button>
					</span>
				</div>
			</div>

		</div>
	</div>

	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>전체 리스트</h2>
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
					<div style="width: 100%; height: 400px; overflow: auto">

						<c:choose>
							<c:when test="${empty list}">
								<p>검색 결과가 없습니다.</p>
							</c:when>
							<c:otherwise>
								<!-- 테이블 밖 스크롤 설정(테이블 안으로 변경 하는 코드 필요...)  -->
								<div class="table-responsive">
									<table class="table table-striped jambo_table bulk_action">
										<thead>
											<tr class="headings">
												<th><input type="checkbox" id="check-all" class="flat">
												</th>
												<th class="column-title">번호</th>
												<th class="column-title">제목</th>
												<th class="column-title">글쓴이</th>
												<th class="column-title">등록일</th>
												<th class="column-title">조회수</th>
												<th class="column-title">댓글</th>
												<th class="column-title">카테고리</th>
												<th class="column-title no-link last"><span
													class="nobr">태그</span></th>
												<th class="bulk-actions" colspan="8"><a class="antoo"
													style="color: #fff; font-weight: 500;">Bulk Actions ( <span
														class="action-cnt"> </span> ) <i
														class="fa fa-chevron-down"></i></a></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="postVO">
												<tr>
													<td class="a-center "><input type="checkbox"
														class="flat" name="table_records" value="${postVO.id}"></td>
													<td>${postVO.id}</td>
													<td><a
														href="/admin/post/${postVO.id}${pageMaker.makeSearch(pageMaker.criteria.page)}">${postVO.title}</a></td>
													<td>${postVO.memberVO.loginId}</td>
													<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
															value="${postVO.published}" /></td>
													<td>Paid</td>
													<td>Paid</td>
													<c:if test="${postVO.categoryVO.term != null}">
														<td id="categoryTermTd${postVO.id}">${postVO.categoryVO.term}</td>
													</c:if>
													<c:if test="${postVO.categoryVO.term == null}">
														<td id="categoryTermTd${postVO.id}"><a
															href="#categorySelectModal" data-postid="${postVO.id}"
															data-toggle="modal"><span style="color: cyan;">카테고리를
																	설정하세요.</span></a></td>
													</c:if>
													<td><a href="#">View</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
				</div>

				<c:if test="${not empty list}">
					<button id="deletePost" class="btn btn-danger">삭제</button>
				</c:if>

				<div class="text-center">
					<ul class="pagination">
						<c:if test="${pageMaker.previous}">
							<li><a
								href="${pageMaker.makeSearch(pageMaker.startPage -  1)}">&laquo;</a></li>
						</c:if>
						<c:forEach begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}" var="idx">
							<li
								<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}" />><a
								href="${pageMaker.makeSearch(idx)}">${idx}</a></li>
						</c:forEach>
						<c:if test="${pageMaker.next && (pageMaker.endPage > 0)}">
							<li><a href="${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a></li>
						</c:if>
					</ul>
				</div>

				<!-- Small modal -->
				<div class="modal fade" id="categorySelectModal" tabindex="-1"
					role="dialog" aria-labelledby="categorySelectModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="categorySelectModalLabel"></h4>
							</div>
							<div class="modal-body">
								<select class="form-control" name="categoryId" required>
									<c:forEach items="${categoryList}" var="categoryVO">
										<option value="${categoryVO.id}">${categoryVO.term}</option>
									</c:forEach>
								</select>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									id="categorySelectButton" data-dismiss="modal">확인</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">닫기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/resources/admin/vendors/jquery/dist/jquery.min.js"></script>
<script
	src="/resources/admin/vendors/bootstrap/dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
	var result = '${msg}';

	if (result == 'success') {
		alert("처리가 완료되었습니다.");
	}
</script>

<script type="text/javascript">
	/* $(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	}); */
</script>

<script type="text/javascript">
	var postId = 0;

	$("#categorySelectModal").on(
			"show.bs.modal",
			function(event) {
				postId = $(event.relatedTarget).data('postid');

				$(this).find('.modal-title').html(
						"카테고리를 선택하세요. <small>(글 번호: " + postId + ")</small>"); // $(this): modal
			});

	$(function() {
		$("#categorySelectButton").click(function(event) {
			if (postId != 0) {
				var categoryId = $(".modal-body select option:selected").val();

				$.ajax({
					method : 'POST',
					url : "/admin/post",
					contentType : 'application/json',
					data : JSON.stringify({
						id : postId,
						categoryId : Number(categoryId)
					})
				}).done(function(data) {
					var categoryTerm = data.categoryVO.term;
					$("#categoryTermTd" + postId).html(categoryTerm);

					alert("카테고리를 설정했습니다.");
				});
			}
		});
	})
</script>

<script type="text/javascript">
	$("#deletePost").click(
			function() {
				var postIds = [];

				$("input[name='table_records']:checked").each(function() {
					postIds.push($(this).val()); // this -> input(checkbox)
				});

				if (postIds == '') {
					alert("삭제할 대상을 선택하세요.");

					return false;
				} else {
					$.ajax({
						url : "/posts?ids=" + postIds,
						method : "DELETE"
					}).done(
							function() {
								$("input[name='table_records']:checked")
										.parents("tr").remove();

								alert("글을 삭제했습니다.");
							});
				}
			});
</script>

<script type="text/javascript">
	$(function() {
		$("#searchButton").on(
				"click",
				function(event) {
					var keywordInput = $(".top_search").find(
							"input[name='keyword']");

					if (!keywordInput.val()) {
						alert("검색어를 입력하세요.");

						keywordInput.focus();
					} else {
						var currentPath = window.location.pathname;

						location.href = currentPath
								+ "${pageMaker.makeQuery(1)}"
								+ "&keyword="
								+ $(".top_search")
										.find("input[name='keyword']").val();
					}
				});
	});
</script>

<script type="text/javascript">
	$("#selectCategoryForSearch").change(function() {
		var slugTerm = $("#selectCategoryForSearch option:selected").val();

		location.href = "/admin/post/category/" + slugTerm;
	});
</script>
<!-- /page content -->