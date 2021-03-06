<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.freepeople.SearchFreepeopleJSP" %>
<%@ page import="kxg.searchaf.url.freepeople.FreepeopleProduct" %>
<%@ page import="kxg.searchaf.url.freepeople.FreepeopleConstant" %>



<html>
<head>
	
<script type="text/javascript">
  var vglnk = { api_url: '//api.viglink.com/api',
                key: '1fd42f1d7997ed7abc43c8b7486548c4' };

  (function(d, t) {
    var s = d.createElement(t); s.type = 'text/javascript'; s.async = true;
    s.src = ('https:' == document.location.protocol ? vglnk.api_url :
             '//cdn.viglink.com/api') + '/vglnk.js';
    var r = d.getElementsByTagName(t)[0]; r.parentNode.insertBefore(s, r);
  }(document, 'script'));
</script>

</head>

   <g:render template="head" /> 

   <body onload="init()">

   <g:render template="top" /> 

<%
	boolean firstload=false;
	
    String showdiscount = request.getParameter("showdiscount");
    if(showdiscount==null){
		showdiscount=String.valueOf(FreepeopleConstant.warningdiscount);
		firstload=true;
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
	if(email==null){
		email="";
	}
	
	String searchurl = request.getParameter("searchurl");
	if(searchurl==null){
		searchurl="";
	}
	
	SearchFreepeopleJSP af=new SearchFreepeopleJSP();
	List<FreepeopleProduct> matchprolist = null;
	if(!firstload ){
		matchprolist=af.getcustomerdiscountproductforJsp(searchurl,showdiscount,email,searchtext);
	};
 
%>

    <form name="frm" action="<%=request.getContextPath()%>/freepeople/customersearch" method="post" onsubmit="return checkinput()">
      <table>
       <tr>
      		<td>搜索URL:<input type="text" name="searchurl" value="<%=searchurl%>" size="160"/></td>
      		<td>输入Freepeople 网站任意链接</td>
      </tr>
      <tr>
      		<td>显示折扣:<input type="text" name="showdiscount" value="<%=showdiscount%>"/></td>
      		<td> </td>
      </tr>
  		<tr>
      		<td>搜索名称:<input type="text" name="searchtext" value="<%=searchtext%>"/></td>
      		<td> </td>
      </tr>
      <!--
      <tr>
      		<td>购买邮箱:<input type="text" name="email" size=30 value="<%=email%>"/> </td>
      		<td></td>
       </tr>  
      <tr>
         <td> 购买提醒系统的，可以在Freepeople更新产品时，收到邮件提醒 </td>
		<td> 产品10元/每月, <a href="http://item.taobao.com/item.htm?id=17308753023" target="_blank"> 购买请点击 </a></td>
	 </tr>  
	 -->
	 </table>
	 <table>
	 <tr>
      		<td><input type="submit" value="查询"/></td>
       </tr>
      </table>
     </form>
     
   <table width='100%'>  
     <%
     if(matchprolist!=null){
      for (int i = 0; i < matchprolist.size(); i++) {
    	  FreepeopleProduct product= matchprolist.get(i);
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
function back(){
	document.frm.action="freepeople.html";
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
</script>