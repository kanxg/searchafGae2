 <%@ page contentType="text/html;charset=utf-8" language="java" %>
 
 
 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
           <div class="nav-collapse collapse">
            <ul class="nav">
                    		 <li><a href="<%=request.getContextPath()%>">首页</a></li>
                         <li><a href="<%=request.getContextPath()%>/amazon/topseller">Top Seller查询</a></li>
             <li><a href="<%=request.getContextPath()%>/amazon/testurl">产品价格历史</a></li>
      	<!--		 <li><a href="<%=request.getContextPath()%>/amazon/updatemaillist">注册用户配置</a></li>  -->
        		 <li><a href="<%=request.getContextPath()%>/amazon/addmaillist">设置邮件提醒</a></li>
        		 <li><a href="<%=request.getContextPath()%>/amazon/howto">设置说明</a></li>
            </ul>
          </div><!--/.nav-collapse -->
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