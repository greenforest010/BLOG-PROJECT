<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>프로필</h3>
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
						<h2>프로필 편집</h2>
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
						<form class="form-horizontal form-label-left" method="post"
							data-parsley-validate>
							<input type="hidden" name="_method" value="PUT" />
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="loginId">사용자 ID </label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input class="form-control col-md-7 col-xs-12" type="text"
										name="loginId" value="${member.loginId}" readonly>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="nickname">닉네임 <small>(2글자 이상)</small>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<input class="form-control col-md-7 col-xs-12" type="text"
										name="nickname" minlength="2" value="${member.nickname}"
										placeholder="닉네임을 변경할 수 있습니다.">
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="displayName">공개 표시 이름 <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<select class="form-control" name="displayName" required>
										<option value="">공개 표시 이름을 선택하세요.</option>
										<option value="${member.loginId}">${member.loginId}</option>
										<option></option>
									</select>
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button type="submit" class="btn btn-primary">Cancel</button>
									<button id="send" type="submit" class="btn btn-success">Submit</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /page content -->

<script src="/resources/admin/vendors/jquery/dist/jquery.min.js"></script>
<script src="/resources/admin/vendors/parsleyjs/dist/parsley.min.js"></script>
<script src="/resources/admin/vendors/parsleyjs/dist/i18n/ko.js"></script>

<script type="text/javascript">
	var result = '${msg}';

	if (result == 'success') {
		alert("처리가 완료되었습니다.");
	}

	if (result == 'fail') {
		alert("폼 검증에 실패했습니다.");
	}
</script>

<script type="text/javascript">
$("select[name='displayName']").focus(function() {
	$("select[name='displayName'] option:nth-child(3)").remove();
	
	var nickname = $("input[name='nickname']").val();
	
	if (nickname) {
		var option = "<option val='" + nickname + "'>" + nickname + "</option>";

		$("select[name='displayName']").append(option);
	}
});
</script>

<!-- 공개 표시 이름 항목에서 닉네임을 클릭한 후 닉네임이 바뀌면 자동으로 공개 표시 이름 항목도 바뀜  -->
<script type="text/javascript">
$( "input[name='nickname']" ).on('input', function() {
	var nickname = $("input[name='nickname']").val();
	
	$("select[name='displayName'] option:nth-child(3)").val(nickname).html(nickname);
});
</script>