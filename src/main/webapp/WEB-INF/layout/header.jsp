<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!-- start header -->
<div class="header_bg">
	<div class="wrap">
		<div class="header">
			<div class="logo">
				<h1>
					<a href="/"><img src="/resources/images/logo.png" alt="" /></a>
				</h1>
			</div>
			<div class="h_right">
				<ul class="menu">
					<li><a href="/">home</a></li>
					<li><a href="/about">about</a></li>
					<sec:authorize access="isAnonymous()">
						<li><a href="/login">Login</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a href="/admin">Admin</a></li>
					</sec:authorize>
				</ul>
				<div id="sb-search" class="sb-search">
					<form>
						<input class="sb-search-input"
							placeholder="Enter your search term..." type="text" value=""
							name="keyword" id="search"> <input
							class="sb-search-submit" type="submit" value=""> <span
							class="sb-icon-search"></span>
					</form>
				</div>
				<script src="/resources/js/classie.js"></script>
				<script src="/resources/js/uisearch.js"></script>
				<script>
					new UISearch(document.getElementById('sb-search'));
				</script>
				<!-- start smart_nav * -->
				<nav class="nav">
					<ul class="nav-list">
						<li class="nav-item"><a href="/">Home</a></li>
						<li class="nav-item"><a href="/about">About</a></li>
						<sec:authorize access="isAnonymous()">
							<li class="nav-item"><a href="/login">Login</a></li>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<li class="nav-item"><a href="/admin">Admin</a></li>
						</sec:authorize>
						<div class="clear"></div>
					</ul>
				</nav>
				<script type="text/javascript"
					src="/resources/js/responsive.menu.js"></script>
				<!-- end smart_nav * -->
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>