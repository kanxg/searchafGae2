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
   
<div class="container">

 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/af/searchafmen" method="post">

		  <div class="control-group">
		    <label class="control-label" for="Category">分类</label>
		    <div class="controls">
		      <select class="select" id="Category" name="Category" >
      		 <option value="saleclearance">Sale区+清仓区</option>
			 <option value="saleclearancesecret">Sale区+清仓区+Secret区</option>
			 <option value="sale">Sale区</option>
	         <option value="clearance">清仓区</option>
	         <option value="secret">Secret区</option>
	         <option value="all">全部(包括正价产品)</option>
	       </select>
		    </div>
		    只显示在当前分类以下的产品
		  </div>
		  
		    <div class="control-group">
		    <label class="control-label" for="showdiscount">显示折扣</label>
		    <div class="controls">
      		 <input type="text" name="showdiscount" value="<%=showdiscount%>"/>
		    </div>
		    只显示在当前折扣以下的产品
		  </div>
			  <div class="control-group">
			    <label class="control-label" for="searchtext">搜索名称</label>
			    <div class="controls">
      				<input type="text" name="searchtext" value="<%=searchtext%>"/>
			    </div>
			  </div>
		 	  <div class="control-group">
		    <label class="control-label" for="showinventory">显示库存 </label>
		    <div class="controls">
      		<input type="checkbox" id="showinventory" name="showinventory" > 
		    </div>
		  </div>
   			 <div class="control-group">
		    <label class="control-label" for="fromcache">缓存数据 </label>
		    <div class="controls">
      		<td> <input type="checkbox" id="fromcache" name="fromcache"> </td>
		    </div>
		    从缓存数据查询，速度非常快，但有5-10分钟延迟
		  </div>
    
	 <div class="control-group">
    <div class="controls">
       <button type="submit" id="addbutton" class="btn btn-success"><i class="fa fa-cog"></i>查询</button>
       	<td align="right"><input type="button"  value="查看购物车" style="width:100px;" onclick="addToCart('https://www.abercrombie.com/webapp/wcs/stores/servlet/OrderItemDisplayView?orderStatus=P&catalogId=10901&langId=-1&storeId=10051');" /></td>
            <td align="right"><input type="button"  value="结帐" style="width:100px;" onclick="addToCart('https://www.abercrombie.com/webapp/wcs/stores/servlet/ANFCheckOrderItems?doInventory=N&errpage=OrderItemDisplayView&URL=OrderShippingSectionDisplayView&storeId=10051&catalogId=10901&langId=-1');" /></td>
    </div> 
  </div>
     </form>
         </div>
         
     <div class="container"> 

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
  </div>


  </body>
</html>

<script language="javascript">
( document ).ready(function(){
	 $("#showinventory").val("<%=showinventory%>");
	 $("#fromcache").val("<%=fromcache%>");	 
	 
	 <%
	 	if(!"".equals(ErrMsg)){
	 %>
	 	$.globalMessenger().post("<%=ErrMsg%>");
	 <%}%>
	 
	  $("#form1").submit(function(){
 
         return true;
  });

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