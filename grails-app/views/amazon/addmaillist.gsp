<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.amazon.AmazonPageJSP" %>
<%@ page import="kxg.searchaf.url.amazon.AmazonPage" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
  <head>
   <g:render template="headmeta" />
   <g:render template="headcss" />
   <g:render template="headjs" />

 
  </head>
  <body>
    
    <g:render template="menu" />

    <g:render template="addmaillist_body" />
    
    <g:render template="foot" />

    <g:render template="footjs" />

  </body>


</html>

<script language="javascript">
$( document ).ready(function(){
 	 $("#mailaddress").val("<%=mailaddress%>");
   $("#url").val("<%=url%>");
 	 
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
   		$("#form1").attr("action", "<%=request.getContextPath()%>/amazon/addmaillist?type=add");
   		var tem1=$("#expectPirce").val();
  		if(!isNumber(tem1)){
			$.globalMessenger().post("请输入期望价格!");
			return false;
		  }
       if($("#url").val()==""){
      $.globalMessenger().post("must input url!");
      return false;
     }
 
   });
   
   $("#loadbutton").click(function() {
   		$("#form1").attr("action", "<%=request.getContextPath()%>/amazon/addmaillist?type=load");
   		
   });
   
     <%
     if(pagelist!=null&&pagelist.size()!=0){
     for(int i=0;i<pagelist.size();i++){
       AmazonPage apage=pagelist.get(i);
   %>

  $("#tbody1").append("<tr><td><%=apage.prodname%></td><td><%=apage.getUIshowforchecksalerisAmazon()%></td><td><a target='_blank' href='<%=apage.getJspUrl()%>'>打开Amazon链接<a></td><td><%=apage.expectPirce%></td><td><div class='btn-group'><button type='submit' id='querybutton<%=i%>' class='btn btn-info'>产品价格历史</button></div><div class='btn-group'><button type='submit' id='deletebutton<%=i%>' class='btn btn-danger'><i class='fa fa-trash-o fa-lg'></i> 删除</button></div></td></tr>"); 
  
   $("#deletebutton<%=i%>").click(function() {
   		$("#url").val("<%=apage.url%>");
   		$("#form1").attr("action", "<%=request.getContextPath()%>/amazon/addmaillist?type=delete");
   		$("#form1").submit();
   });
   
   $("#querybutton<%=i%>").click(function() {
   		$("#url").val("<%=apage.url%>");
   		$("#checksalerisAmazon").attr("checked", <%=apage.checksalerisAmazon%>);
   		$("#form1").attr("action", "<%=request.getContextPath()%>/amazon/testurl");
   		$("#form1").submit();
    });
  
  <%
     }
   }
  %>

   $("#table1").tablesorter(); 
  
});
 
</script>
