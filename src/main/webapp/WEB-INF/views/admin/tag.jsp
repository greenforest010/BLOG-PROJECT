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
	function getTagListId(tagList, val) {
		var id = 0;

		$.each(tagList, function(tagListKey, tagListVal) {
			if (tagListVal.term == val) {
				id = tagListVal.id;
			}
		});

		return id;
	}

	var tagList;
	var tagTermList = [];

	$.ajax({
		async : false,
		url : "/tags",
		method : "GET",
		dataType : "json",
		success : function(data) {
			tagList = data;

			for (i in data) {
				tagTermList.push(data[i].term);
			}
		}
	});

	$("#tag").tagEditor(
			{
				initialTags : tagTermList,
				placeholder : 'Enter tags ...',
				onChange : function(field, editor, tags) {
				},
				beforeTagSave : function(field, editor, tags, tag, val) {
					if (!tag) {
						$.post('/tags', {
							'term' : val,
						}).done(
								function(data) {
									console.log("data: " + data
											+ ", data.term: " + data.term
											+ ", data.slugTerm: "
											+ data.slugTerm);
								}).fail(function() {
						});

						alert("태그를 생성했습니다.");
					} else {
						var id = getTagListId(tagList, tag);

						$.ajax({
							url : "/tags/" + id,
							method : "PUT",
							contentType : 'application/json',
							data : JSON.stringify({
								term : val
							})
						}).done(function(data) {
							$.each(tagList, function(tagListKey, tagListVal) {
								if (tagListVal.id == id) {
									tagListVal.term = data.term;

									$("#tagName").val(tagListVal.term);
								}
							});
						});
					}
				},
				beforeTagDelete : function(field, editor, tags, val) {
					var id = getTagListId(tagList, val);

					$.ajax({
						url : "/tags/" + id,
						method : "DELETE"
					}).done(function() {
						alert("삭제 하였습니다.");
					}).fail(function() {
					});
				}
			});

	$(".tag-editor-tag").click(function() {
		$("#tagName").val($(this).text());
	});

	$('#changeTagPermalink').on('click', function() {
		var value = $('#tagPermalinkInput').val();

		if (!value) {
			alert("내용을 입력하세요");
		} else {
			var tagId = getTagListId(tagList, $("#tagName").val());

			if (tagId != 0) {
				$.ajax({
					method : 'PUT',
					url : '/tags/' + tagId,
					contentType : 'application/json',
					data : JSON.stringify({
						slugTerm : value
					})
				}).done(function() {
					alert("성공 했습니다.");
				});
			}
		}
	});
</script>
<!-- /page content -->