<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<title>GrowingITSkill</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- google webmaster -->
<meta name="google-site-verification" content="BSZPwiWKqIKaGroPJT3Sdq29cw3zhvQIObtCUUQCq8E" />

<!-- naver webmaster  -->
<meta name="naver-site-verification" content="4c67b605b799adb05834b5092cf3379269271b83"/>
<link rel="shortcut icon" type="image/x-icon" href="/resources/images/favicon.ico"/>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600'
	rel='stylesheet' type='text/css'>
<link href="/resources/css/style.css" rel="stylesheet" type="text/css"
	media="all" />

<link href="/resources/glyphicons-only-bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
	
<link rel="stylesheet" href="/resources/font-awesome/css/font-awesome.min.css">

<!-- jsTree  -->
<link rel="stylesheet"
	href="/resources/js/jsTree/dist/themes/default/style.min.css" />
	
<!-- highlight.js  -->
<link rel="stylesheet" href="/resources/js/highlight/styles/monokai.css" />

<script type="text/javascript" src="/resources/js/highlight/highlight.pack.js"></script>

<script>hljs.initHighlightingOnLoad();</script>

<!--  jquery plguin -->
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>

<!-- google analytics  -->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-90421095-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="content" />
	<tiles:insertAttribute name="footer" />
</body>
</html>