<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<!-- page content -->
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>카테고리</h3>
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
						<h2>카테고리 리스트</h2>
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
							<div id="category"></div>
						</div>

						<div class="col-md-6 col-sm-6 col-xs-8">
							<h2>고유주소</h2>

							<div class="row">
								<div class="col-md-10 col-sm-10 col-xs-10">
									<input id="categoryPermalinkInput" type="text"
										class="form-control">
								</div>

								<div class="col-md-2 col-sm-2 col-xs-2">
									<button id="changeCategoryPermalink" class="btn btn-primary">변경</button>
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

<!-- jsTree-->
<script src="/resources/admin/vendors/jsTree/dist/jstree.min.js"></script>

<script type="text/javascript">
	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	var categoryData = [];

	$.ajax({
		async : false,
		url : "/categories",
		dataType : "json",
		success : function(data) {
			$.each(data, function(key, val) {
				var items = new Object();

				if (val.parent == 0) {
					val.parent = "#";
				}

				items.id = val.id.toString();
				items.text = val.term;
				items.parent = val.parent.toString();
				items.slugTerm = val.slugTerm;

				console.log("id: " + items.id + ", text: " + items.text
						+ ", parent: " + items.parent + ", slugTerm: "
						+ items.slugTerm);

				categoryData.push(items);
			});
		}
	});

	for (i in categoryData) {
		console.log(categoryData[i]);
	}
	
	var selectCategory = new Object();

	$('#category').on('changed.jstree', function(e, data) {
		selectCategory.id = data.instance.get_node(data.selected[0]).id;
		
		var i, j, r = [];

		for (i = 0, j = data.selected.length; i < j; i++) {
			r.push(data.instance.get_node(data.selected[i]).original.slugTerm);
		}

		$('#categoryPermalinkInput').val(r.join(', '));
	}).on(
			'create_node.jstree',
			function(e, data) {
				$.post('/categories', {
					'term' : data.node.text,
					'parent' : data.node.parent,
					'slugTerm' : data.node.text
				}).done(
						function(d) {
							console.log("d: " + d + ", d.term: " + d.term
									+ ", d.parent " + d.parent + ", d.slugTerm"
									+ d.slugTerm);

							data.instance.set_id(data.node, d.id);
						}).fail(function() {
					data.instance.refresh();
				});
			}).on('rename_node.jstree', function(e, data) {
		console.log("rename: " + data.node.text);
		$.ajax({
			method : 'PUT',
			url : '/categories/' + data.node.id,
			contentType : 'application/json',
			data : JSON.stringify({
				id : data.node.id,
				term : data.node.text
			})
		}).fail(function() {
			data.instance.refresh();
		});
	}).on('delete_node.jstree', function(e, data) {
		console.log("delete: " + data.node.id);
		
		if (data.node.parent == '#') {
			alert("이 카테고리는 삭제 할 수 없습니다.");
			
			return false;
		}
		
		$.ajax({
			method : 'DELETE',
			url : '/categories/' + data.node.id
		}).done(function() {
			alert("삭제 하였습니다.");
		}).fail(function() {
			data.instance.refresh();
		});
		
	}).jstree({
		'core' : {
			'data' : categoryData,
			'check_callback' : true
		},
		'plugins' : [ 'contextmenu', 'unique', 'sort', 'dnd', 'changed' ]
	});
	
	$('#changeCategoryPermalink').on('click', function() {
		var value = $('#categoryPermalinkInput').val();
		
		Number(selectCategory.id);
		
		$.ajax({
			method : 'PUT',
			url : '/categories/' + selectCategory.id,
			contentType : 'application/json',
			data : JSON.stringify({
				id : selectCategory.id,
				slugTerm : value
			})
		}).done(function() {
			alert("성공 했습니다.");
		});
	});
</script>
<!-- /page content -->