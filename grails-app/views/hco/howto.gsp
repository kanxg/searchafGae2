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
       <p class="text-info">试用用户部分产品无法监视，如无法监视Secret Sale区</p>
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
    <label class="control-label" for="warningCode">新折扣码提醒</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningCode" name="warningCode" value="warningCode">
	     </div> <p class="text-info">* 勾选，AF网站的促销代码更新提醒</p>
    </div>
  </div> 
  
   <hr>
   
   <div class="control-group">
    <label class="control-label" for="warningMen">男士区</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="warningMen" name="warningMen" value="warningMen">
	     </div> <p class="text-info">* 不勾选，所有男装都将不监视</p>
    </div>
  </div> 
  
  <div class="control-group">
    <label class="control-label" for="mencheckingRegular">正价区</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	<input type="checkbox" id="mencheckingRegular" name="mencheckingRegular" value="mencheckingRegular">
        </div><p class="text-info">* 勾选，监视正价区商品</p>
    </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="mencheckingSale">S区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="mencheckingSale" name="mencheckingSale" value="mencheckingSale">
          </div> <p class="text-info">* 勾选，监视Sale区商品</p>
    </div>
    
  </div>   
  <div class="control-group">
    <label class="control-label" for="mencheckingSaleDiscount">S区告警折扣</label>
    <div class="controls">
      <input type="text" id="mencheckingSaleDiscount" name="mencheckingSaleDiscount">              
         <p class="text-info">* 低于输入的折扣,才会提醒</p>
      
    </div>
  </div>
  
  
  <div class="control-group">
    <label class="control-label" for="mencheckingClearance">C区</label>
    <div class="controls">
	          <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="mencheckingClearance" name="mencheckingClearance" value="mencheckingClearance">
             </div> <p class="text-info">* 勾选，监视清仓区商品</p>
    </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="mencheckingClearanceDiscount">C区告警折扣</label>
    <div class="controls">
      <input type="text" id="mencheckingClearanceDiscount" name="mencheckingClearanceDiscount" value="0.4">
                 <p class="text-info">* 低于输入的折扣,才会提醒</p>
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="mencheckingSecretSale">Secret Sale区</label>
    <div class="controls">
	           <div class="make-switch" data-on-label="是" data-off-label="否" tabindex="0">
	  <input type="checkbox" id="mencheckingSecretSale" name="mencheckingSecretSale" value="mencheckingSecretSale" >
	            </div>   <p class="text-info">* 勾选，当有Secret Sale产品，提醒 </p>
    </div>
  </div>   
  
    <div class="control-group">
    <label class="control-label" for="mencheckingCategory">选择分类</label>
    <div class="controls">
     <select id="mencheckingCategory" name="mencheckingCategory" multiple="multiple" size=15>
		  <option value="TEES" selected>TEES</option>
		  <option value="POLOS" selected>POLOS</option>
		  <option value="SHIRTS">SHIRTS</option>
		  <option value="HOODIES & SWEATSHIRTS">HOODIES & SWEATSHIRTS</option>
		  <option value="SWEATERS">SWEATERS</option>
		  <option value="OUTERWEAR" selected>OUTERWEAR</option>
		  <option value="SHORTS">SHORTS</option>
		  <option value="JEANS">JEANS</option>
		  <option value="SWEATPANTS">SWEATPANTS</option>
		  <option value="PANTS">PANTS</option>
		  <option value="SWIM">SWIM</option>
		  <option value="SLEEP">SLEEP</option>
		  <option value="FLIP FLOPS">FLIP FLOPS</option>
		  <option value="ACCESSORIES">ACCESSORIES</option>
		  <option value="UNDERWEAR">UNDERWEAR</option>
		</select>
	  <p class="text-info">* 多选，只有选中的分类才提醒 </p>
    </div>
  </div>
  
    <div class="control-group">
    <label class="control-label" for="mencheckingSize">选择尺码</label>
    <div class="controls">
     <select id="mencheckingSize" name="mencheckingSize"  multiple="multiple" size=34>
		  <option value="XS" selected>XS</option>
		  <option value="S" selected>S</option>
		  <option value="M">M</option>
		  <option value="L">L</option>
		  <option value="XL">XL</option>
		  <option value="XXL">XXL</option>
		  <option value="28 X 30">28 X 30</option>
		  <option value="28 X 32">28 X 32</option>
		  <option value="30 X 30" selected>30 X 30</option>
		  <option value="30 X 32" selected>30 X 32</option>
		  <option value="30 X 34">30 X 34</option>
		  <option value="31 X 30">31 X 30</option>
		  <option value="31 X 32">31 X 32</option>
		  <option value="31 X 34">31 X 34</option>
		  <option value="32 X 30">32 X 30</option>
		  <option value="32 X 32">32 X 32</option>
		  <option value="32 X 34">32 X 34</option>
		  <option value="33 X 32">33 X 32</option>
		  <option value="33 X 34">33 X 34</option>
		  <option value="34 X 32">34 X 32</option>
		  <option value="34 X 34">34 X 34</option>
		  <option value="36 X 32">36 X 32</option>
		  <option value="36 X 34">36 X 34</option>
		  <option value="28">28</option>
		  <option value="30">30</option>
		  <option value="31">31</option>
		  <option value="32">32</option>
		  <option value="33">33</option>
		  <option value="34">34</option>
		  <option value="36">36</option>
		  <option value="38">38</option>
		  <option value="1 SIZE">1 SIZE</option>
		  <option value="S/M">S/M</option>
		  <option value="L/XL">L/XL</option>
		</select>
   	  <p class="text-info">* 多选，只有选中的尺码才提醒 </p>
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