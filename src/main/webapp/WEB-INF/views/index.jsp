<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- start slider -->
<div class="slider_bg">
	<div class="wrap">
		<div class="slider">
			<h2>${blogInfo.subtitle}</h2>
			<h3>I Hope you get a Good Inspiration!</h3>
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
						<c:when test="${empty list}">
							<div class="blog_main">
								<div class="b_right">
									<p>검색 결과가 없습니다.</p>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${list}" var="postVO">
								<div class="blog_main">
									<div class="b_left">
										<h4 class="bg">
											<img src="/resources/images/note.jpg" alt="" />
										</h4>
									</div>
									<div class="b_right">
										<h4>
											<a href="/post/${postVO.slugTitle}">${postVO.title}</a>
										</h4>
										<div class="blog_list">
											<ul>
												<li><a href="#"> <i class="date"> </i><span><fmt:formatDate
																pattern="yyyy-MM-dd HH:mm" value="${postVO.published}" />
													</span></a></li>
												<li><a href="/category/${postVO.categoryVO.slugTerm}">
														<i class="fa fa-archive fa-lg"
														style="background: none; margin-top: 5px"></i><span>${postVO.categoryVO.term}
													</span>
												</a></li>
												<li><a href="/post/${postVO.slugTitle}#disqus_thread">
														<i class="comment"> </i> <span>Comments</span>
												</a></li>
											</ul>
											<div class="clear"></div>
										</div>
									</div>
									<div class="clear"></div>

									<div class="b_content">
										<p>${postVO.content}</p>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<div class="txt-center">
						<ul class="pagination">
							<c:if test="${pageMaker.previous}">
								<li><a
									href="${pageMaker.makeSearch(pageMaker.startPage -  1)}">&laquo;</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" var="idx">
								<li
									<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}" />><a
									href="${pageMaker.makeSearch(idx)}">${idx}</a></li>
							</c:forEach>
							<c:if test="${pageMaker.next && (pageMaker.endPage > 0)}">
								<li><a
									href="${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>

				</div>

				<!-- start blog_sidebar -->
				<div class="blog_sidebar">
					<div class="sidebar">
						<!-- start category-->
						<h4>Category</h4>
						<div id="category" style="margin: 8%;"></div>

						<!-- start tag_nav -->
						<h4>Tag</h4>
						<ul class="tag_nav">
							<li><a href="/tag">태그 전체보기</a></li>
							<div class="clear"></div>
						</ul>

						<!-- start subscribe -->
						<h4>Subscribe</h4>
						<a href="/feed"><i class="fa fa-rss-square fa-4x"
							style="color: orange;"></i></a>

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

			var postCount = ${postCountByCategory};

			$.each(data, function(key, val) {
				var items = new Object();

				if (val.parent == 0) {
					val.parent = "#";
				}

				items.id = val.id.toString();
				items.text = val.term + "(" + postCount[val.id] + ")";
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