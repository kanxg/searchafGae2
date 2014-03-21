<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.amazon.AmazonPageJSP" %>
<%@ page import="kxg.searchaf.url.amazon.AmazonPage" %>
<%@ page import="kxg.searchaf.url.keepa.SearchKeepaJSP" %>
<%@ page import="kxg.searchaf.url.keepa.AmazonProduct" %>
<%@ page import="java.util.*" %>


<!DOCTYPE html>
<html lang="en">
    <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
   
  	  <div class="container">
 
 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/amazon/testurl" method="post">

      <div class="control-group">
    <label class="control-label" for="url">产品URL</label>
    <div class="controls">
      <input class="input-xxlarge"  type="text" name="url" id="url" >
    </div>
  </div>
  
        <div class="control-group">
    <label class="control-label" for="checksalerisAmazon">只看Amazon自营产品</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	 	<input type="checkbox" id="checksalerisAmazon" name="checksalerisAmazon" value="checksalerisAmazon">
    </div>
    </div>
  </div>
  
    <div class="control-group">
    <div class="controls">
      <button type="submit" id="addbutton" class="btn btn-success"><i class="fa fa-cog"></i>  查询产品当前信息</button>
    </div>
  </div>
       
 </form>
 
    <hr> 
    
    
    
    <%
    if(apage!=null){
   %>  
       <form class="form-horizontal">
       
        <%
    if(product!=null){
     %>
    	   		<div class="control-group">
    			<div class="controls">
					<canvas id="canvas" height="250" width="500"></canvas>
   			 </div>
   	</div>	
   	   <%
     }
   %>
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
   			<label class="control-label">自营产品：</label>
   			<div class="controls">
   			   <%=apage.salerisAmazon%> 
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
 	 $("#checksalerisAmazon").attr("checked", <%=checksalerisAmazon%>);
	 $('#checksalerisAmazon').parent().bootstrapSwitch('setState', <%=checksalerisAmazon%>);
 	 
 	     <%
    if(product!=null){
   %>  
   var lineChartData = {
			labels : [<%=product.getChartXLineDate()%>],
			datasets : [
				{
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "rgba(151,187,205,1)",
					pointStrokeColor : "#fff",
					data : [<%=product.getChartYLineDate()%>]
				}
			]
			
		}

	var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Line(lineChartData);
	
     <%
     }
   %>
   
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
