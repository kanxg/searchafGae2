<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.victoriassecret.VictoriassecretMailListJSP" %>
<%@ page import="kxg.searchaf.url.victoriassecret.VictoriassecretMailList" %>
<%@ page import="kxg.searchaf.util.TimeUtli" %>
<%request.setCharacterEncoding("utf-8");%>  

<%	
	 String ErrMsg="";
     VictoriassecretMailList afMailList=new VictoriassecretMailList();
     String  valideTime="";
     String userType="";
     String mailaddress = request.getParameter("mailaddress");
	 if(mailaddress!=null){
	      String action = request.getParameter("action");
	 	if("update".equals(action)){
			 
	 		 boolean warningWomen=false;
	 		 if(request.getParameterValues("warningWomen")!=null){
	 		 	 warningWomen=true; 		 
	 		 }

	 		 String womencheckingSaleDiscount = request.getParameter("womencheckingSaleDiscount");
	 		
	 		 VictoriassecretMailListJSP jsp=new VictoriassecretMailListJSP();
	 		 ErrMsg=jsp.updateMail(mailaddress,warningWomen,womencheckingSaleDiscount);
	 		 if("success".equals(ErrMsg)){
	 		 	ErrMsg="更新成功.";
	 		 }
	 	}
			VictoriassecretMailListJSP jsp=new VictoriassecretMailListJSP();
			VictoriassecretMailList afMailList1=jsp.loadMail(mailaddress);
			if(afMailList1!=null){
				afMailList=afMailList1;
				valideTime=TimeUtli.dateToString(afMailList.valideTime);
				userType=afMailList.userType;
			}else{
				ErrMsg="email不存在，请购买软件，或在试用页面自行添加！";
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


 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/victoriassecret/updatemaillist" method="post">
  <div class="control-group">
    <label class="control-label" for="mailaddress">Email</label>
    <div class="controls">
      <input type="text" name="mailaddress" id="mailaddress" value="">
      <button type="submit"  id="loadbutton" class="btn">加载配置</button>
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
    <label class="control-label" for="warningWomen">是否邮件提醒</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningWomen" name="warningWomen" value="warningWomen">
    </div></div>
  </div> 
  

  <div class="control-group">
    <label class="control-label" for="womencheckingSaleDiscount">告警折扣</label>
    <div class="controls">
      <input type="text" id="womencheckingSaleDiscount" name="womencheckingSaleDiscount">
    </div>
  </div>
 
    <div class="control-group">
    <div class="controls">
      <button type="submit" id="updatebutton" class="btn">更新</button>
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
	 $("#userType").val("<%=userType%>");
	 $("#valideTime").val("<%=valideTime%>");
	 $("#warningWomen").attr("checked", <%=afMailList.warningWomen%>);
	 $('#warningMen').parent().bootstrapSwitch('setState', <%=afMailList.warningMen%>);
	 
	 $("#womencheckingSaleDiscount").val("<%=afMailList.womencheckingSaleDiscount%>");
	 
	  
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
  
   $("#loadbutton").click(function() {
  	  $("#form1").attr("action", "<%=request.getContextPath()%>/victoriassecret/updatemaillist?action=load");
   });
  
   $("#updatebutton").click(function() {
  
	var tem2=$("#womencheckingSaleDiscount").val();
  	if(!isNumber(tem2)){
		$.globalMessenger().post("S区折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem2)<0.01 || Number(tem2)>1){
		$.globalMessenger().post("S区折扣，请输入0至1之间的一个数值");
		return false;
	}
	
	
   		$("#form1").attr("action", "<%=request.getContextPath()%>/victoriassecret/updatemaillist?action=update");
   });
	 	
});		<!-- end ready-->


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
