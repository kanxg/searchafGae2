package kxg.searchaf.url.af;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AfMailListJSP {
	public AfMailList loadMail(String mailaddress) {
		try {
			AfMailListMongoDao dao = new AfMailListMongoDao();
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
			AfMailListMongoDao dao = new AfMailListMongoDao();

			AfMailList afMailList = dao.find(mailaddress);
			if (afMailList == null) {
				ErrMsg = "notexist";
			} else {
				afMailList.warningMen = warningMen;
				afMailList.warningWomen = warningWoMen;
				afMailList.warningCode = warningCode;
				afMailList.mencheckingRegular = mencheckingRegular;
				afMailList.mencheckingSecretSale = mencheckingSecretSale;
				afMailList.mencheckingClearance = mencheckingClearance;
				afMailList.mencheckingSale = mencheckingSale;
				afMailList.mencheckingClearanceDiscount = Float
						.parseFloat(mencheckingClearanceDiscount) / 100;
				afMailList.mencheckingSaleDiscount = Float
						.parseFloat(mencheckingSaleDiscount) / 100;
				afMailList.mencheckingCategory = Arrays
						.asList(mencheckingCategory);
				afMailList.mencheckingSize = Arrays.asList(mencheckingSize);
				afMailList.womencheckingRegular = womencheckingRegular;
				afMailList.womencheckingSecretSale = womencheckingSecretSale;
				afMailList.womencheckingClearance = womencheckingClearance;
				afMailList.womencheckingSale = womencheckingSale;
				afMailList.womencheckingClearanceDiscount = Float
						.parseFloat(womencheckingClearanceDiscount) / 100;
				afMailList.womencheckingSaleDiscount = Float
						.parseFloat(womencheckingSaleDiscount) / 100;
				afMailList.womencheckingCategory = Arrays
						.asList(womencheckingCategory);
				afMailList.womencheckingSize = Arrays.asList(womencheckingSize);
				dao.update(afMailList);
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
			AfMailListMongoDao dao = new AfMailListMongoDao();

			AfMailList amaildb = dao.find(mailaddress);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				AfMailList afMailList = new AfMailList();
				afMailList.mailaddress = mailaddress;
				afMailList.userType = "tryer";
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.DAY_OF_YEAR, AfConstant.tryerLicenseDay);// 日期加7天
				afMailList.valideTime = rightNow.getTime();
				afMailList.warningMen = true;
				afMailList.warningWomen = true;
				afMailList.warningCode = false;
				afMailList.mencheckingRegular = false;
				afMailList.mencheckingSecretSale = false;
				afMailList.mencheckingClearance = true;
				afMailList.mencheckingSale = true;
				afMailList.mencheckingClearanceDiscount = AfConstant.warningdiscount;
				afMailList.mencheckingSaleDiscount = AfConstant.warningdiscount;
				afMailList.mencheckingCategory = new ArrayList<String>();
				afMailList.mencheckingCategory.add("POLOS");
				afMailList.mencheckingSize = new ArrayList<String>();
				afMailList.mencheckingSize.add("S");
				afMailList.womencheckingRegular = false;
				afMailList.womencheckingSecretSale = false;
				afMailList.womencheckingClearance = true;
				afMailList.womencheckingSale = true;
				afMailList.womencheckingClearanceDiscount = AfConstant.warningdiscount;
				afMailList.womencheckingSaleDiscount = AfConstant.warningdiscount;
				afMailList.womencheckingCategory = new ArrayList<String>();
				afMailList.womencheckingCategory.add("POLOS");
				afMailList.womencheckingSize = new ArrayList<String>();
				afMailList.womencheckingSize.add("S");
				dao.save(afMailList);
				returnkey = "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnkey;
	}

	public static void main(String[] args) throws UnknownHostException {
//		AfMailListJSP jsp = new AfMailListJSP();
//		AfMailList afMailList = jsp.loadMail("308003936@qq.com");
//		System.out.println(afMailList);
//		if (afMailList != null) {
//
//			try {
//				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//				afMailList.valideTime = df.parse("2013-11-19");
//				// afMailList.userType="buyer";
//				AfMailListMongoDao dao = new AfMailListMongoDao();
//				dao.update(afMailList);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		 AfMailListMongoDao dao = new AfMailListMongoDao();
		 List<AfMailList> afmailist = AfMailList.getinstance();
		 for (int i = 0; i < afmailist.size(); i++) {
		 AfMailList afMailList = afmailist.get(i);
		 System.out.print(afMailList.mailaddress+",");
 

//		 try {
//			 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//				Date quTime = df.parse("2014-12-12");
//				
//			 afMailList.valideTime =quTime;
//			
//			 dao.update(afMailList);
//		 } catch (Exception e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
		
		 // System.out.println(afMailList);
		 }

		// for (int i = 0; i < afMailList.mencheckingCategory.size(); i++) {
		// System.out.println(afMailList.mencheckingCategory.get(i));
		// }
	}
}
