package kxg.searchaf.url;

import java.util.Date;
import java.util.TimerTask;

import kxg.searchaf.url.af.AfConstant;
import kxg.searchaf.url.af.SearchAf;
import kxg.searchaf.url.amazon.AmazonConstant;
import kxg.searchaf.url.amazon.SearchAmazon;
import kxg.searchaf.url.aptamil.AptamilConstant;
import kxg.searchaf.url.aptamil.SearchAptamil;
import kxg.searchaf.url.beauty.BeautyConstant;
import kxg.searchaf.url.beauty.SearchBeauty;
import kxg.searchaf.url.beauty.SearchBeauty;
import kxg.searchaf.url.chunqiu.ChunqiuConstant;
import kxg.searchaf.url.chunqiu.SearchChunqiu;
import kxg.searchaf.url.drugstore.DrugstoreConstant;
import kxg.searchaf.url.drugstore.SearchDrugstore;
import kxg.searchaf.url.freepeople.FreepeopleConstant;
import kxg.searchaf.url.freepeople.SearchFreepeople;
import kxg.searchaf.url.gnc.GncConstant;
import kxg.searchaf.url.gnc.SearchGnc;
import kxg.searchaf.url.gymboree.GymboreeConstant;
import kxg.searchaf.url.gymboree.SearchGymboree;
import kxg.searchaf.url.hollisterco.HollistercoConstant;
import kxg.searchaf.url.hollisterco.SearchHollisterco;
import kxg.searchaf.url.jcrew.JcrewConstant;
import kxg.searchaf.url.jcrew.SearchJcrew;
import kxg.searchaf.url.juicycouture.JuicycoutureConstant;
import kxg.searchaf.url.juicycouture.SearchJuicycouture;
import kxg.searchaf.url.macys.MacysConstant;
import kxg.searchaf.url.macys.SearchMacys;
import kxg.searchaf.url.neiman.NeimanConstant;
import kxg.searchaf.url.neiman.SearchNeiman;
import kxg.searchaf.url.nordstrom.NordstromConstant;
import kxg.searchaf.url.nordstrom.SearchNordstrom;
import kxg.searchaf.url.ralphlauren.RalphlaurenConstant;
import kxg.searchaf.url.ralphlauren.SearchRalphlauren;
import kxg.searchaf.url.saks.SaksConstant;
import kxg.searchaf.url.saks.SearchSaks;
import kxg.searchaf.url.sephora.SearchSephora;
import kxg.searchaf.url.sephora.SephoraConstant;
import kxg.searchaf.url.sierratradingpost.SearchSierratradingpost;
import kxg.searchaf.url.sierratradingpost.SierratradingpostConstant;
import kxg.searchaf.url.sixpm.SearchSixpm;
import kxg.searchaf.url.sixpm.SixpmConstant;
import kxg.searchaf.url.tommy.SearchTommy;
import kxg.searchaf.url.tommy.TommyConstant;
import kxg.searchaf.url.toryburch.SearchToryburch;
import kxg.searchaf.url.toryburch.ToryburchConstant;
import kxg.searchaf.url.victoriassecret.SearchVictoriassecret;
import kxg.searchaf.url.victoriassecret.VictoriassecretConstant;
import kxg.searchaf.url.vitacost.SearchVitacost;
import kxg.searchaf.url.vitacost.VitacostConstant;
import kxg.searchaf.weixin.WeixinHelper;

public class SearchTask {// extends TimerTask {

	private int i = 0;

	public SearchAmazon amazon;
	public SearchAf af;
	public SearchHollisterco hollisterco;
	public SearchDrugstore drugstore;
	public SearchRalphlauren Ralphlauren;
	public SearchTommy Tommy;
	public SearchJcrew Jcrew;
	public SearchGymboree gymboree;
	public SearchNeiman neiman;
	public SearchAptamil aptamil;
	public SearchSephora sephora;
	public SearchChunqiu chunqiu;
	public SearchSaks saks;
	public SearchFreepeople freepeople;
	public SearchJuicycouture juicycouture;
	public SearchToryburch toryburch;
	public SearchVictoriassecret victoriassecret;
	public SearchGnc gnc;
	public SearchVitacost vitacost;
	public SearchSixpm sixpm;
	public SearchBeauty beauty;
	public SearchNordstrom nordstrom;
	public SearchMacys macys;
	public SearchSierratradingpost sierratradingpost;

	public SearchTask() {
		amazon = new SearchAmazon();

		af = new SearchAf();
		hollisterco = new SearchHollisterco();

		// drugstore = new SearchDrugstore();
		// Ralphlauren = new SearchRalphlauren();
		// Tommy = new SearchTommy();
		// gymboree = new SearchGymboree();
		// neiman = new SearchNeiman();
		// Jcrew = new SearchJcrew();
		// sephora = new SearchSephora();
		// freepeople = new SearchFreepeople();
		// juicycouture = new SearchJuicycouture();
		// toryburch = new SearchToryburch();
		// saks = new SearchSaks();
		// gnc = new SearchGnc();
		// vitacost = new SearchVitacost();
		// sixpm = new SearchSixpm();
		// beauty = new SearchBeauty();
		// nordstrom = new SearchNordstrom();
		// macys = new SearchMacys();
		// sierratradingpost = new SearchSierratradingpost();
		//
		// aptamil = new SearchAptamil();
		// chunqiu = new SearchChunqiu();
		// victoriassecret = new SearchVictoriassecret();

	}

	// @Override
	public void run() {
		System.out.println("start:" + new Date());
		//WeixinHelper.connect2Weixin();

		// amazon
		int mo = (int) (AmazonConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && amazon != null)
			amazon.searchsite();

		// af
		mo = (int) (AfConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && af != null)
			af.getnewproduct(i);

		// hco
		mo = (int) (HollistercoConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && hollisterco != null)
			hollisterco.getnewproduct(i);

		// drugstore
		mo = (int) (DrugstoreConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && drugstore != null)
			drugstore.searchsite();

		// Aptamil
		mo = (int) (AptamilConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && aptamil != null)
			aptamil.getnewproduct();

		// RL
		mo = (int) (RalphlaurenConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && Ralphlauren != null)
			Ralphlauren.getnewproduct();

		// Tommy
		mo = (int) (TommyConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && Tommy != null)
			Tommy.getnewproduct();

		// Jcrew
		mo = (int) (JcrewConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && Jcrew != null)
			Jcrew.getnewproduct();

		// GYM
		mo = (int) (GymboreeConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && gymboree != null)
			gymboree.getnewproduct();

		// Sephora
		mo = (int) (SephoraConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && sephora != null)
			sephora.searchsite();

		// Chun qiu
		mo = (int) (ChunqiuConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && chunqiu != null)
			chunqiu.getnewproduct();

		// Neiman
		mo = (int) (NeimanConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && neiman != null)
			neiman.searchsite();

		// Saks
		mo = (int) (SaksConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && saks != null)
			saks.searchsite();

		// free people
		mo = (int) (FreepeopleConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && freepeople != null)
			freepeople.getnewproduct(i);

		// Juicycouture
		mo = (int) (JuicycoutureConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && juicycouture != null)
			juicycouture.getnewproduct(i);

		// Toryburch
		mo = (int) (ToryburchConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && toryburch != null)
			toryburch.getnewproduct(i);

		// Victoriassecret
		mo = (int) (VictoriassecretConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && victoriassecret != null)
			victoriassecret.getnewproduct(i);

		// GNc
		mo = (int) (GncConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && gnc != null)
			gnc.searchsite();

		// vitacost
		mo = (int) (VitacostConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && vitacost != null)
			vitacost.searchsite();

		// sixpm
		mo = (int) (SixpmConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && sixpm != null)
			sixpm.searchsite();

		// beauty
		mo = (int) (BeautyConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && beauty != null)
			beauty.searchsite();

		// nordstrom
		mo = (int) (NordstromConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && nordstrom != null)
			nordstrom.searchsite();

		// macys
		mo = (int) (MacysConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && macys != null)
			macys.searchsite();

		// sierratradingpost
		mo = (int) (SierratradingpostConstant.sleeptime / Constant.sleeptime);
		if (i % mo == 0 && sierratradingpost != null)
			sierratradingpost.searchsite();

		i = i + 1;
		WeixinHelper.disconnect2Weixin();
		System.out.println("sleep:" + new Date());

	}
	// @Override
	// public void execute(JobExecutionContext arg0) throws
	// JobExecutionException {
	// // 输出执行myjob的时间
	// System.out.println(new Date() + ",start task...");
	// af.getnewproduct();
	// hollisterco.getnewproduct();
	// // Ralphlauren.getnewproduct();
	//
	// // System.out.println(new Date() + ",done task.");
	// }

}
