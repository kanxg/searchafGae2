package searchaf

import kxg.searchaf.url.*;
import kxg.searchaf.url.af.*;


class AfJob {

     static triggers = {
      simple  startDelay: 1000*60*5 ,repeatInterval: 1000l*60*10 // start after 5 mins, execute job once in 10 mins
    }

    def execute() {
        // execute job
        //print "Job run!" +  new Date() 
 		SearchTaskFactory.getInstance().af?.getnewproduct(i);
    }
}
