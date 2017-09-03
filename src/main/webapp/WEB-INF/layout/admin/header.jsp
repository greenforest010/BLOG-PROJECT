<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<div class="container body">
	<div class="main_container">
		<div class="col-md-3 left_col">
			<div class="left_col scroll-view">
				<div class="navbar nav_title" style="border: 0;">
					<a href="/admin" class="site_title"><i class="fa fa-paw"></i> <span>GrowingITSkill</span></a>
				</div>

				<div class="clearfix"></div>

				<!-- menu profile quick info -->
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<sec:authentication property="principal.username" var="loginId" />
					<div class="profile">
						<div class="profile_pic">
							<img src="/resources/admin/images/img.jpg" alt="..."
								class="img-circle profile_img">
						</div>

						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${loginId}</h2>
						</div>
					</div>
				</sec:authorize>


				<!-- /menu profile quick info -->

				<br />

				<!-- sidebar menu -->
				<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
					<div class="menu_section">
						<h3>메뉴</h3>
						<ul class="nav side-menu">
							<li><a href="/admin/post"><i
									class="fa fa-pencil-square-o"></i>글</a></li>
							<li><a href="#"><i class="fa fa-comments"></i>댓글</a></li>
							<li><a href="/admin/category"><i class="fa fa-archive"></i>카테고리</a></li>
							<li><a href="/admin/tag"><i class="fa fa-tags"></i>태그</a></li>
							<li><a href="/admin/media"><i class="fa fa-picture-o"></i>미디어
									리소스</a></li>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<li><a href="/admin/about-edit"><i class="fa fa-picture-o"></i>블로그 소개 편집</a></li>
							</sec:authorize>
							<li><a href="/"><i class="fa fa-home"></i>블로그 페이지로 이동</a></li>
						</ul>
					</div>
				</div>
				<!-- /sidebar menu -->
			</div>
		</div>

		<!-- top navigation -->
		<div class="top_nav">
			<div class="nav_menu">
				<nav class="" role="navigation">
					<div class="nav toggle">
						<a id="menu_toggle"><i class="fa fa-bars"></i></a>
					</div>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="javascript:;"
							class="user-profile dropdown-toggle" data-toggle="dropdown"
							aria-expanded="false"> <img
								src="/resources/admin/images/img.jpg" alt=""> <sec:authentication
									property="principal.username" /> <span
								class=" fa fa-angle-down"></span>
						</a>
							<ul class="dropdown-menu dropdown-usermenu pull-right">
								<li><a href="/admin/profile">프로필</a></li>
								<li><a href="javascript:;"> <span
										class="badge bg-red pull-right">50%</span> <span>Settings</span>
								</a></li>
								<li><a href="javascript:;">Help</a></li>
								<li><a href="/logout"><i
										class="fa fa-sign-out pull-right"></i> 로그아웃</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- /top navigation -->