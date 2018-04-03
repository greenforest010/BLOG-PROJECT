<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>메인</h3>
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
						<h2>메인 페이지</h2>
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
						<div class="row">
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="x_panel">
									<div class="x_title">
										<h2>카테고리 비율</h2>
										<ul class="nav navbar-right panel_toolbox">
											<li><a class="collapse-link"><i
													class="fa fa-chevron-up"></i></a></li>
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-expanded="false"><i
													class="fa fa-wrench"></i></a>
												<ul class="dropdown-menu" role="menu">
													<li><a href="#">Settings 1</a></li>
													<li><a href="#">Settings 2</a></li>
												</ul></li>
											<li><a class="close-link"><i class="fa fa-close"></i></a>
											</li>
										</ul>
										<div class="clearfix"></div>
									</div>
									<div class="x_content">
										<canvas id="myChart" width="200" height="200"></canvas>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-3 col-xs-12">
								<div class="x_panel">
									<div class="x_title">
										<h2>방문자 통계</h2>
										<ul class="nav navbar-right panel_toolbox">
											<li><a class="collapse-link"><i
													class="fa fa-chevron-up"></i></a></li>
											<li class="dropdown"><a href="#" class="dropdown-toggle"
												data-toggle="dropdown" role="button" aria-expanded="false"><i
													class="fa fa-wrench"></i></a>
												<ul class="dropdown-menu" role="menu">
													<li><a href="#">Settings 1</a></li>
													<li><a href="#">Settings 2</a></li>
												</ul></li>
											<li><a class="close-link"><i class="fa fa-close"></i></a>
											</li>
										</ul>
										<div class="clearfix"></div>
									</div>
									<div class="x_content" style="background-color : #eeeeee">
										<div class="row tile_count">
											<div class="col-md-12 col-sm-12 col-xs-12 tile_stats_count">
												<span class="count_top"><i class="fa fa-user"></i>
													방문자 수</span>
												<div class="count">${newVisitors}</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /page content -->

				<!-- jQuery -->
				<script src="/resources/admin/vendors/jquery/dist/jquery.min.js"></script>

				<!-- Chart.js  -->
				<script src="/resources/admin/vendors/Chart.js/dist/Chart.js"></script>
				<script>
					var categoryLevel = ${categoryLevel};

					var labels = [];
					var data = [];

					$.each(categoryLevel, function(key, value) {
						labels.push(key);
						data.push(value);
					});

					function getRandomColor() {
						var letters = '0123456789ABCDEF'.split('');
						var color = '#';
						for (var i = 0; i < 6; i++) {
							color += letters[Math.floor(Math.random() * 16)];
						}
						return color;
					}

					var arrayRandomColor = [];

					for (x in labels) {
						x = getRandomColor();
						arrayRandomColor.push(x);
					}

					var ctx = $("#myChart");
					var myPieChart = new Chart(ctx, {
						type : 'pie',
						data : {
							labels : labels,
							datasets : [ {
								data : data,
								backgroundColor : arrayRandomColor,
							} ]
						},
						options : {
							title : {
								display : true,
								text : '전체 카테고리 중 글 수에 따른 상위 카테고리 비율'
							}
						}
					});
				</script>