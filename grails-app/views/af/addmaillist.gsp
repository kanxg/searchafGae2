<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.af.AfMailListJSP" %>
<%@ page import="kxg.searchaf.url.af.AfMailList" %>
<%@ page import="kxg.searchaf.util.TimeUtli" %>
<%request.setCharacterEncoding("utf-8");%>  

<!DOCTYPE html>
<html lang="en">

   <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
    
<div class="container">
       <div class="hero-unit">
 
		
				 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/af/addmaillist" method="post">
				  <div class="control-group">
				    <label class="control-label" for="mailaddress">Email</label>
				    <div class="controls">
				      <input type="text" name="mailaddress" id="mailaddress" value="">
				    </div>
				  </div>
				  
				      <div class="control-group">
				    <label class="control-label" for="userType">用户类型</label>
				    <div class="controls">
				      <input type="text" name="userType" id="userType"  readonly="true">
				    </div>
				  </div>
				  
				    <div class="control-group">
				    <label class="control-label" for="valideTime">到期时间</label>
				    <div class="controls">
				      <input type="text" name="valideTime" id="valideTime"  readonly="true">
				    </div>
				  </div>
				
				    <div class="control-group">
				    <div class="controls">
				      <button type="submit" id="addbutton" class="btn btn-success">添加</button>
				      <button type="submit" id="updatebutton" class="btn btn-success">配置添加的邮箱</button>
				    </div>
				  </div>
				       
				 </form>		  
		 </div>
 </div>
 
 
   </body>
</html>

<script language="javascript">
$( document ).ready(function(){
	 $("#mailaddress").val("<%=mailaddress%>");
	 $("#valideTime").val("<%=valideTime%>");
	 $("#userType").val("<%=userType%>");
	 
	 
	 <%
	 	if(!"".equals(ErrMsg)){
	 %>
	 	$.globalMessenger().post("<%=ErrMsg%>");
	 <%}%>
	 
	  $("#form1").submit(function(){
		if($("#mailaddress").val()==""){
			$.globalMessenger().post("must input email!");
			return false;
		} 
         return true;
  });
  
 
   $("#addbutton").click(function() {
   		$("#form1").attr("action", "<%=request.getContextPath()%>/af/addmaillist?action=add");
   });
   
    $("#updatebutton").click(function() {
   		$("#form1").attr("action", "<%=request.getContextPath()%>/af/updatemaillist?action=add");
   });
	 	
});
 
</script>
