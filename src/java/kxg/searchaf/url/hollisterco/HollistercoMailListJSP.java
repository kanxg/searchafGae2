package kxg.searchaf.url.hollisterco;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kxg.searchaf.url.af.AfMailList;
import kxg.searchaf.url.af.AfMailListMongoDao;

import com.mongodb.WriteResult;

public class HollistercoMailListJSP {
	public HollistercoMailList loadMail(String mailaddress) {
		try {
			HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();
			return dao.find(mailaddress);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String updateMail(String mailaddress, boolean warningMen,
			boolean warningWoMen, boolean warningCode,
			boolean mencheckingRegular, boolean mencheckingSecretSale,
			boolean mencheckingClearance, boolean mencheckingSale,
			String mencheckingClearanceDiscount,
			String mencheckingSaleDiscount, String[] mencheckingCategory,
			String[] mencheckingSize, boolean womencheckingRegular,
			boolean womencheckingSecretSale, boolean womencheckingClearance,
			boolean womencheckingSale, String womencheckingClearanceDiscount,
			String womencheckingSaleDiscount, String[] womencheckingCategory,
			String[] womencheckingSize) {
		String ErrMsg = "";
		try {
			HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();

			HollistercoMailList hollistercoMailList = dao.find(mailaddress);
			if (hollistercoMailList == null) {
				ErrMsg = "notexist";
			} else {
				hollistercoMailList.warningMen = warningMen;
				hollistercoMailList.warningWomen = warningWoMen;
				hollistercoMailList.warningCode = warningCode;
				hollistercoMailList.mencheckingRegular = mencheckingRegular;
				hollistercoMailList.mencheckingSecretSale = mencheckingSecretSale;
				hollistercoMailList.mencheckingClearance = mencheckingClearance;
				hollistercoMailList.mencheckingSale = mencheckingSale;
				hollistercoMailList.mencheckingClearanceDiscount = Float
						.parseFloat(mencheckingClearanceDiscount);
				hollistercoMailList.mencheckingSaleDiscount = Float
						.parseFloat(mencheckingSaleDiscount);
				hollistercoMailList.mencheckingCategory = Arrays
						.asList(mencheckingCategory);
				hollistercoMailList.mencheckingSize = Arrays
						.asList(mencheckingSize);
				hollistercoMailList.womencheckingRegular = womencheckingRegular;
				hollistercoMailList.womencheckingSecretSale = womencheckingSecretSale;
				hollistercoMailList.womencheckingClearance = womencheckingClearance;
				hollistercoMailList.womencheckingSale = womencheckingSale;
				hollistercoMailList.womencheckingClearanceDiscount = Float
						.parseFloat(womencheckingClearanceDiscount);
				hollistercoMailList.womencheckingSaleDiscount = Float
						.parseFloat(womencheckingSaleDiscount);
				hollistercoMailList.womencheckingCategory = Arrays
						.asList(womencheckingCategory);
				hollistercoMailList.womencheckingSize = Arrays
						.asList(womencheckingSize);
				dao.update(hollistercoMailList);
				ErrMsg = "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress) {
		String returnkey = "";

		try {
			HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();

			HollistercoMailList amaildb = dao.find(mailaddress);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				HollistercoMailList hollistercoMailList = new HollistercoMailList();
				hollistercoMailList.mailaddress = mailaddress;
				hollistercoMailList.userType = "tryer";
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.DAY_OF_YEAR,
						HollistercoConstant.tryerLicenseDay);// 日期加30天
				hollistercoMailList.valideTime = rightNow.getTime();
				hollistercoMailList.warningMen = true;
				hollistercoMailList.warningWomen = true;
				hollistercoMailList.warningCode = false;
				hollistercoMailList.mencheckingRegular = false;
				hollistercoMailList.mencheckingSecretSale = false;
				hollistercoMailList.mencheckingClearance = true;
				hollistercoMailList.mencheckingSale = true;
				hollistercoMailList.mencheckingClearanceDiscount = HollistercoConstant.warningdiscount;
				hollistercoMailList.mencheckingSaleDiscount = HollistercoConstant.warningdiscount;
				hollistercoMailList.mencheckingCategory = new ArrayList<String>();
				hollistercoMailList.mencheckingCategory.add("POLOS");
				hollistercoMailList.mencheckingSize = new ArrayList<String>();
				hollistercoMailList.mencheckingSize.add("S");
				hollistercoMailList.womencheckingRegular = false;
				hollistercoMailList.womencheckingSecretSale = false;
				hollistercoMailList.womencheckingClearance = true;
				hollistercoMailList.womencheckingSale = true;
				hollistercoMailList.womencheckingClearanceDiscount = HollistercoConstant.warningdiscount;
				hollistercoMailList.womencheckingSaleDiscount = HollistercoConstant.warningdiscount;
				hollistercoMailList.womencheckingCategory = new ArrayList<String>();
				hollistercoMailList.womencheckingCategory.add("POLOS");
				hollistercoMailList.womencheckingSize = new ArrayList<String>();
				hollistercoMailList.womencheckingSize.add("S");
				dao.save(hollistercoMailList);
				returnkey = "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnkey;
	}

	public static void main(String[] args) throws Exception {
//		HollistercoMailListJSP jsp = new HollistercoMailListJSP();
//		HollistercoMailList hollistercoMailList = jsp
//				.loadMail("xingang.af1@gmail.com");
//		System.out.println(hollistercoMailList);
//
//		if (hollistercoMailList != null) {
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			hollistercoMailList.valideTime = df.parse("2015-09-13");
//			hollistercoMailList.userType = "buyer";
//			HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();
//			dao.update(hollistercoMailList);
//		}
//		System.out.println(hollistercoMailList);

		// for (int i = 0; i < hollistercoMailList.mencheckingCategory.size();
		// i++) {
		// System.out.println(hollistercoMailList.mencheckingCategory.get(i));
		// }

		HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();
		List<HollistercoMailList> afmailist = HollistercoMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			HollistercoMailList afMailList = afmailist.get(i);
			System.out.print(afMailList.mailaddress+",");

//			try {
//				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//				Date quTime = df.parse("2014-12-12");
//
//				afMailList.valideTime = quTime;
//
//				dao.update(afMailList);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
