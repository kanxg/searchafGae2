<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.af.SearchAfJSP" %>
<%@ page import="kxg.searchaf.url.af.AfProduct" %>
<%@ page import="kxg.searchaf.url.af.AfConstant" %>
   

<!DOCTYPE html>
<html lang="en">
      <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
       <form name="frm" class="form-horizontal" action="<%=request.getContextPath()%>/af/searchafwomen" method="post" onsubmit="return checkinput()">
      <table>
     <tr>
      		<td>分类：</td>
      		<td>
      		<select class="select" id="Category" name="Category" >
      		 <option value="saleclearance">Sale区+清仓区</option>
			 <option value="saleclearancesecret">Sale区+清仓区+Secret区</option>
			 <option value="sale">Sale区</option>
	         <option value="clearance">清仓区</option>
	         <option value="secret">Secret区</option>
	         <option value="all">全部(包括正价产品)</option>
	       </select>
	       </td>
      		<td>只显示在当前分类以下的产品</td>
      </tr>
      <tr>
      		<td>显示折扣:</td>
      		<td><input type="text" name="showdiscount" value="<%=showdiscount%>"/></td>
      		<td>只显示在当前折扣以下的产品</td>
      </tr>
 	 <tr>
      		<td>搜索名称:</td>
      		<td><input type="text" name="searchtext" value="<%=searchtext%>"/></td>
      		<td>根据产品名称搜索，比如：只显示衬衫， 输入 "SHIRTS"</td>
      </tr>
      <tr>
      		<td>显示库存   </td>
      		<td><input type="checkbox" id="showinventory" name="showinventory" > </td>
			<td> </td>
       <tr>
      		<td>缓存数据</td> 
      		<td> <input type="checkbox" id="fromcache" name="fromcache"> </td>
			 <td>从缓存数据查询，速度非常快，但有5-10分钟延迟 </td>
 	 </tr>
	 </table>
	 <table>
	 <tr>
      		<td><input type="submit"   value="查询" style="width:100px;"/></td>
      		<td align="right"><input type="button"  value="查看购物车" style="width:100px;" onclick="addToCart('https://www.abercrombie.com/webapp/wcs/stores/servlet/OrderItemDisplayView?orderStatus=P&catalogId=10901&langId=-1&storeId=10051');" /></td>
            <td align="right"><input type="button"  value="结帐" style="width:100px;" onclick="addToCart('https://www.abercrombie.com/webapp/wcs/stores/servlet/ANFCheckOrderItems?doInventory=N&errpage=OrderItemDisplayView&URL=OrderShippingSectionDisplayView&storeId=10051&catalogId=10901&langId=-1');" /></td>
        
 		</tr> 
 
      </table>
     </form>
     
      <table width='100%'>  
     <%
     if(matchprolist!=null){
      for (int i = 0; i < matchprolist.size(); i++) {
    	  AfProduct product= matchprolist.get(i);
    	  int mod=i % 4 ;
     	  if(mod==0){
       %>
       	  <tr width='100%'>   
       <% }%>
       	
        <td width='25%'><div><%=product.toJspString()%><%=product.tagcontext%><br/></div></td>
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