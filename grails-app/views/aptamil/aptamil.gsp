<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.aptamil.AptamilProduct" %>
<%@ page import="kxg.searchaf.url.aptamil.SearchAptamilJSP" %>


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


  <body onload="init()">

<%
   SearchAptamilJSP searchAptamilJSP=new SearchAptamilJSP();
   searchAptamilJSP.chechInventory();
   ArrayList<AptamilProduct> matchprolist=searchAptamilJSP.matchprolist;
%>

    <form name="frm" action="<%=request.getContextPath()%>/amazon/aptamil" method="post" ">
       
	 <table>
	 <tr>
      		<td><input type="submit"  value="查询" style="width:100px;"/></td>
      		<td><input type="submit"  value="返回"  onclick="back();" style="width:100px;"/></td>
       </tr>
      </table>
     </form>
      <table>  
     <%
     if(matchprolist!=null){
      for (int i = 0; i < matchprolist.size(); i++) {
    	  AptamilProduct product= matchprolist.get(i);
    	%>  
       	 <tr>  
         <td><%=product.toString()%><br/></td>
          </tr>
         <%
        }
      }
      %>
        
       </table>
       
           <p><a href="/searchaf/aptamil/aptamilReadme.html">使用说明</a></p>
 
     
       
      
  </body>
</html>

<script language="javascript">
function back(){
	document.frm.action="/index.html";
	document.frm.submit();
}
  function openUrl(url){
	   //$.globalMessenger().post(url);
    	window.open(url);
     }
</script>