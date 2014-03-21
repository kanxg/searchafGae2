<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="kxg.searchaf.url.af.AfMailList" %>
<%@ page import="kxg.searchaf.url.af.AfProduct" %>
<%@ page import="kxg.searchaf.url.af.AfConstant" %>

 <table width='100%'>  
 
     <%
     	AfMailList.sync();
		List<AfMailList> afmailist = AfMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			AfMailList amail = afmailist.get(i);
			%>
			<tr width='100%'>  
			<td width='100%'><%=amail.toString()%></td>
			</tr>
			<%
			//System.out.println(amail);
		}
		
		%>
 </table>