 <%@ page contentType="text/html;charset=utf-8" language="java" %>
 
 
 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
    	  <a class="brand" href="<%=request.getContextPath()%>">首页</a>
   			 <a class="brand">saksfifthavenue.com</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
            <li><a href="<%=request.getContextPath()%>/saks/searchsaksmakeup">化妆品在线查询</a></li>
            <li><a href="<%=request.getContextPath()%>/saks/searchsakswomen">女装在线查询</a></li>
            <li><a href="<%=request.getContextPath()%>/saks/customersearch">自定义查询</a></li>
             <li><a href="<%=request.getContextPath()%>/saks/testurl">在线查询</a></li>
      	<!--		 <li><a href="<%=request.getContextPath()%>/saks/updatemaillist">注册用户配置</a></li>  -->
        		 <li><a href="<%=request.getContextPath()%>/saks/addmaillist">设置邮件提醒</a></li>
        		 <li><a href="<%=request.getContextPath()%>/saks/howto">设置说明</a></li>
            </ul>
            <!--
            <form class="navbar-form pull-right">
              <input class="span2" type="text" placeholder="Email">
              <input class="span2" type="password" placeholder="Password">
              <button type="submit" class="btn">Sign in</button>
            </form>
            -->
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>