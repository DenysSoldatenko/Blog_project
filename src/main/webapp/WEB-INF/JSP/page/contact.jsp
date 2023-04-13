<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<div class="callout">
	<h3 class="text-center">Contact form</h3>
	<hr />
	<form action="/contact" method="post" data-abide novalidate class="contact-form">
		<div data-abide-error class="small-12 medium-8 columns small-centered"
			style="padding: 0 5px; display: none;">
			<div class="alert callout">
				<p>
					<i class="fi-alert alert"></i> There are some errors in your form.
				</p>
			</div>
		</div>
		<div class="row">
			<div class="small-12 medium-8 columns small-centered">
				<label> <input type="text" required name="name"
					placeholder="Your name"> <span class="form-error">
						Name is required. </span>
				</label>
			</div>
		</div>
		<div class="row">
			<div class="small-12 medium-8 columns small-centered">
				<label> <input type="email" required name="email"
					placeholder="Your email"> <span class="form-error">
						Email is invalid. </span>
				</label>
			</div>
		</div>
		<div class="row">
			<div class="small-12 medium-8 columns small-centered">
				<label> <textarea name="text" required
						style="height: 120px;" placeholder="Your message"></textarea> <span
					class="form-error"> Message is required. </span>
				</label>
			</div>
		</div>
		<div class="row  columns">
			<button class="button float-center" type="submit" value="Submit">Submit</button>
		</div>
	</form>
</div>