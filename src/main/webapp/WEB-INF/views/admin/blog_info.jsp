<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>블로그 정보</h3>
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
						<h2>
							블로그 정보 <small>블로그 정보는 피드(FEED)에 반영됩니다.</small>
						</h2>
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
						<sf:form class="form-horizontal form-label-left" method="put">
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="title">제목 <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="title"
										class="form-control col-md-7 col-xs-12"
										value="${blogInfo.title}" placeholder=" 블로그 제목을 입력하세요."
										required>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="subtitle">부제목 </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="subtitle"
										class="form-control col-md-7 col-xs-12"
										value="${blogInfo.subtitle}"
										placeholder="블로그 부제목을 입력할 수 있습니다.">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="domainName">도메인 이름 <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input type="text" name="domainName"
										class="form-control col-md-7 col-xs-12"
										value="${blogInfo.domainName}" readonly>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="rights">저작권(rights) </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<textarea class="form-control" name="rights" required>${blogInfo.rights}</textarea>
								</div>

							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button type="submit" class="btn btn-primary">Cancel</button>
									<button type="submit" class="btn btn-success">Submit</button>
								</div>
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
	/*remove auto p tag*/
	CKEDITOR.config.autoParagraph = false;

	CKEDITOR
			.replace(
					'rights',
					{
						customConfig : '/resources/admin/vendors/ckeditor/custom/basic_toolbar_config.js',
					});
</script>
<!-- /page content -->