package kxg.searchaf.url.tommy;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.WriteResult;

public class TommyMailListJSP {
	public TommyMailList loadMail(String mailaddress) {
		try {
			TommyMailListMongoDao dao = new TommyMailListMongoDao();
			return dao.find(mailaddress);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String updateMail(String mailaddress, boolean warningMen,
			String mencheckingSaleDiscount, boolean warningWoMen,
			String womencheckingSaleDiscount, boolean warningBoy,
			String boycheckingSaleDiscount, boolean warningGirl,
			String girlcheckingSaleDiscount) {
		String ErrMsg = "";
		try {
			TommyMailListMongoDao dao = new TommyMailListMongoDao();

			TommyMailList afMailList = dao.find(mailaddress);
			if (afMailList == null) {
				ErrMsg = "notexist";
			} else {
				afMailList.warningMen = warningMen;
				afMailList.mencheckingSaleDiscount = Float
						.parseFloat(mencheckingSaleDiscount);

				afMailList.warningWomen = warningWoMen;
				afMailList.womencheckingSaleDiscount = Float
						.parseFloat(womencheckingSaleDiscount);

				afMailList.warningBoy = warningBoy;
				afMailList.boycheckingSaleDiscount = Float
						.parseFloat(boycheckingSaleDiscount);

				afMailList.warningGirl = warningWoMen;
				afMailList.girlcheckingSaleDiscount = Float
						.parseFloat(girlcheckingSaleDiscount);

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
			TommyMailListMongoDao dao = new TommyMailListMongoDao();

			TommyMailList amaildb = dao.find(mailaddress);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				TommyMailList afMailList = new TommyMailList();
				afMailList.mailaddress = mailaddress;
				afMailList.userType = "tryer";
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.DAY_OF_YEAR, TommyConstant.tryerLicenseDay);// 日期加7天
				afMailList.valideTime = rightNow.getTime();

				afMailList.warningMen = true;
				afMailList.mencheckingSaleDiscount = TommyConstant.warningdiscount;

				afMailList.warningWomen = true;
				afMailList.womencheckingSaleDiscount = TommyConstant.warningdiscount;

				afMailList.warningBoy = true;
				afMailList.boycheckingSaleDiscount = TommyConstant.warningdiscount;

				afMailList.warningGirl = true;
				afMailList.girlcheckingSaleDiscount = TommyConstant.warningdiscount;

				dao.save(afMailList);
				returnkey = "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnkey;
	}

	public static void main(String[] args) {
		TommyMailListJSP jsp = new TommyMailListJSP();
		TommyMailList afMailList = jsp.loadMail("308003936@qq.com");
		System.out.println(afMailList);
		if (afMailList != null) {

			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				afMailList.valideTime = df.parse("2013-11-19");
				// afMailList.userType="buyer";
				TommyMailListMongoDao dao = new TommyMailListMongoDao();
				dao.update(afMailList);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// List<TommyMailList> afmailist =
		// TommyMailList.getinstance();
		// for (int i = 0; i < afmailist.size(); i++) {
		// TommyMailList afMailList = afmailist.get(i);
		// System.out.println(afMailList);
		//
		// Calendar rightNow = Calendar.getInstance();
		// rightNow.setTime(afMailList.valideTime);
		// rightNow.add(Calendar.DAY_OF_YEAR, 7);// 日期加7天
		// afMailList.valideTime = rightNow.getTime();
		//
		// try {
		// TommyMailListMongoDao dao = new TommyMailListMongoDao();
		// dao.update(afMailList);
		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// // System.out.println(afMailList);
		// }

		// for (int i = 0; i < afMailList.mencheckingCategory.size(); i++) {
		// System.out.println(afMailList.mencheckingCategory.get(i));
		// }
	}
}
