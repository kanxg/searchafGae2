<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.hollisterco.HollistercoMailList" %>
<%@ page import="kxg.searchaf.url.hollisterco.HollistercoProduct" %>
<%@ page import="kxg.searchaf.url.hollisterco.HollistercoConstant" %>

 <table width='100%'>  
 
     <%
     	HollistercoMailList.sync();
		List<HollistercoMailList> hollistercomailist = HollistercoMailList.getinstance();
		for (int i = 0; i < hollistercomailist.size(); i++) {
			HollistercoMailList amail = hollistercomailist.get(i);
			%>
			<tr width='100%'>  
			<td width='100%'><%=amail.toString()%></td>
			</tr>
			<%
			//System.out.println(amail);
		}
		
		%>
 </table>