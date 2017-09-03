<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>블로그 소개 편집</h3>
			</div>

			<div class="title_right">
				<div
					class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..."> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button">Go!</button>
						</span>
					</div>
				</div>
			</div>
		</div>

		<div class="clearfix"></div>

		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>about</h2>
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
						<sf:form method="put">
							<div class="form-group">
								<label for="content">내용*</label>
								<textarea class="form-control" name="content" required>${content}</textarea>
							</div>

							<div class="form-group">
								<input type="submit" class="btn btn-primary" value="수정">
								<input type="button" class="btn btn-danger pull-right"
									value="취소">
							</div>
						</sf:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- jQuery -->
<script src="/resources/admin/vendors/jquery/dist/jquery.min.js"></script>

<!-- ckeditor -->
<script src="/resources/admin/vendors/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
	var result = '${msg}';

	if (result == 'success') {
		alert("처리가 완료되었습니다.");
	}
</script>

<script type="text/javascript">
	CKEDITOR.replace('content', {
		filebrowserUploadUrl : '/admin/post/upload'
	});
</script>
<!-- /page content -->