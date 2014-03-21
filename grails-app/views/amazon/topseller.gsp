<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.keepa.SearchKeepaJSP" %>
<%@ page import="kxg.searchaf.url.keepa.AmazonProduct" %>
<%@ page import="java.util.*" %>



<!DOCTYPE html>
<html lang="en">
    <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
   
  	  <div class="container">
 
 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/amazon/topseller" method="post">

      <div class="control-group">
    <label class="control-label" for="url">产品</label>
    <div class="controls">
       <select class="select" id="Category" name="Category" >
         <option value="FASHION TOPS">FASHION TOPS</option>
         <option value="LAYERING TEES & TANKS">LAYERING TEES & TANKS</option>
         <option value="GRAPHIC TEES">GRAPHIC TEES</option>
         <option value="SHIRTS">SHIRTS</option>
         <option value="POLOS">POLOS</option>
       </select>
    </div>
  </div>
  
     
  
    <div class="control-group">
    <div class="controls">
      <button type="submit" id="addbutton" class="btn">Query</button>
    </div>
  </div>
       
 </form>
 
    <hr> 
    
    
    
    <%
    if(productlist!=null){
   %>  
       <form class="form-horizontal">
    
  </form>
   <%
     }
   %>
   
  	  </div>
  

  
   </body>
</html>

<script language="javascript">
$( document ).ready(function(){
 	 $("#Category").val("<%=Category%>");
  	 
	 <%
	 	if(!"".equals(ErrMsg)){
	 %>
	 	$.globalMessenger().post("<%=ErrMsg%>");
	 <%}%>
	 
	  $("#form1").submit(function(){
         return true;
  });
	 	
});

 
 
</script>
