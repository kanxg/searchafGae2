<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.hollisterco.HollistercoMailListJSP" %>
<%@ page import="kxg.searchaf.url.hollisterco.HollistercoMailList" %>
<%@ page import="kxg.searchaf.util.TimeUtli" %>
<%request.setCharacterEncoding("utf-8");%>  

<%	
	 String ErrMsg="";
     HollistercoMailList hollistercoMailList=new HollistercoMailList();
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
	 		 boolean warningWomen=false;
	 		 if(request.getParameterValues("warningWomen")!=null){
	 		 	 warningWomen=true; 		 
	 		 }
	 		 boolean warningCode=false;
	 		 if(request.getParameterValues("warningCode")!=null){
	 		 	 warningCode=true; 		 
	 		 }
 	 		 boolean mencheckingClearance=false;
	 		 if(request.getParameterValues("mencheckingClearance")!=null){
	 		 	 mencheckingClearance=true; 		 
	 		 }
	 		 boolean womencheckingClearance=false;
	 		 if(request.getParameterValues("womencheckingClearance")!=null){
	 		 	 womencheckingClearance=true; 		 
	 		 }
	 		 boolean mencheckingSale=false;
	 		 if(request.getParameterValues("mencheckingSale")!=null){
	 		 	 mencheckingSale=true; 		 
	 		 }
	 		 boolean womencheckingSale=false;
	 		 if(request.getParameterValues("womencheckingSale")!=null){
	 		 	 womencheckingSale=true; 		 
	 		 }
	 		 boolean mencheckingRegular=false;
	 		 if(request.getParameterValues("mencheckingRegular")!=null){
	 		 	 mencheckingRegular=true; 		 
	 		 }
	 		 boolean womencheckingRegular=false;
	 		 if(request.getParameterValues("womencheckingRegular")!=null){
	 		 	 womencheckingRegular=true; 		 
	 		 }
	 		 boolean mencheckingSecretSale=false;
	 		 if(request.getParameterValues("mencheckingSecretSale")!=null){
	 		 	 mencheckingSecretSale=true; 		 
	 		 }
	 		 boolean womencheckingSecretSale=false;
	 		 if(request.getParameterValues("womencheckingSecretSale")!=null){
	 		 	 womencheckingSecretSale=true; 		 
	 		 }
	 		 String mencheckingClearanceDiscount = request.getParameter("mencheckingClearanceDiscount");
	 		 String womencheckingClearanceDiscount = request.getParameter("womencheckingClearanceDiscount");
	 		 String mencheckingSaleDiscount = request.getParameter("mencheckingSaleDiscount");
	 		 String womencheckingSaleDiscount = request.getParameter("womencheckingSaleDiscount");
	 		 String[] mencheckingCategory = request.getParameterValues("mencheckingCategory");
	 		 String[] womencheckingCategory = request.getParameterValues("womencheckingCategory");
	 		 String[] mencheckingSize = request.getParameterValues("mencheckingSize");
	 		 String[] womencheckingSize = request.getParameterValues("womencheckingSize");
	 		 
	 		 HollistercoMailListJSP jsp=new HollistercoMailListJSP();
	 		 ErrMsg=jsp.updateMail(mailaddress,warningMen,warningWomen,warningCode,
	 		 mencheckingRegular,mencheckingSecretSale,mencheckingClearance,
	 		 mencheckingSale,mencheckingClearanceDiscount,mencheckingSaleDiscount,
	 		 mencheckingCategory,mencheckingSize,
	 		 womencheckingRegular,womencheckingSecretSale,womencheckingClearance,
	 		 womencheckingSale,womencheckingClearanceDiscount,womencheckingSaleDiscount,
	 		 womencheckingCategory,womencheckingSize);
	 		 if("success".equals(ErrMsg)){
	 		 	ErrMsg="更新成功.";
	 		 }
	 	}
			HollistercoMailListJSP jsp=new HollistercoMailListJSP();
			HollistercoMailList hollistercoMailList1=jsp.loadMail(mailaddress);
			if(hollistercoMailList1!=null){
				hollistercoMailList=hollistercoMailList1;
				valideTime=TimeUtli.dateToString(hollistercoMailList.valideTime);
				userType=hollistercoMailList.userType;
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


 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/hco/updatemaillist" method="post">
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
    <label class="control-label" for="warningCode">新折扣码提醒</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningCode" name="warningCode" value="warningCode">
    </div> </div>
  </div> 
  
   <hr>
   
   <div class="control-group">
    <label class="control-label" for="warningMen">男士区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningMen" name="warningMen" value="warningMen">
    </div> </div>
  </div> 
  
  <div class="control-group">
    <label class="control-label" for="mencheckingRegular">正价区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="mencheckingRegular" name="mencheckingRegular" value="mencheckingRegular">
    </div> </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="mencheckingSale">S区</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="mencheckingSale" name="mencheckingSale" value="mencheckingSale">
    </div> </div>
    
  </div>   
  <div class="control-group">
    <label class="control-label" for="mencheckingSaleDiscount">S区告警折扣</label>
    <div class="controls">
      <input type="text" id="mencheckingSaleDiscount" name="mencheckingSaleDiscount">
    </div>
  </div>
  
  
  <div class="control-group">
    <label class="control-label" for="mencheckingClearance">C区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="mencheckingClearance" name="mencheckingClearance" value="mencheckingClearance">
    </div> </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="mencheckingClearanceDiscount">C区告警折扣</label>
    <div class="controls">
      <input type="text" id="mencheckingClearanceDiscount" name="mencheckingClearanceDiscount">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="mencheckingSecretSale">Secret Sale区</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="mencheckingSecretSale" name="mencheckingSecretSale" value="mencheckingSecretSale">
    </div> </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="mencheckingCategory">选择分类</label>
    <div class="controls">
     <select id="mencheckingCategory" name="mencheckingCategory" multiple="multiple" size=15>
		  <option value="T-SHIRTS">T-SHIRTS</option>
		  <option value="TANKS">TANKS</option>
		  <option value="POLOS">POLOS</option>
		  <option value="SHIRTS">SHIRTS</option>
		  <option value="HOODIES & SWEATSHIRTS">HOODIES & SWEATSHIRTS</option>
		  <option value="SWEATERS">SWEATERS</option>
		  <option value="OUTERWEAR">OUTERWEAR</option>
		  <option value="SHORTS">SHORTS</option>
		  <option value="JEANS">JEANS</option>
		  <option value="SWEATPANTS">SWEATPANTS</option>
		  <option value="PANTS">PANTS</option>
		  <option value="SWIM">SWIM</option>
		  <option value="FLIP FLOPS">FLIP FLOPS</option>
		  <option value="ACCESSORIES">ACCESSORIES</option>
		  <option value="UNDERWEAR">UNDERWEAR</option>
		</select>
    </div>
  </div>
  
    <div class="control-group">
    <label class="control-label" for="mencheckingSize">选择尺码</label>
    <div class="controls">
     <select id="mencheckingSize" name="mencheckingSize"  multiple="multiple" size=34>
		  <option value="XS">XS</option>
		  <option value="S">S</option>
		  <option value="M">M</option>
		  <option value="L">L</option>
		  <option value="XL">XL</option>
		  <option value="XXL">XXL</option>
		  <option value="28 X 30">28 X 30</option>
		  <option value="28 X 32">28 X 32</option>
		  <option value="30 X 30">30 X 30</option>
		  <option value="30 X 32">30 X 32</option>
		  <option value="30 X 34">30 X 34</option>
		  <option value="31 X 30">31 X 30</option>
		  <option value="31 X 32">31 X 32</option>
		  <option value="31 X 34">31 X 34</option>
		  <option value="32 X 30">32 X 30</option>
		  <option value="32 X 32">32 X 32</option>
		  <option value="32 X 34">32 X 34</option>
		  <option value="33 X 32">33 X 32</option>
		  <option value="33 X 34">33 X 34</option>
		  <option value="34 X 32">34 X 32</option>
		  <option value="34 X 34">34 X 34</option>
		  <option value="36 X 32">36 X 32</option>
		  <option value="36 X 34">36 X 34</option>
		  <option value="28">28</option>
		  <option value="30">30</option>
		  <option value="31">31</option>
		  <option value="32">32</option>
		  <option value="33">33</option>
		  <option value="34">34</option>
		  <option value="36">36</option>
		  <option value="38">38</option>
		  <option value="1 SIZE">1 SIZE</option>
		  <option value="S/M">S/M</option>
		  <option value="L/XL">L/XL</option>
		</select>
    </div>
  </div>
  
   <hr> 
   
   <div class="control-group">
    <label class="control-label" for="warningWomen">女士区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningWomen" name="warningWomen" value="warningWomen">
    </div> </div>
  </div> 
  
 <div class="control-group">
    <label class="control-label" for="womencheckingRegular">正价区</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="womencheckingRegular" name="womencheckingRegular" value="womencheckingRegular">
    </div> </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="womencheckingSale">S区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="womencheckingSale" name="womencheckingSale" value="womencheckingSale">
    </div> </div>
    
  </div>   
  <div class="control-group">
    <label class="control-label" for="womencheckingSaleDiscount">S区告警折扣</label>
    <div class="controls">
      <input type="text" id="womencheckingSaleDiscount" name="womencheckingSaleDiscount">
    </div>
  </div>
  
  
  <div class="control-group">
    <label class="control-label" for="womencheckingClearance">C区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="womencheckingClearance" name="womencheckingClearance" value="womencheckingClearance">
    </div> </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="womencheckingClearanceDiscount">C区告警折扣</label>
    <div class="controls">
      <input type="text" id="womencheckingClearanceDiscount" name="womencheckingClearanceDiscount">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="womencheckingSecretSale">Secret Sale区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="womencheckingSecretSale" name="womencheckingSecretSale" value="womencheckingSecretSale">
    </div> </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="womencheckingCategory">选择分类</label>
    <div class="controls">
     <select id="womencheckingCategory" name="womencheckingCategory" multiple="multiple" size=19>
		  <option value="FASHION TOPS">FASHION TOPS</option>
		  <option value="LAYERING TEES & TANKS">LAYERING TEES & TANKS</option>
		  <option value="GRAPHIC TEES">GRAPHIC TEES</option>
		  <option value="SHIRTS">SHIRTS</option>
		  <option value="POLOS">POLOS</option>
		  <option value="HOODIES & SWEATSHIRTS">HOODIES & SWEATSHIRTS</option>
		  <option value="SWEATERS">SWEATERS</option>
		  <option value="OUTERWEAR">OUTERWEAR</option>		  
		  <option value="SHORTS">SHORTS</option>
		  <option value="JEANS">JEANS</option>
		  <option value="SWEATPANTS">SWEATPANTS</option>
		  <option value="LEGGINGS">LEGGINGS</option>
		  <option value="PANTS">PANTS</option>		  
		  <option value="SKIRTS">SKIRTS</option>
		  <option value="DRESSES & ROMPERS">DRESSES & ROMPERS</option>
		  <option value="SWIM">SWIM</option>
 		  <option value="FLIP FLOPS">FLIP FLOPS</option>
		  <option value="ACCESSORIES">ACCESSORIES</option>
		  <option value="BRAS & UNDIES">BRAS & UNDIES</option>
		</select>
    </div>
  </div>
  
    <div class="control-group">
    <label class="control-label" for="womencheckingSize">选择尺码</label>
    <div class="controls">
     <select id="womencheckingSize" name="womencheckingSize"  multiple="multiple" size=16>
		  <option value="XS">XS</option>
		  <option value="S">S</option>
		  <option value="M">M</option>
		  <option value="L">L</option>
		  <option value="000">000</option>
		  <option value="00">00</option>
		  <option value="0">0</option>
		  <option value="2">2</option>
		  <option value="4">4</option>
		  <option value="6">6</option>
		  <option value="8">8</option>
		  <option value="10">10</option>
		  <option value="12">12</option>
		  <option value="1 SIZE">1 SIZE</option>
		  <option value="XS/S">XS/S</option>
		  <option value="M/L">M/L</option>

		</select>
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
	 $("#warningMen").attr("checked", <%=hollistercoMailList.warningMen%>);
	 $('#warningMen').parent().bootstrapSwitch('setState', <%=hollistercoMailList.warningMen%>);

	 $("#warningWomen").attr("checked", <%=hollistercoMailList.warningWomen%>);
	 $('#warningWomen').parent().bootstrapSwitch('setState', <%=hollistercoMailList.warningWomen%>);

	 $("#warningCode").attr("checked", <%=hollistercoMailList.warningCode%>);
	 $('#warningCode').parent().bootstrapSwitch('setState', <%=hollistercoMailList.warningCode%>);
	 
	 $("#mencheckingRegular").attr("checked", <%=hollistercoMailList.mencheckingRegular%>);
	 $('#mencheckingRegular').parent().bootstrapSwitch('setState', <%=hollistercoMailList.mencheckingRegular%>);

	 $("#mencheckingSecretSale").attr("checked", <%=hollistercoMailList.mencheckingSecretSale%>);
	 $('#mencheckingSecretSale').parent().bootstrapSwitch('setState', <%=hollistercoMailList.mencheckingSecretSale%>);

	 $("#mencheckingClearance").attr("checked", <%=hollistercoMailList.mencheckingClearance%>);
	 $('#mencheckingClearance').parent().bootstrapSwitch('setState', <%=hollistercoMailList.mencheckingClearance%>);

	 $("#mencheckingSale").attr("checked", <%=hollistercoMailList.mencheckingSale%>);
	 $('#mencheckingSale').parent().bootstrapSwitch('setState', <%=hollistercoMailList.mencheckingSale%>);

	 $("#mencheckingClearanceDiscount").val("<%=hollistercoMailList.mencheckingClearanceDiscount%>");
	 $("#mencheckingSaleDiscount").val("<%=hollistercoMailList.mencheckingSaleDiscount%>");
	 <%
	 	if(hollistercoMailList.mencheckingCategory!=null)
	 	for (int i = 0; i < hollistercoMailList.mencheckingCategory.size(); i++) {
	  %>
	      $("#mencheckingCategory").find("option[value='<%=hollistercoMailList.mencheckingCategory.get(i)%>']").attr("selected",true);
	  <%
		}
	 %>
	 	 <%
	 	if(hollistercoMailList.mencheckingSize!=null)
	 	for (int i = 0; i < hollistercoMailList.mencheckingSize.size(); i++) {
	  %>
	      $("#mencheckingSize").find("option[value='<%=hollistercoMailList.mencheckingSize.get(i)%>']").attr("selected",true);
	  <%
		}
	 %>
	
	
	 $("#womencheckingRegular").attr("checked", <%=hollistercoMailList.womencheckingRegular%>);
	 $('#womencheckingRegular').parent().bootstrapSwitch('setState', <%=hollistercoMailList.womencheckingRegular%>);

	 $("#womencheckingSecretSale").attr("checked", <%=hollistercoMailList.womencheckingSecretSale%>);
	 $('#womencheckingSecretSale').parent().bootstrapSwitch('setState', <%=hollistercoMailList.womencheckingSecretSale%>);

	 $("#womencheckingClearance").attr("checked", <%=hollistercoMailList.womencheckingClearance%>);
	 $('#womencheckingClearance').parent().bootstrapSwitch('setState', <%=hollistercoMailList.womencheckingClearance%>);

	 $("#womencheckingSale").attr("checked", <%=hollistercoMailList.womencheckingSale%>);
	 $('#womencheckingSale').parent().bootstrapSwitch('setState', <%=hollistercoMailList.womencheckingSale%>);

	 $("#womencheckingClearanceDiscount").val("<%=hollistercoMailList.womencheckingClearanceDiscount%>");

	 $("#womencheckingSaleDiscount").val("<%=hollistercoMailList.womencheckingSaleDiscount%>");
	 <%
	 	if(hollistercoMailList.womencheckingCategory!=null)
	 	for (int i = 0; i < hollistercoMailList.womencheckingCategory.size(); i++) {
	  %>
	      $("#womencheckingCategory").find("option[value='<%=hollistercoMailList.womencheckingCategory.get(i)%>']").attr("selected",true);
	  <%
		}
	 %>
	 	 <%
	 	if(hollistercoMailList.womencheckingSize!=null)
	 	for (int i = 0; i < hollistercoMailList.womencheckingSize.size(); i++) {
	  %>
	      $("#womencheckingSize").find("option[value='<%=hollistercoMailList.womencheckingSize.get(i)%>']").attr("selected",true);
	  <%
		}
	 %>
	  
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
  	  $("#form1").attr("action", "<%=request.getContextPath()%>/hco/updatemaillist?action=load");
   });
  
   $("#updatebutton").click(function() {
  		var tem1=$("#mencheckingClearanceDiscount").val();
  	if(!isNumber(tem1)){
		$.globalMessenger().post("C区折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem1)<0.01 || Number(tem1)>1){
		$.globalMessenger().post("C区折扣，请输入0至1之间的一个数值");
		return false;
	}
		var tem2=$("#mencheckingSaleDiscount").val();
  	if(!isNumber(tem2)){
		$.globalMessenger().post("S区折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem2)<0.01 || Number(tem2)>1){
		$.globalMessenger().post("S区折扣，请输入0至1之间的一个数值");
		return false;
	}
	
	var tem1=$("#womencheckingClearanceDiscount").val();
  	if(!isNumber(tem1)){
		$.globalMessenger().post("C区折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem1)<0.01 || Number(tem1)>1){
		$.globalMessenger().post("C区折扣，请输入0至1之间的一个数值");
		return false;
	}
		var tem2=$("#womencheckingSaleDiscount").val();
  	if(!isNumber(tem2)){
		$.globalMessenger().post("S区折扣，请输入0至1之间的一个数值");
		return false;
	}
	if(Number(tem2)<0.01 || Number(tem2)>1){
		$.globalMessenger().post("S区折扣，请输入0至1之间的一个数值");
		return false;
	}
	
	
   		$("#form1").attr("action", "<%=request.getContextPath()%>/hco/updatemaillist?action=update");
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
