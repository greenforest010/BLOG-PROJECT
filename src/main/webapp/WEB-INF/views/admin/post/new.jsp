<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

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
						<h2>글 쓰기</h2>
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
						<sf:form method="post">
							<sec:authorize access="isAuthenticated()">
								<sec:authentication property="principal.username" var="loginId" />
								<input type="hidden" name="loginId" value="${loginId}">
							</sec:authorize>

							<div class="form-group">
								<label for="title">제목*</label> <input type="text"
									class="form-control" name="title" placeholder="제목을 입력해주세요."
									required />
							</div>

							<div class="form-group">
								<label for="content">내용*</label>
								<textarea class="form-control" name="content" required></textarea>
							</div>

							<div class="form-group">
								<label for="category">카테고리*</label> <select class="form-control" name="categoryId" required>
									<option value="">카테고리를 선택해 주세요.</option>
									<c:forEach items="${categoryList}" var="categoryVO">
										<option  value="${categoryVO.id}">${categoryVO.term}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group">
								<label for="permalink">고유주소*</label>
								<div class="row">
									<div class="col-md-5">
										<input type="text" class="form-control" name="slugTitle"
											placeholder="고유주소를 입력해주세요." required />
									</div>
									<div class="col-md-7"></div>
								</div>
							</div>

							<div class="form-group">
								<input type="submit" class="btn btn-success" value="확인">
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
<!-- /page content -->

<script src="/resources/admin/vendors/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$(".btn-danger").on("click", function() {
		self.location = "/admin/post?page=" + ${criteria.page} + "&perPageNum=" + ${criteria.perPageNum};
	});
});
</script>