 <div class="container">
 
 <form id="form1" class="form-horizontal" action="<%=request.getContextPath()%>/amazon/addmaillist" method="post">
     <div class="control-group">
    <label class="control-label" for="mailaddress">Email</label>
    <div class="controls">
      <input type="text" name="mailaddress" id="mailaddress" value="">
            <button type="submit" id="loadbutton" class="btn btn-info"><i class="fa fa-refresh"></i>查询我已经添加的</button>
    </div>
  </div>

  <div class="control-group">    
    <label class="control-label" for="prodname">产品名称</label>
    <div class="controls">
      <input type="text" name="prodname" id="prodname"> 
    </div>
  </div>
  
      <div class="control-group">
    <label class="control-label" for="url">产品URL</label>
    <div class="controls">
      <input class="input-xxlarge" type="text" name="url" id="url" >
    </div>
  </div>
  
        <div class="control-group">
    <label class="control-label" for="checksalerisAmazon">只看Amazon自营产品</label>
    <div class="controls">
         <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
          <input type="checkbox" id="checksalerisAmazon" name="checksalerisAmazon" value="checksalerisAmazon">
         </div>
    </div>
  </div>
  
        <div class="control-group">
    <label class="control-label" for="expectPirce">期望价格</label>
    <div class="controls">
      <input class="input-mini"  type="text" id="expectPirce" name="expectPirce">
    </div>
  </div>
  

    <div class="control-group">
    <div class="controls">
       <button type="submit" id="addbutton" class="btn btn-success"><i class="fa fa-cog"></i>新增</button>
    </div> 
  </div>
       
 </form>
  </div>
  
     <div class="container">
      <table id="table1" class="table table-bordered tablesorter">
             <thead>
                  <tr>
                    <th ><i class=" icon-arrow-down"></i> 产品名称  </th>
                    <th  ><i class=" icon-arrow-down"></i> 只看自营 </th>
                    <th >产品URL</th>
                    <th ><i class=" icon-arrow-down"></i> 期望价格 </th>
                    <th>操作</th>
                  </tr>
                </thead>
                <tbody id="tbody1"></tbody>
      </table>
  </div>
  