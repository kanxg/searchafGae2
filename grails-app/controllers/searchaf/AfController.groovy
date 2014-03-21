package searchaf
import kxg.searchaf.url.af.*;
//import grails.plugin.springsecurity.annotation.Secured
//@Secured(['ROLE_USER'])
class AfController {

	def index() {
	}

	def af(){
	}
	def afmen(){
	}

	def afwomen(){
	}

	def customersearch(){
	}

	def howto(){
	}

	def searchafmen(){
		boolean firstload=false;

		def Category=params.Category
		def showinventory=params.showinventory
		def fromcache=params.fromcache

		String showdiscount = params.showdiscount
		if(showdiscount==null){
			showdiscount=String.valueOf(AfConstant.warningdiscount);
			firstload=true;
		}

		String searchtext = params.searchtext
		if(searchtext==null){
			searchtext="";
		}

		SearchAfJSP af=new SearchAfJSP();
		List<AfProduct> matchprolist = null;
		if(!firstload ){
			matchprolist=af.getmendiscountproductforJsp(Category,showdiscount,searchtext,showinventory,fromcache);
		};

		[firstload:firstload,showdiscount:showdiscount,searchtext:searchtext,showinventory:showinventory,fromcache:fromcache,matchprolist:matchprolist]
	}
	def searchafwomen(){
		boolean firstload=false;

		def Category=params.Category
		def showinventory=params.showinventory
		def fromcache=params.fromcache

		String showdiscount = params.showdiscount
		if(showdiscount==null){
			showdiscount=String.valueOf(AfConstant.warningdiscount);
			firstload=true;
		}

		String searchtext = params.searchtext
		if(searchtext==null){
			searchtext="";
		}

		SearchAfJSP af=new SearchAfJSP();
		List<AfProduct> matchprolist = null;
		if(!firstload ){
			matchprolist=af.getwomendiscountproductforJsp(Category,showdiscount,searchtext,showinventory,fromcache);
		};

		[firstload:firstload,showdiscount:showdiscount,searchtext:searchtext,showinventory:showinventory,fromcache:fromcache,matchprolist:matchprolist]
	}
	def updatemaillist(){
	}

	def addmaillist(){
		String ErrMsg="";
		AfMailList afMailList=new AfMailList();
		String  valideTime="";
		String userType="";
		String mailaddress = params.mailaddress
		if(mailaddress!=null){
			String action = params.action
			if("add".equals(action)){
				AfMailListJSP jsp=new AfMailListJSP();
				ErrMsg=jsp.addNewMail(mailaddress);
				if("exist".equals(ErrMsg)){
					ErrMsg="当前邮箱已经在服务器列表中.";
				}else if("success".equals(ErrMsg)){
					ErrMsg="添加成功.";
				}
			}
			AfMailListJSP jsp=new AfMailListJSP();
			AfMailList afMailList1=jsp.loadMail(mailaddress);
			if(afMailList1!=null){
				afMailList=afMailList1;
				valideTime=TimeUtli.dateToString(afMailList.valideTime);
				userType=afMailList.userType;
			}

		}
		[ErrMsg:ErrMsg,userType:userType,valideTime:valideTime,mailaddress:mailaddress,afMailList:afMailList]
	}
}
