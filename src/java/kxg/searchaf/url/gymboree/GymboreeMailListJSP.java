package kxg.searchaf.url.gymboree;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.WriteResult;

public class GymboreeMailListJSP {
	public GymboreeMailList loadMail(String mailaddress) {
		try {
			GymboreeMailListMongoDao dao = new GymboreeMailListMongoDao();
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
			GymboreeMailListMongoDao dao = new GymboreeMailListMongoDao();

			GymboreeMailList afMailList = dao.find(mailaddress);
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
			GymboreeMailListMongoDao dao = new GymboreeMailListMongoDao();

			GymboreeMailList amaildb = dao.find(mailaddress);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				GymboreeMailList afMailList = new GymboreeMailList();
				afMailList.mailaddress = mailaddress;
				afMailList.userType = "tryer";
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.DAY_OF_YEAR, GymboreeConstant.tryerLicenseDay);// 日期加7天
				afMailList.valideTime = rightNow.getTime();

				afMailList.warningMen = true;
				afMailList.mencheckingSaleDiscount = GymboreeConstant.warningdiscount;

				afMailList.warningWomen = true;
				afMailList.womencheckingSaleDiscount = GymboreeConstant.warningdiscount;

				afMailList.warningBoy = true;
				afMailList.boycheckingSaleDiscount = GymboreeConstant.warningdiscount;

				afMailList.warningGirl = true;
				afMailList.girlcheckingSaleDiscount = GymboreeConstant.warningdiscount;

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
		GymboreeMailListJSP jsp = new GymboreeMailListJSP();
		GymboreeMailList afMailList = jsp.loadMail("308003936@qq.com");
		System.out.println(afMailList);
		if (afMailList != null) {

			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				afMailList.valideTime = df.parse("2013-11-19");
				// afMailList.userType="buyer";
				GymboreeMailListMongoDao dao = new GymboreeMailListMongoDao();
				dao.update(afMailList);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// List<GymboreeMailList> afmailist =
		// GymboreeMailList.getinstance();
		// for (int i = 0; i < afmailist.size(); i++) {
		// GymboreeMailList afMailList = afmailist.get(i);
		// System.out.println(afMailList);
		//
		// Calendar rightNow = Calendar.getInstance();
		// rightNow.setTime(afMailList.valideTime);
		// rightNow.add(Calendar.DAY_OF_YEAR, 7);// 日期加7天
		// afMailList.valideTime = rightNow.getTime();
		//
		// try {
		// GymboreeMailListMongoDao dao = new GymboreeMailListMongoDao();
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
