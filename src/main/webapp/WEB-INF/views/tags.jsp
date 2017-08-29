<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- start slider -->
<div class="slider_bg">
	<div class="wrap">
		<div class="slider">
			<h2>Welcome, green010's BLOG</h2>
			<h3>I Hope You Get Good Inspiration!</h3>
		</div>
	</div>
</div>

<!-- start main -->
<div class="main_bg">
	<div class="wrap">
		<div class="main">
			<div class="content">

				<!-- start blog_left -->
				<div class="blog_left">
					<c:choose>
						<c:when test="${empty tagList}">
							<div class="blog_main">
								<div class="b_right">
									<p>태그가 없습니다.</p>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="blog_main">
								<div class="b_right">
									<div class="tag">
										<h2>태그</h2>
										<ul>
											<c:forEach items="${tagList}" var="tag">
												<li><a href="/tag/${tag.slugTerm}">${tag.term}</a></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>

				<!-- start blog_sidebar -->
				<div class="blog_sidebar">
					<div class="sidebar">
						<!-- start category-->
						<h4>Category</h4>
						<div id="category" style="margin: 8%;"></div>

						<!-- start tag_nav -->
						<h4>tags</h4>
						<ul class="tag_nav">
							<li><a href="/tag">태그 전체보기</a></li>
							<div class="clear"></div>
						</ul>

						<!-- start flicker images -->
						<h4>ads 125x125</h4>
						<!-- <ul class="ads_nav">
							<li><a href="#"><img src="/resources/images/ads_pic.jpg"
									alt=""> </a></li>
							<li><a href="#"><img src="/resources/images/ads_pic.jpg"
									alt=""> </a></li>
							<li><a href="#"><img src="/resources/images/ads_pic.jpg"
									alt=""> </a></li>
							<li><a href="#"><img src="/resources/images/ads_pic.jpg"
									alt=""> </a></li>
							<div class="clear"></div>
						</ul> -->
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>

<script src="/resources/js/jquery.min.js"></script>

<script id="dsq-count-scr" src="//growingitskill.disqus.com/count.js"
	async></script>

<!-- jsTree-->
<script src="/resources/js/jsTree/dist/jstree.min.js"></script>

<script type="text/javascript">
	/* $(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	}); */

	var categoryData = [];

	$.ajax({
		async : false,
		url : "/categories",
		dataType : "json",
		success : function(data) {
			var state = new Object();
			state.opened = true;

			$.each(data, function(key, val) {
				var items = new Object();

				if (val.parent == 0) {
					val.parent = "#";
				}

				items.id = val.id.toString();
				items.text = val.term;
				items.parent = val.parent.toString();
				items.slugTerm = val.slugTerm;
				items.state = state;

				categoryData.push(items);
			});
		}
	});

	$('#category').on('changed.jstree', function(e, data) {
		var i, j, r = [];

		for (i = 0, j = data.selected.length; i < j; i++) {
			r.push(data.instance.get_node(data.selected[i]).original.slugTerm);
		}

		location.href = "/category/" + r.join(", ");
	}).jstree({
		'core' : {
			'data' : categoryData
		},
		'types' : {
			'default' : {
				'icon' : "fa fa-archive"
			}
		},
		'plugins' : [ 'unique', 'sort', 'types' ]
	});
</script>