<%@ page contentType="text/html;charset=utf-8" language="java" %>
 
<!DOCTYPE html>
<html lang="en">
     <g:render template="head" /> 
  

  <body>
 <g:render template="top" /> 
  
   <div class="container">
      <div class="hero-unit">


 <form id="form1" class="form-horizontal" >
  <div class="control-group">
    <label class="control-label" for="mailaddress">Email</label>
    <div class="controls">
      <input type="text" name="mailaddress" id="mailaddress" value="123456@qq.com">
         <p class="text-info">* 你需要提醒的邮件地址</p>
    </div>
  </div>
  
       <div class="control-group">
    <label class="control-label" for="userType">用户类型</label>
    <div class="controls">
      <input type="text" name="userType" id="userType"  readonly="true">
       <p class="text-info">* 分购买用户,试用用户</p>
       <p class="text-info">试用用户部分产品无法监视</p>
    </div>
  </div>
  
     <div class="control-group">
    <label class="control-label" for="valideTime">到期时间</label>
    <div class="controls">
      <input type="text" name="valideTime" id="valideTime"  readonly="true" value="2013-12-25">
   <p class="text-info">* 用户license到期日</p>
    </div>
  </div>
  
  
   <div class="control-group">
    <label class="control-label" for="warningWomen">是否邮件提醒</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningWomen" name="warningWomen" value="warningWomen" readonly="true">
	    </div> <p class="text-info">* 不勾选将不接收提醒邮件</p>
    </div>
  </div> 
  

  <div class="control-group">
    <label class="control-label" for="womencheckingSaleDiscount">告警折扣</label>
    <div class="controls">
      <input type="text" id="womencheckingSaleDiscount" name="womencheckingSaleDiscount"  value="0.4" readonly="true">
      	     <p class="text-info">* 低于当前折扣的产品才会发邮件</p>
    </div>
  </div>  
   
  
     <div>
  <h1 class="text-error">说明</h1>
  <p class="text-info">1， 当产品上架，或者价格降低，就会发邮件； </p>
  <p class="text-info">2， 请将发件人加入邮件列表，以防被当为垃圾邮件！ </p>

  </div>
 
       
 </form>
 </div>
  </div>
       
   </body>
</html>