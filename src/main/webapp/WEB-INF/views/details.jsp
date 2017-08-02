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
					${postVO.memberVO.loginId},
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${postVO.published}" />
					<div class="det_text">${postVO.content}</div>

					<div id="disqus_thread"></div>
					<script>
						/**
						 *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
						 *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
						var disqus_config = function() {
							var url = window.location.href;
							var path = window.location.pathname;
							
							var identifier = path.split("/")[2];
							
							console.log("url: " + url + ", identifier: " + identifier);
							
							this.page.url = url; // Replace PAGE_URL with your page's canonical URL variable
							this.page.identifier = identifier; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
						};
						(function() { // DON'T EDIT BELOW THIS LINE
							var d = document, s = d.createElement('script');
							s.src = 'https://growingitskill.disqus.com/embed.js';
							s.setAttribute('data-timestamp', +new Date());
							(d.head || d.body).appendChild(s);
						})();
					</script>
					<noscript>
						Please enable JavaScript to view the <a
							href="https://disqus.com/?ref_noscript">comments powered by
							Disqus.</a>
					</noscript>
					<div class="clear"></div>
				</div>
				<!-- end details -->
			</div>


		</div>
	</div>
</div>
