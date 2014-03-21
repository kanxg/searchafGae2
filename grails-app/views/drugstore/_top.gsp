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
   			 <a class="brand">Drugstore.com</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
             <li><a href="<%=request.getContextPath()%>/drugstore/testurl">在线查询</a></li>
      	<!--		 <li><a href="<%=request.getContextPath()%>/drugstore/updatemaillist">注册用户配置</a></li>  -->
        		 <li><a href="<%=request.getContextPath()%>/drugstore/addmaillist">设置邮件提醒</a></li>
        		 <li><a href="<%=request.getContextPath()%>/drugstore/howto">设置说明</a></li>
            </ul>
            <!--
                     <ul class="nav pull-right">
                       <li><a href="logout">退出<sec:username/></a></li>
                       <li><a href="userDetails">配置用户信息<sec:username/></a></li>
                    </ul>
           -->

          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>