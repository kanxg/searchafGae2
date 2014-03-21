package kxg.searchaf
import kxg.searchaf.url.aptamil.SearchAptamil

class WeixinService {
	

	def static searchAptamil=new SearchAptamil()

    def serviceMethod() {

    }

    def getCommandRespond(def MsgType,def Content){
    	if(Content=="wa"){
    		//get  w 家爱他美
    	}else if (Content=="wa"){

    	}
    }

    def getInventory(){
    	 
    }

    def updateInventory(){
		searchAptamil.getInventory4Weixin()
    }
}
