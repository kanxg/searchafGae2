package searchaf
import kxg.searchaf.url.amazon.*;
import kxg.searchaf.url.keepa.*
//import grails.plugin.springsecurity.annotation.Secured
//@Secured(['ROLE_USER'])
class AmazonController {
	//def springSecurityService

	def index() {
	}

	def addmaillist (){

		//def user= springSecurityService.getCurrentUser()

		String ErrMsg="";
		List<AmazonPage> pagelist=null;

		String url = params.url
		String prodname = params.prodname
		String mailaddress =params.mailaddress  

		String type =params.type
		AmazonPageJSP jsp=new AmazonPageJSP();
//		println params
 		if(mailaddress!=null){
			if("add".equals(type)){
				//System.out.println("prodname:"+prodname);
				boolean checksalerisAmazon=false;
				if(params.checksalerisAmazon){
					checksalerisAmazon=true;
				}
				String expectPirce = params.expectPirce
				ErrMsg=jsp.addNewMail(mailaddress,prodname,url,expectPirce,checksalerisAmazon);
				if("exist".equals(ErrMsg)){
					ErrMsg="当前产品已经在您的监视列表中.";
				}else if("notamazon".equals(ErrMsg)){
					ErrMsg="当前只支持amazon美国,中国,德国,日本,英国站,请输入正确的链接!";
				}else if("success".equals(ErrMsg)){
					ErrMsg="添加成功!";
				}
			}

			if("delete".equals(type)){	
				println url
				ErrMsg=jsp.deleteMail(mailaddress,url);
				if("notexist".equals(ErrMsg)){
					ErrMsg="你没有监视这个URL.";
				}else if("success".equals(ErrMsg)){
					ErrMsg="删除成功!";
				}
			}
			pagelist=jsp.loadMail( mailaddress);

		}
 
		[ErrMsg:ErrMsg,url:url,mailaddress:mailaddress,pagelist:pagelist]
	}
	def updatemaillist (){
	}
	def topseller (){
			String ErrMsg="";
	 		List<AmazonProduct> productlist=null;
 
    		String Category = params.Category
    		
		 	if(Category!=null){
		 	     SearchKeepaJSP jsp=new SearchKeepaJSP();
		 
		 		 try {
		 		 	//productlist=jsp.getUsAllBestSellersProduct(url);
			 	} catch (Exception e) {
			 		ErrMsg=e.getMessage();
			 	}
		 	 	
			 }
	 
	 	[productlist:productlist,Category:Category,ErrMsg:ErrMsg]
 

	}
	def testurl() {
		 String ErrMsg="";
		 AmazonPage apage=null;
		 AmazonProduct product=null;
		 
		 boolean checksalerisAmazon=false;
		 
	     String url = request.getParameter("url");
		 if(url!=null){
	 	      AmazonPageJSP jsp=new AmazonPageJSP();
	 		 if(request.getParameterValues("checksalerisAmazon")!=null){
	 		 	 checksalerisAmazon=true; 		 
	 		 }
	 		 SearchKeepaJSP searchKeepaJSP=new SearchKeepaJSP();
	 		 try {
	 		 	apage=jsp.testUrl(url,checksalerisAmazon);
	 		 	product=searchKeepaJSP.getProductPriceList(apage);
		 	} catch (Exception e) {
		 		ErrMsg=e.getMessage();
		 	}
	 	 	
		 }
		 [ErrMsg:ErrMsg,url:url,product:product,apage:apage]
	}
	def howto(){
	}

	def testUI(){
	}
}
