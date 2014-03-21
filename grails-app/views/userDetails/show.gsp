
<%@ page import="kxg.searchaf.UserDetails" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userDetails.label', default: 'UserDetails')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-userDetails" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-userDetails" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list userDetails">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="User Name" /></span>
					<span class="property-value" aria-labelledby="username-label">	<sec:username/></span>
				
				</li>
				<li class="fieldcontain">
					<span id="useremail-label" class="property-label"><g:message code="user.useremail.label" default="User Email Address" /></span>
					<span class="property-value" aria-labelledby="useremail-label">	 ${email}</span>
				
				</li>
				
				<li class="fieldcontain">
					<span id="weixin-label" class="property-label"><g:message code="userDetails.weixin.label" default="Weixin" /></span>
					<g:if test="${userDetailsInstance?.weixin}">
						<span class="property-value" aria-labelledby="weixin-label"><g:fieldValue bean="${userDetailsInstance}" field="weixin"/></span>
					</g:if>
				</li>
				
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${userDetailsInstance?.id}" />
					<g:link class="edit" action="edit" id="${userDetailsInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
 				</fieldset>
			</g:form>
		</div>
	</body>
</html>
