<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.victoriassecret.SearchVictoriassecretJSP" %>
<%@ page import="kxg.searchaf.url.victoriassecret.VictoriassecretProduct" %>
<%@ page import="kxg.searchaf.url.victoriassecret.VictoriassecretConstant" %>



<html>


   <g:render template="head" /> 

   <body onload="init()">

   <g:render template="top" /> 

<%
    boolean firstload=false;
	
    String showdiscount = request.getParameter("showdiscount");
    if(showdiscount==null){
		showdiscount=String.valueOf(VictoriassecretConstant.warningdiscount);
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
	
	SearchVictoriassecretJSP af=new SearchVictoriassecretJSP();
	List<VictoriassecretProduct> matchprolist = null;
	if(!firstload ){
		matchprolist=af.getwomendiscountproductforJsp(showdiscount,email,searchtext);
	};
 
%>

    <form name="frm" action="<%=request.getContextPath()%>/victoriassecret/searchwomen" method="post" onsubmit="return checkinput()">
      <table>
      <tr>
      		<td>显示折扣:<input type="text" name="showdiscount" value="<%=showdiscount%>"/></td>
      		<td> </td>
      </tr>
  		<tr>
      		<td>搜索名称:<input type="text" name="searchtext" value="<%=searchtext%>"/></td>
      		<td> </td>
      </tr>
     
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
    	  VictoriassecretProduct product= matchprolist.get(i);
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
	document.frm.action="victoriassecret.html";
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