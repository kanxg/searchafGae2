<%@ page contentType="text/html;charset=utf-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
    <g:render template="head" /> 

  <body>

   <g:render template="top" /> 
   
   
  	  <div class="container">
 
 <form id="form1" class="form-horizontal">
  <div class="control-group">
    <label class="control-label" for="mailaddress">Email</label>
    <div class="controls">
      <input type="text" name="mailaddress" id="mailaddress" value="123456@qq.com">
         <p class="text-info">* 你需要提醒的邮件地址</p>
    </div>
  </div>
  
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
      <input type="text" name="url" id="url" style="width: 100%" value="http://www.vitacost.com/Panasonic-ES-LV61-A-Electric-Multiflex-Pivoting/dp/B005GNL5G4/ref=lh_ni_t?ie=UTF8&psc=1&smid=ATVPDKIKX0DER">
      	<p class="text-info">*  必须是Vitacost网站的产品，打开你的产品页面，拷贝URL到这里 </p>
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
   
 
  
 </form>
 	  </div>
  

  
   </body>
</html>

 
