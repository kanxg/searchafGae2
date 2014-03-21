<%@ page contentType="text/html;charset=utf-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
    <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
   
  	  <div class="container">
 
 <form id="form1" class="form-horizontal">

  
  <div class="control-group">
    <label class="control-label" for="prodname">产品名称</label>
    <div class="controls">
      <input type="text" name="prodname" id="prodname" value="剃须刀1100">
      	 <p class="text-info">*   方便你辨识添加的记录内容 </p>
    </div>
  </div>
  
      <div class="control-group">
    <label class="control-label" for="url">产品URL</label>
    <div class="controls">
      <input type="text" name="url" id="url" style="width: 100%" value="http://www.amazon.com/Panasonic-ES-LV61-A-Electric-Multiflex-Pivoting/dp/B005GNL5G4/ref=lh_ni_t?ie=UTF8&psc=1&smid=ATVPDKIKX0DER">
      	<p class="text-info">*  必须是Amazon网站的产品，打开你的产品页面，拷贝URL到这里 </p>
    </div>
  </div>
  
        <div class="control-group">
    <label class="control-label" for="checksalerisAmazon">只看Amazon自营产品</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="checksalerisAmazon" name="checksalerisAmazon" value="checksalerisAmazon">
	   </div>
	    <p class="text-info">*  勾选这个，将只监视Amazon自营产品，当自营产品缺货时，不提醒 </p>
    </div>
  </div>
  
        <div class="control-group">
    <label class="control-label" for="expectPirce">期望价格</label>
    <div class="controls">
      <input type="text" id="expectPirce" name="expectPirce" value="280">
      <p class="text-info">*  当产品实际价格低于期望价格时，才触发邮件提醒 </p>
    </div>
  </div>
  
 
  <div>
  <h1 class="text-error">规则说明</h1>
  <p class="text-info">1, 当产品从缺货状态，更新到到货状态，且实际价格低于预期价格，就会发邮件； </p>
  <p class="text-info"> 2, 当产品有货， 价格降低， 且实际价格低于预期价格，就会发邮件； </p>
	<p> </p>
  </div>
   
  <div>
  <h2 class="text-error">需要选择尺码的产品怎么办？</h2>
   <p class="text-info"> 1,选择好你要的尺码，颜色，加入购物车</p>
  <p><img src="<%=request.getContextPath()%>/images/amazon/amazon_chima.jpg"  width="600"   ></p>
     <p class="text-info">2 , 在确认页面，右键产品链接,"复制链接地址",拷贝这个地址到监测页面URL</p>
  <p><img src="<%=request.getContextPath()%>/images/amazon/addcart.jpg"  width="600"   ></p>
   <p class="text-info"> 2, 或者进入购物车,右键产品链接,"复制链接地址",拷贝这个地址到监测页面URL</p>
  <p><img src="<%=request.getContextPath()%>/images/amazon/incart.jpg"  width="600"   ></p>
  <p class="text-info">3,怎么样验证我加入的对不对？</p>
  <p class="text-info">在添加产品操作里，点"产品价格历史"，确认结果和Amazon页面一致</p>
  <p><img src="<%=request.getContextPath()%>/images/amazon/verify.jpg"  width="400"   ><img src="<%=request.getContextPath()%>/images/amazon/verify1.jpg"  width="400"   ></p>
   <p class="text-info">如果结果不一致, 提醒将不准确，请联系客服！</p>
  </div>
  
 </form>
 	  </div>
  

  
   </body>
</html>

 
