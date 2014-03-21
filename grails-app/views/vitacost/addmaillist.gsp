<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.vitacost.VitacostPageJSP" %>
<%@ page import="kxg.searchaf.url.vitacost.VitacostPage" %>
<%@ page import="java.util.*" %>
<%request.setCharacterEncoding("utf-8");%>  
 
<%	
	 String ErrMsg="";
     List<VitacostPage> pagelist=null;

     String mailaddress = request.getParameter("mailaddress");
     String url = request.getParameter("url");
     String prodname = request.getParameter("prodname");
     
	 if(mailaddress!=null){
	      String action = request.getParameter("action");
	      VitacostPageJSP jsp=new VitacostPageJSP();
	      
	 	if("add".equals(action)){
	 		 String expectPirce = request.getParameter("expectPirce");
	 		 ErrMsg=jsp.addNewMail(mailaddress,prodname,url,expectPirce);
	 		 if("exist".equals(ErrMsg)){
	 		 	ErrMsg="当前产品已经在您的监视列表中.";
	 		 }else if("notvitacost".equals(ErrMsg)){
	 		 	ErrMsg="当前只支持vitacost.com,请输入正确的链接!";
	 		 }else if("success".equals(ErrMsg)){
	 		 	ErrMsg="添加成功!";
	 		 }
	 	 }
	 	 
	 	 if("delete".equals(action)){
	 		 ErrMsg=jsp.deleteMail(mailaddress,url);
	 		 if("notexist".equals(ErrMsg)){
	 		 	ErrMsg="你没有监视这个URL.";
	 		 }else if("success".equals(ErrMsg)){
	 		 	ErrMsg="删除成功!";
	 		 }
	 	 }
 			pagelist=jsp.loadMail(mailaddress);
 	 	
	 }else{
	 	mailaddress="";
	 }
	 
	 if(url==null){
	 	url="";
	 }
	 
%>


<!DOCTYPE html>
<html lang="en">
    <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
   
  	  <div class="container">
 
 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/vitacost/addmaillist" method="post">
  <div class="control-group">
    <label class="control-label" for="mailaddress">Email</label>
    <div class="controls">
      <input type="text" name="mailaddress" id="mailaddress" value="">
            <button type="submit" id="loadbutton" class="btn">查询我已经添加的</button>
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="prodname">产品名称</label>
    <div class="controls">
      <input type="text" name="prodname" id="prodname">
    </div>
  </div>
  
      <div class="control-group">
    <label class="control-label" for="url">产品URL</label>
    <div class="controls">
      <input type="text" name="url" id="url" style="width: 100%">
    </div>
  </div>
  
        <div class="control-group">
    <label class="control-label" for="expectPirce">期望价格</label>
    <div class="controls">
      <input type="text" id="expectPirce" name="expectPirce">
    </div>
  </div>
  

    <div class="control-group">
    <div class="controls">
      <button type="submit" id="addbutton" class="btn">添加</button>
    </div>
  </div>
       
 </form>
  </div>
  
     <div class="container">
	    <table class="table table-bordered"  >
	           <thead>
	                <tr>
	                  <th style="width: 10%">产品名称</th>
	                  <th style="width: 50%">产品URL</th>
	                  <th style="width: 10%">期望价格</th>
	                  <th style="width: 20%">操作</th>
	                </tr>
	              </thead>
	              <tbody id="table1"></tbody>
	    </table>
 	</div>
  

  
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
   		$("#form1").attr("action", "<%=request.getContextPath()%>/vitacost/addmaillist?action=add");
   		var tem1=$("#expectPirce").val();
  		if(!isNumber(tem1)){
			$.globalMessenger().post("请输入一个数值!");
			return false;
		}
		if($("#url").val()==""){
			$.globalMessenger().post("must input url!");
			return false;
		}
   });
   
   $("#loadbutton").click(function() {
   		$("#form1").attr("action", "<%=request.getContextPath()%>/vitacost/addmaillist?action=load");
   		
   });
   
     <%
     if(pagelist!=null&&pagelist.size()!=0){
     for(int i=0;i<pagelist.size();i++){
       VitacostPage apage=pagelist.get(i);
   %>
   
   $("#table1").append("<tr><td><%=apage.prodname%></td><td><a target='_blank' href='<%=apage.getJspUrl()%>'><%=apage.url%><a></td><td><%=apage.expectPirce%></td><td><button type='submit' id='querybutton<%=i%>' class='btn'>在线查询</button><button type='submit' id='deletebutton<%=i%>' class='btn'>删除</button></td></tr>");
  
   $("#deletebutton<%=i%>").click(function() {
   		$("#mailaddress").val("<%=apage.mailaddress%>");
   		$("#url").val("<%=apage.url%>");
   		$("#form1").attr("action", "<%=request.getContextPath()%>/vitacost/addmaillist?action=delete");
   		$("#form1").submit();
   });
   
   $("#querybutton<%=i%>").click(function() {
   		$("#url").val("<%=apage.url%>");
   		$("#form1").attr("action", "<%=request.getContextPath()%>/vitacost/testurl");
   		$("#form1").submit();
    });
  
  <%
     }
   }
  %>
	 	
});

 
function isNumber(oNum) 
{ 
	if(!oNum) return false; 
	var strP=/^\d+(\.\d+)?$/; 
	if(!strP.test(oNum)) return false; 
	try{ 
		if(parseFloat(oNum)!=oNum) return false; 
	} 
	catch(ex) 
	{ 
		return false; 
	} 
	return true; 
}
 
</script>
