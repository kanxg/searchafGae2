<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.tommy.TommyMailListJSP" %>
<%@ page import="kxg.searchaf.url.tommy.TommyMailList" %>
<%@ page import="kxg.searchaf.util.TimeUtli" %>
<%request.setCharacterEncoding("utf-8");%>  

<%	
	 String ErrMsg="";
     TommyMailList afMailList=new TommyMailList();
     String  valideTime="";
     String userType="";
     String mailaddress = request.getParameter("mailaddress");
	 if(mailaddress!=null){
	      String action = request.getParameter("action");
	 	if("update".equals(action)){
	 		 boolean warningMen=false;
	 		 if(request.getParameterValues("warningMen")!=null){
	 		 	 warningMen=true; 		 
	 		 }
	 		 String mencheckingSaleDiscount = request.getParameter("mencheckingSaleDiscount");
	 		
	 		 boolean warningWomen=false;
	 		 if(request.getParameterValues("warningWomen")!=null){
	 		 	 warningWomen=true; 		 
	 		 }
	 		 String womencheckingSaleDiscount = request.getParameter("womencheckingSaleDiscount");
	 		 
	 		 boolean warningBoy=false;
	 		 if(request.getParameterValues("warningBoy")!=null){
	 		 	 warningBoy=true; 		 
	 		 }
	 		 String boycheckingSaleDiscount = request.getParameter("boycheckingSaleDiscount");
	 		 
	 		 boolean warningGirl=false;
	 		 if(request.getParameterValues("warningGirl")!=null){
	 		 	 warningGirl=true; 		 
	 		 }
	 		 String girlcheckingSaleDiscount = request.getParameter("girlcheckingSaleDiscount");
	 		 
	 		 TommyMailListJSP jsp=new TommyMailListJSP();
	 		 ErrMsg=jsp.updateMail(mailaddress, warningMen,	 mencheckingSaleDiscount,  warningWomen,
			 womencheckingSaleDiscount,  warningBoy, boycheckingSaleDiscount,  warningGirl,	 girlcheckingSaleDiscount);
	 		 if("success".equals(ErrMsg)){
	 		 	ErrMsg="更新成功.";
	 		 }
	 	}
			TommyMailListJSP jsp=new TommyMailListJSP();
			TommyMailList afMailList1=jsp.loadMail(mailaddress);
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


 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/tommy/updatemaillist" method="post">
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
    <label class="control-label" for="warningMen">男装</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningMen" name="warningMen" value="warningMen">
    </div></div>
  </div> 
  

  <div class="control-group">
    <label class="control-label" for="mencheckingSaleDiscount">男装告警折扣</label>
    <div class="controls">
      <input type="text" id="mencheckingSaleDiscount" name="mencheckingSaleDiscount">
    </div>
  </div>
 
    <div class="control-group">
    <label class="control-label" for="warningWomen">女装</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningWomen" name="warningWomen" value="warningWomen">
    </div></div>
  </div> 
  

  <div class="control-group">
    <label class="control-label" for="womencheckingSaleDiscount">女装告警折扣</label>
    <div class="controls">
      <input type="text" id="womencheckingSaleDiscount" name="womencheckingSaleDiscount">
    </div>
  </div>
  
     <div class="control-group">
    <label class="control-label" for="warningBoy">男童装</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningBoy" name="warningBoy" value="warningBoy">
    </div></div>
  </div> 
  

  <div class="control-group">
    <label class="control-label" for="boycheckingSaleDiscount">男童装告警折扣</label>
    <div class="controls">
      <input type="text" id="boycheckingSaleDiscount" name="boycheckingSaleDiscount">
    </div>
  </div>
  
     <div class="control-group">
    <label class="control-label" for="warningGirl">女童装</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningGirl" name="warningGirl" value="warningGirl">
    </div></div>
  </div> 
  

  <div class="control-group">
    <label class="control-label" for="girlcheckingSaleDiscount">女童装告警折扣</label>
    <div class="controls">
      <input type="text" id="girlcheckingSaleDiscount" name="girlcheckingSaleDiscount">
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
	 $("#warningMen").attr("checked", <%=afMailList.warningMen%>);
	 $('#warningMen').parent().bootstrapSwitch('setState', <%=afMailList.warningMen%>);

	 $("#warningWomen").attr("checked", <%=afMailList.warningWomen%>);
	 $('#warningWomen').parent().bootstrapSwitch('setState', <%=afMailList.warningWomen%>);

	 $("#warningBoy").attr("checked", <%=afMailList.warningBoy%>);
	 $('#warningBoy').parent().bootstrapSwitch('setState', <%=afMailList.warningBoy%>);

	 $("#warningGirl").attr("checked", <%=afMailList.warningGirl%>);
	 $('#warningGirl').parent().bootstrapSwitch('setState', <%=afMailList.warningGirl%>);
	 
	 $("#mencheckingSaleDiscount").val("<%=afMailList.mencheckingSaleDiscount%>");
	 $("#womencheckingSaleDiscount").val("<%=afMailList.womencheckingSaleDiscount%>");
	 $("#boycheckingSaleDiscount").val("<%=afMailList.boycheckingSaleDiscount%>");
	 $("#girlcheckingSaleDiscount").val("<%=afMailList.girlcheckingSaleDiscount%>");
	 
	  
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
  	  $("#form1").attr("action", "<%=request.getContextPath()%>/tommy/updatemaillist?action=load");
   });
  
   $("#updatebutton").click(function() {
  	var tem1=$("#mencheckingSaleDiscount").val();
  	if(!isNumber(tem1)){
		$.globalMessenger().post("男装折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem1)<0.01 || Number(tem2)>1){
		$.globalMessenger().post("男装折扣，请输入0至1之间的一个数值");
		return false;
	}
	var tem2=$("#womencheckingSaleDiscount").val();
  	if(!isNumber(tem2)){
		$.globalMessenger().post("女装折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem2)<0.01 || Number(tem2)>1){
		$.globalMessenger().post("女装折扣，请输入0至1之间的一个数值");
		return false;
	}
		var tem3=$("#boycheckingSaleDiscount").val();
  	if(!isNumber(tem3)){
		$.globalMessenger().post("男童装折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem3)<0.01 || Number(tem2)>1){
		$.globalMessenger().post("男童装折扣，请输入0至1之间的一个数值");
		return false;
	}
		var tem4=$("#girlcheckingSaleDiscount").val();
  	if(!isNumber(tem4)){
		$.globalMessenger().post("女童装折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem4)<0.01 || Number(tem2)>1){
		$.globalMessenger().post("女他装折扣，请输入0至1之间的一个数值");
		return false;
	}
	
   		$("#form1").attr("action", "<%=request.getContextPath()%>/tommy/updatemaillist?action=update");
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
