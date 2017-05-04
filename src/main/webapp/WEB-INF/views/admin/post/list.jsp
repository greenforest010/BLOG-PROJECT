<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
			<a class="btn btn-primary" href="post/new" role="button">글 쓰기</a>
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
											class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
									</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${list}" var="postVO">
									<tr>
										<td class="a-center "><input type="checkbox" class="flat"
											name="table_records"></td>
										<td>${postVO.id}</td>
										<td>${postVO.title}</td>
										<td>${postVO.author}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${postVO.published}" /></td>
										<td>Paid</td>
										<td>Paid</td>
										<td>$7.45</td>
										<td><a href="#">View</a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var result = '${msg}';
	if (result == 'success') {
		alert("처리가 완료되었습니다.");
	}
</script>
<!-- /page content -->