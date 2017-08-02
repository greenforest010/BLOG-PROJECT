<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- start slider -->
<div class="slider_bg">
	<div class="wrap">
		<div class="slider">
			<h2>Growing IT Skill</h2>
			<h3>Welcome, green010's BLOG</h3>
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
					<c:forEach items="${list}" var="postVO">
						<div class="blog_main">
							<div class="b_left">
								<h4 class="bg">
									<img src="resources/images/note.jpg" alt="" />
								</h4>
							</div>
							<div class="b_right">
								<%-- <h4><a href="${postVO.slugTitle}">${postVO.title}</a></h4> --%>
								<h4>
									<a href="/post/${postVO.id}">${postVO.title}</a>
								</h4>
								<div class="blog_list">
									<ul>
										<li><a href="#"> <i class="date"> </i><span><fmt:formatDate
														pattern="yyyy-MM-dd HH:mm" value="${postVO.published}" />
											</span></a></li>
										<li><a href="/post/${postVO.id}#disqus_thread"> <i
												class="comment"> </i> <span>Comments</span></a></li>
										<li><a href="#"> <i class="news"> </i><span>Public,
													News</span></a></li>
										<li><a href="#"> <i class="views"> </i><span>124
													views</span></a></li>
									</ul>
									<div class="clear"></div>
								</div>
								<div class="blog_art">
									<ul>
										<li><a href="#"> <i> </i><span>18</span></a></li>
									</ul>
									<div class="clear"></div>
								</div>
							</div>
							<div class="clear"></div>
							<p>${postVO.content}</p>
							<a class="btn" href="details">read more</a>
						</div>
					</c:forEach>

					<div class="txt-center">
						<ul class="pagination">
							<c:if test="${pageMaker.previous}">
								<li><a href="${pageMaker.startPage -  1}">&laquo;</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" var="idx">
								<li
									<c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}" />><a
									href="${idx}">${idx}</a></li>
							</c:forEach>
							<c:if test="${pageMaker.next && (pageMaker.endPage > 0)}">
								<li><a href="${pageMaker.endPage + 1}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>

				</div>

				<!-- start blog_sidebar -->
				<div class="blog_sidebar">
					<div class="sidebar">
						<!-- start tag_nav -->
						<h4>tags</h4>
						<ul class="tag_nav">
							<li><a href="#">art</a></li>
							<li><a href="#">awesome</a></li>
							<li><a href="#">classic</a></li>
							<li><a href="#">photo</a></li>
							<li><a href="#">wordpress</a></li>
							<li><a href="#">videos</a></li>
							<li><a href="#">standard</a></li>
							<li><a href="#">gaming</a></li>
							<li><a href="#">photo</a></li>
							<li><a href="#">music</a></li>
							<li><a href="#">data</a></li>
							<div class="clear"></div>
						</ul>

						<!-- start flicker images -->
						<h4>ads 125x125</h4>
						<ul class="ads_nav">
							<li><a href="#"><img src="resources/images/ads_pic.jpg"
									alt=""> </a></li>
							<li><a href="#"><img src="resources/images/ads_pic.jpg"
									alt=""> </a></li>
							<li><a href="#"><img src="resources/images/ads_pic.jpg"
									alt=""> </a></li>
							<li><a href="#"><img src="resources/images/ads_pic.jpg"
									alt=""> </a></li>
							<div class="clear"></div>
						</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>

<form id="pageForm">
	<input type='hidden' name="page"> <input type='hidden'
		name="perPageNum" value="${pageMaker.criteria.perPageNum}">
</form>

<script src="resources/js/jquery.min.js"></script>
<script>
	$(".pagination li a").on("click", function(event) {

		event.preventDefault();

		var targetPage = $(this).attr("href");

		var pageForm = $("#pageForm");
		pageForm.find("[name='page']").val(targetPage);
		pageForm.attr("action", "/").attr("method", "get");
		pageForm.submit();
	});
</script>

<script id="dsq-count-scr" src="//growingitskill.disqus.com/count.js" async></script>