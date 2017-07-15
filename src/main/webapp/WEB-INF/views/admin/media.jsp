<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

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
						<form class="dropzone" action="/uploads" method="post"
							enctype="multipart/form-data"
							style="border: 2px dashed;">
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

						<div class="col-md-55">
							<div class="thumbnail">
								<div class="image view view-first">
									<img style="width: 100%; display: block;"
										src="/resources/admin/images/picture2.jpg" alt="image" />
									<div class="mask">
										<p>Your Text</p>
										<div class="tools tools-bottom">
											<a href="#"><i class="fa fa-link"></i></a> <a href="#"><i
												class="fa fa-pencil"></i></a> <a href="#"><i
												class="fa fa-times"></i></a>
										</div>
									</div>
								</div>
								<div class="caption">
									<p>Snow and Ice Incoming for the South</p>
								</div>
							</div>
						</div>

						<div class="col-md-55">
							<div class="thumbnail">
								<div class="image view view-first">
									<img style="width: 100%; display: block;"
										src="/resources/admin/images/picture2.jpg" alt="image" />
									<div class="mask">
										<p>Your Text</p>
										<div class="tools tools-bottom">
											<a href="#"><i class="fa fa-link"></i></a> <a href="#"><i
												class="fa fa-pencil"></i></a> <a href="#"><i
												class="fa fa-times"></i></a>
										</div>
									</div>
								</div>
								<div class="caption">
									<p>Snow and Ice Incoming for the South</p>
								</div>
							</div>
						</div>

						<div class="col-md-55">
							<div class="thumbnail">
								<div class="image view view-first">
									<img style="width: 100%; display: block;"
										src="/resources/admin/images/picture2.jpg" alt="image" />
									<div class="mask">
										<p>Your Text</p>
										<div class="tools tools-bottom">
											<a href="#"><i class="fa fa-link"></i></a> <a href="#"><i
												class="fa fa-pencil"></i></a> <a href="#"><i
												class="fa fa-times"></i></a>
										</div>
									</div>
								</div>
								<div class="caption">
									<p>Snow and Ice Incoming for the South</p>
								</div>
							</div>
						</div>

						<div class="col-md-55">
							<div class="thumbnail">
								<div class="image view view-first">
									<img style="width: 100%; display: block;"
										src="/resources/admin/images/picture2.jpg" alt="image" />
									<div class="mask">
										<p>Your Text</p>
										<div class="tools tools-bottom">
											<a href="#"><i class="fa fa-link"></i></a> <a href="#"><i
												class="fa fa-pencil"></i></a> <a href="#"><i
												class="fa fa-times"></i></a>
										</div>
									</div>
								</div>
								<div class="caption">
									<p>Snow and Ice Incoming for the South</p>
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