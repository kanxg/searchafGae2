<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.util.StringUtli" %>
<%@ page import="kxg.debug.Groovydebug" %>
<%request.setCharacterEncoding("utf-8");%>  

<%	
 
     String code = request.getParameter("code");
     String output="";
     String exception="";
	 if(code!=null){
	 	  code=StringUtli.HtmlEncode(code);
	 	  // System.out.println(code);
	 	   Groovydebug debug=new Groovydebug();
	 	   Map map=debug.eval(code);
	 	   output=(String)map.get("output");
	 	   //System.out.println(output);
	 	   exception=(String)map.get("exception");
	 	   //System.out.println(exception);
	 	   
	 	   
 	 }else{
	 		code="";
	 }
	 
%>


<!DOCTYPE html>
<html lang="en">
   <g:render template="head" /> 
 
  <body>
<g:render template="top" /> 
     
 
 
		
				 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/groovy/debug" method="post">
				  <div class="control-group">
				    <label class="control-label" for="code">code</label>
				    <div class="controls">
				      <textarea type="text" name="code" id="code"  rows="8" style="width: 80%"></textarea>
				    </div>
				  </div>
				  
				    
				
				    <div class="control-group">
				    <div class="controls">
				      <button type="submit" id="addbutton" class="btn">调试</button>
				    </div>
				  </div>
				  
				  	  <div class="control-group">
				    <label class="control-label" for="output">output</label>
				    <div class="controls">
				      <textarea type="text" name="output" id="output"  rows="8" style="width: 80%"></textarea>
				    </div>
				  </div>
				  
				  	  <div class="control-group">
				    <label class="control-label" for="exception">exception</label>
				    <div class="controls">
				      <textarea type="text" name="exception" id="exception"  rows="8" style="width: 80%"></textarea>
				    </div>
				  </div>
				 </form>	
 
 
   </body>
</html>

<script language="javascript">
$( document ).ready(function(){
 	$("#code").append("<%=code%>");
 
	 
	<%
	 	if(!"".equals(output)){
	 %>
 		$("#output").append("<%=output%>");
	 <%}%>
	 
	<%
	 	if(!"".equals(exception)&&exception!=null){
	 %>
 		 $("#exception").append("<%=exception%>");
	 <%}%>
	 
	  $("#form1").submit(function(){
 		if($("#code").val()==""){
			$.globalMessenger().post("must input code!");
			return false;
		} 
         return true;
 	  }); 
	 	
});
</script>
