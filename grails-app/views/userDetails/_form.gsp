<%@ page import="kxg.searchaf.UserDetails" %>

				<div class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="User Name" /></span>
					<span class="property-value" aria-labelledby="username-label">	<sec:username/></span>
				
				</div>
				<div class="fieldcontain">
					<span id="useremail-label" class="property-label"><g:message code="user.useremail.label" default="User Email Address" /></span>
					<span class="property-value" aria-labelledby="useremail-label">	 ${email}</span>
				
				</div>

<div class="fieldcontain ${hasErrors(bean: userDetailsInstance, field: 'weixin', 'error')} ">
	<label for="weixin">
		<g:message code="userDetails.weixin.label" default="Weixin" />
		
	</label>
	<g:textField name="weixin" value="${userDetailsInstance?.weixin}"/>
</div>

