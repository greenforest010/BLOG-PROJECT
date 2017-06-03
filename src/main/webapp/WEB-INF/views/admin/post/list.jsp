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
			<a class="btn btn-primary" href="/admin/post/new" role="button">글
				쓰기</a>
		</div>

		<div
			class="col-md-5 col-sm-5 col-xs-8 form-group pull-right top_search">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span>
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
									<th class="column-title no-link last"><span class="nobr">태그</span>
									</th>
									<th class="bulk-actions" colspan="8"><a class="antoo"
										style="color: #fff; font-weight: 500;">Bulk Actions ( <span
											class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a></th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${list}" var="postVO">
									<tr>
										<td class="a-center "><input type="checkbox" class="flat"
											name="table_records" value="${postVO.id}"></td>
										<td>${postVO.id}</td>
										<td><a href="/admin/post/${postVO.id}">${postVO.title}</a></td>
										<td>${postVO.memberVO.loginId}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
												value="${postVO.published}" /></td>
										<td>Paid</td>
										<td>Paid</td>
										<c:if test="${postVO.categoryVO.term != null}">
											<td id="categoryTermTd${postVO.id}">${postVO.categoryVO.term}</td>
										</c:if>
										<c:if test="${postVO.categoryVO.term == null}">
											<td id="categoryTermTd${postVO.id}"><a href="#categorySelectModal"
												data-postid="${postVO.id}" data-toggle="modal"><span
													style="color: cyan;">카테고리를 설정하세요.</span></a></td>
										</c:if>
										<td><a href="#">View</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<sf:form id="deletePostForm" action="/admin/post/remove"
						method="post">
						<input type="submit" class="btn btn-danger" value="삭제" />
					</sf:form>

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
	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
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
	$("#deletePostForm").submit(function(event) {
		var postIds = [];
		
		$("input[name='table_records']:checked").each(function() {
			postIds.push($(this).val()); // this -> input(checkbox)
		});
		
		if (postIds == '') {
			alert("삭제할 대상을 선택하세요.");
			
			return false;
		} else {
			var postIdInput = "<input type='hidden' name='postIds' value=" + postIds +" />";
			
			$(this).append(postIdInput); // this -> deletePostForm
		}
	});
</script>
<!-- /page content -->