<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>글</h3>
			</div>
		</div>

		<div class="clearfix"></div>

		<div class="row" style="margin-top: 10px">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>글 편집</h2>
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
						<form action="post/new" method="post">
							<div class="form-group">
								<label for="title">제목*</label> <input type="text"
									class="form-control" placeholder="제목을 입력해주세요." required />
							</div>

							<div class="form-group">
								<label for="content">내용*</label>
								<textarea class="form-control" required></textarea>
							</div>

							<div class="form-group">
								<label for="category">카테고리*</label>
								<div class="row">
									<div class="col-md-5 col-sm-5 col-xs-12">
										<select class="form-control">
											<option value="n">카테고리를 선택해 주세요.</option>
										</select>
									</div>
									<div class="col-md-7 col-sm-7"></div>
								</div>
							</div>

							<div class="form-group">
								<label for="tag">태그</label>
								<div class="row">
									<div class="col-md-5">
										<input type="text" class="form-control"
											placeholder="태그를 입력해주세요." />
									</div>
									<div class="col-md-7"></div>
								</div>
							</div>

							<div class="form-group">
								<label for="permalink">고유주소*</label>
								<div class="row">
									<div class="col-md-5">
										<input type="text" class="form-control"
											placeholder="고유주소를 입력해주세요." required />
									</div>
									<div class="col-md-7"></div>
								</div>
							</div>

							<div class="form-group">
								<input type="submit" class="btn btn-primary" value="수정">
								<input type="button" class="btn btn-danger pull-right" value="취소">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->