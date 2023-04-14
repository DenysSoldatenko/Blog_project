<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="article thumbnail">
	<img src="http://placehold.it/1000x400" alt="Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem.">
	<div class="data">
		<h3>Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem.</h3>
		<ul class="vertical large-horizontal menu">
			<li><i class="fi-folder"></i><a href="/news">Phasellus</a></li>
			<li><i class="fi-comments"></i>14 comments</li>
			<li><i class="fi-clock"></i>7 Sep 2015, 7:47</li>
			<li><i class="fi-eye"></i>Hits: 7&nbsp;168</li>
		</ul>
		<hr>
		<div class="content">
			<p>Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id,
				lorem.</p>
			<p>Pellentesque libero tortor, tincidunt et, tincidunt eget, semper nec, quam. Ut tincidunt tincidunt erat. Praesent nec nisl a purus
				blandit viverra quis gravida magna mi a libero. Morbi ac felis.</p>
			<p>Morbi ac felis. Sed augue ipsum, egestas nec, vestibulum et, malesuada adipiscing, dui.</p>
			<p></p>
			<p>Proin sapien ipsum, porta a, auctor quis, euismod ut, mi.</p>
		</div>
		<div class="row columns social">
			<img src="http://placehold.it/32x32?text=f" alt="social" /> 
			<img src="http://placehold.it/32x32?text=t" alt="social" /> 
			<img src="http://placehold.it/32x32?text=g" alt="social" /> 
			<img src="http://placehold.it/32x32?text=f" alt="social" /> 
			<img src="http://placehold.it/32x32?text=t" alt="social" /> 
			<img src="http://placehold.it/32x32?text=g" alt="social" />
		</div>
		<br>
		<div class="comments">
			<jsp:include page="../fragment/new-comment.jsp" />
			<div id="comments-list-container">
				<jsp:include page="../fragment/comments.jsp" />
			</div>
			<div id="comments-load-more-ctrl" class="row column text-center">
				<a href="#" class="button hollow expanded load-more-btn">Load More</a>
			</div>
		</div>
	</div>
</div>