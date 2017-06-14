<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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
				<!-- start details -->
				<div class="details">
					<h2>${postVO.title}</h2>
					${postVO.memberVO.loginId}, <fmt:formatDate pattern="yyyy-MM-dd HH-mm" value="${postVO.published}"/>
					<div class="det_pic">
						<img src="/resources/images/det_pic.jpg" alt="" />
					</div>
					<div class="det_text">
						${postVO.content}
					</div>
					<div class="clear"></div>
				</div>
				<!-- end details -->
			</div>
		</div>
	</div>
</div>