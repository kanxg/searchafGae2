   <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">EBuyBot</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="<%=request.getContextPath()%>">首页</a></li>
            <li><a href="<%=request.getContextPath()%>/amazon/topseller">Top Seller查询</a></li>
             <li><a href="<%=request.getContextPath()%>/amazon/testurl">产品价格历史</a></li>
        <!--     <li><a href="<%=request.getContextPath()%>/amazon/updatemaillist">注册用户配置</a></li>  -->
             <li><a href="<%=request.getContextPath()%>/amazon/addmaillist">设置邮件提醒</a></li>
             <li><a href="<%=request.getContextPath()%>/amazon/howto">设置说明</a></li>
          </ul>
           <ul class="nav navbar-nav navbar-right">
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>

         
        </div><!--/.navbar-collapse -->
      </div>
    </div>