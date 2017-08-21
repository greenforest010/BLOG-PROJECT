<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>태그</h3>
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
						<h2>태그 리스트</h2>
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
						<div class="col-md-6 col-sm-6 col-xs-12">
							<textarea id="tag"></textarea>
						</div>

						<div class="col-md-6 col-sm-6 col-xs-8">
							<h2>이름</h2>
							<div class="row">
								<div class="col-md-10 col-sm-10 col-xs-10">
									<input id="tagName" type="text" class="form-control" readonly>
								</div>
							</div>

							<h2>고유주소</h2>
							<div class="row">
								<div class="col-md-10 col-sm-10 col-xs-10">
									<input id="tagPermalinkInput" type="text" class="form-control">
								</div>

								<div class="col-md-2 col-sm-2 col-xs-2">
									<button id="changeTagPermalink" class="btn btn-primary">변경</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- jQuery -->
<script src="/resources/admin/vendors/jquery/dist/jquery.min.js"></script>

<!-- tagEditor  -->
<script type="text/javascript"
	src="/resources/admin/vendors/jQuery-tagEditor/jquery.tag-editor.min.js"></script>

<script type="text/javascript"
	src="/resources/admin/vendors/jQuery-tagEditor/jquery.caret.min.js"></script>

<script type="text/javascript">
	$("#tag").tagEditor({
		initialTags : [ 'Hello', 'World', 'Example', 'Tags' ],
		delimiter : ', ', /* space and comma */
		placeholder : 'Enter tags ...'
	});
</script>
<!-- /page content -->