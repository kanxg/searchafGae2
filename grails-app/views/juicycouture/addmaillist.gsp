<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.juicycouture.JuicycoutureMailListJSP" %>
<%@ page import="kxg.searchaf.url.juicycouture.JuicycoutureMailList" %>
<%@ page import="kxg.searchaf.util.TimeUtli" %>
<%request.setCharacterEncoding("utf-8");%>  

<%	
	 String ErrMsg="";
     JuicycoutureMailList afMailList=new JuicycoutureMailList();
     String  valideTime="";
     String userType="";
     String mailaddress = request.getParameter("mailaddress");
	 if(mailaddress!=null){
	      String action = request.getParameter("action");
	 	if("add".equals(action)){
	 		 JuicycoutureMailListJSP jsp=new JuicycoutureMailListJSP();
	 		 ErrMsg=jsp.addNewMail(mailaddress);
	 		 if("exist".equals(ErrMsg)){
	 		 	ErrMsg="当前邮箱已经在服务器列表中.";
	 		 }else if("success".equals(ErrMsg)){
	 		 	ErrMsg="添加成功.";
	 		 }
	 	 }
			JuicycoutureMailListJSP jsp=new JuicycoutureMailListJSP();
			JuicycoutureMailList afMailList1=jsp.loadMail(mailaddress);
			if(afMailList1!=null){
				afMailList=afMailList1;
				valideTime=TimeUtli.dateToString(afMailList.valideTime);
				userType=afMailList.userType;
			}
	 	
	 }else{
	 	mailaddress="";
	 }
	 
%>


<!DOCTYPE html>
<html lang="en">

   <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
    
<div class="container">
       <div class="hero-unit">
 
		
				 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/juicycouture/addmaillist" method="post">
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
				      <button type="submit" id="addbutton" class="btn">添加</button>
				      <button type="submit" id="updatebutton" class="btn">配置添加的邮箱</button>
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
   		$("#form1").attr("action", "<%=request.getContextPath()%>/juicycouture/addmaillist?action=add");
   });
   
    $("#updatebutton").click(function() {
   		$("#form1").attr("action", "<%=request.getContextPath()%>/juicycouture/updatemaillist?action=add");
   });
	 	
});
 
</script>
