<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.hollisterco.SearchHollistercoJSP" %>
<%@ page import="kxg.searchaf.url.hollisterco.HollistercoProduct" %>
<%@ page import="kxg.searchaf.url.hollisterco.HollistercoConstant" %>

<%
      
    String firstload=request.getParameter("firstload");;
	
    String showdiscount = request.getParameter("showdiscount");
    if(showdiscount==null){
		showdiscount=String.valueOf(HollistercoConstant.warningdiscount);
	}
	
	String searchtext = request.getParameter("searchtext");
    if(searchtext==null){
		searchtext="";
	}
	
	String userdiscount = request.getParameter("userdiscount");
	if(userdiscount==null){
		userdiscount="1";
	}
	
	String email = request.getParameter("email");
	String emailinSession = (String)session.getAttribute("email");
	if(email==null){
		if(emailinSession==null){
			email="";
		}else{
			email=emailinSession;
		}
	}else{
		if(emailinSession==null||!emailinSession.equals(email)){
			session.setAttribute("email",email);
		}
	}
	
	
	String searchurl = request.getParameter("searchurl");
	if(searchurl==null){
		searchurl="";
	}
	
	SearchHollistercoJSP hollisterco=new SearchHollistercoJSP();
	List<HollistercoProduct> matchprolist = null;
	if("0".equals(firstload) ){
		matchprolist=hollisterco.getcustomerdiscountproductforJsp(searchurl,showdiscount,email,searchtext);
	}; 
%>

<!DOCTYPE html>
<html lang="en">
  <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
    <form name="frm" action="<%=request.getContextPath()%>/hco/customersearch" method="post" onsubmit="return checkinput()">
      <table>
      <tr>
      		<td>搜索URL:(输入HCO网站任意链接)</td>
      </tr>
        <tr>
      		<td>搜索URL:<input type="text" name="searchurl" value="<%=searchurl%>" size="220"/></td>
      </tr>
      <tr>
      		<td>显示折扣:<input type="text" name="showdiscount" value="<%=showdiscount%>"/>只显示在当前折扣以下的产品</td>
      		<td><input type="hidden" name="firstload" value="0"/> </td>
      </tr>
 		<tr>
      		<td>搜索名称:<input type="text" name="searchtext" value="<%=searchtext%>"/>根据产品名称搜索，比如：只显示衬衫， 输入 "SHIRTS" </td>
      </tr>
      <tr>
      		<td>购买邮箱:<input type="text" name="email" size=30 value="<%=email%>"/>购买了监视系统软件的,请输入你的邮箱,否则无法显示库存, 点击库存即可加入购物车 </td>
       </tr>  
      <tr>
		<td> 产品10元/每月, <a href="http://item.taobao.com/item.htm?id=25941644664" target="_blank"> 购买请点击 </a></td>
	 </tr>
	 </table>
	 <table>
      <tr>
      		<td><input type="submit"  value="查询" style="width:100px;"/></td>
      		<td align="right"><input type="button"  value="查看购物车" style="width:100px;" onclick="addToCart('http://www.hollisterco.com/webapp/wcs/stores/servlet/OrderItemDisplayView?orderStatus=P&catalogId=10201&langId=-1&storeId=10251');" /></td>
            <td align="right"><input type="button"  value="结帐" style="width:100px;" onclick="addToCart('http://www.hollisterco.com/webapp/wcs/stores/servlet/ANFCheckOrderItems?doInventory=N&errpage=OrderItemDisplayView&URL=OrderShippingSectionDisplayView&storeId=10251&catalogId=10201&langId=-1');" /></td>
        </tr>
      </table>
     </form>
     
     <table width='100%'>  
     <%
     if(matchprolist!=null){
      for (int i = 0; i < matchprolist.size(); i++) {
    	  HollistercoProduct product= matchprolist.get(i);
    	  int mod=i % 4 ;
     	  if(mod==0){
       %>
       	  <tr width='100%'>  
       <% }%>
       	
        <td width='25%'><%=product.toJspString()%><%=product.tagcontext%><br/></td>
       <%
         if(mod==3){
         %>
          </tr>
         <%
         }
        }
     }
      %>
       </table>


	  
      
      
      
  </body>
</html>

<script language="javascript">
function back(){
	document.frm.action="hollisterco.html";
	document.frm.submit();
}
function checkinput(){
	showdiscount= document.frm.showdiscount.value;
	if(!isNumber(showdiscount)){
		$.globalMessenger().post("请输入0至1之间的一个数值");
		return false;
	}
	if(Number(showdiscount)<0.01 || Number(showdiscount)>1){
		$.globalMessenger().post("请输入0至1之间的一个数值");
		return false;
	}
}
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
	
	   function addToCart(url){
	   //$.globalMessenger().post(url);
    	window.open(url);
     }
</script>



