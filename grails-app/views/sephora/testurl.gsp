<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.sephora.SephoraPageJSP" %>
<%@ page import="kxg.searchaf.url.sephora.SephoraPage" %>
<%@ page import="java.util.*" %>
 
<%	
	 String ErrMsg="";
	 SephoraPage apage=null;
	 
     String url = request.getParameter("url");
	 if(url!=null){
 	      SephoraPageJSP jsp=new SephoraPageJSP();
 		 try {
 		 	apage=jsp.testUrl(url);
	 	} catch (Exception e) {
	 		ErrMsg=e.getMessage();
	 	}
 	 	
	 }else{
	 	url="";
	 }
	 
 
	 
%>


<!DOCTYPE html>
<html lang="en">
    <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
   
  	  <div class="container">
 
 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/sephora/testurl" method="post">

      <div class="control-group">
    <label class="control-label" for="url">产品URL</label>
    <div class="controls">
      <input type="text" name="url" id="url" style="width: 80%">
    </div>
  </div>
  
    <div class="control-group">
    <div class="controls">
      <button type="submit" id="addbutton" class="btn">测试</button>
    </div>
  </div>
       
 </form>
 
    <hr> 
    
    
    
    <%
    if(apage!=null){
   %>  
       <form class="form-horizontal">
   	<div class="control-group">
   			<label class="control-label">产品名称：</label>
   			<div class="controls"><%=apage.title%></div>
   	</div>
   	   	<div class="control-group">
   			<label class="control-label">价格：</label>
   			<div class="controls">
   			  <%=apage.price%> 
   			 </div>
   			 
   	</div>
   		<div class="control-group">
   			<label class="control-label">在售：</label>
   			<div class="controls">
   			   <%=apage.instock%> 
   			 </div>
   			 
   	</div>
 
   	 	<div class="control-group">
   			<label class="control-label">链接：</label>
   			<div class="controls">
   	 			<a target='_blank' href='<%=apage.getJspUrl()%>'><%=apage.url%> <a>
   			 </div>
   			 
   	</div>
  </form>
   <%
     }
   %>
   
 
 
  	  </div>
  

  
   </body>
</html>

<script language="javascript">
$( document ).ready(function(){
 	 $("#url").val("<%=url%>");
 	 
	 <%
	 	if(!"".equals(ErrMsg)){
	 %>
	 	$.globalMessenger().post("<%=ErrMsg%>");
	 <%}%>
	 
	  $("#form1").submit(function(){
		if($("#url").val()==""){
			$.globalMessenger().post("must input url!");
			return false;
		} 
         return true;
  });
	 	
});

 
 
</script>
