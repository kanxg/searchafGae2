package kxg.searchaf.url.toryburch;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mongodb.WriteResult;

public class ToryburchMailListJSP {
	public ToryburchMailList loadMail(String mailaddress) {
		try {
			ToryburchMailListMongoDao dao = new ToryburchMailListMongoDao();
			return dao.find(mailaddress);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String updateMail(String mailaddress, boolean warningWoMen,
			String womencheckingSaleDiscount) {
		String ErrMsg = "";
		try {
			ToryburchMailListMongoDao dao = new ToryburchMailListMongoDao();

			ToryburchMailList afMailList = dao.find(mailaddress);
			if (afMailList == null) {
				ErrMsg = "notexist";
			} else {
				afMailList.warningWomen = warningWoMen;

				afMailList.womencheckingSaleDiscount = Float
						.parseFloat(womencheckingSaleDiscount);

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
			ToryburchMailListMongoDao dao = new ToryburchMailListMongoDao();

			ToryburchMailList amaildb = dao.find(mailaddress);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				ToryburchMailList afMailList = new ToryburchMailList();
				afMailList.mailaddress = mailaddress;
				afMailList.userType = "tryer";
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(new Date());
				rightNow.add(Calendar.DAY_OF_YEAR, ToryburchConstant.tryerLicenseDay);// 日期加7天
				afMailList.valideTime = rightNow.getTime();

				afMailList.warningWomen = true;

				afMailList.womencheckingSaleDiscount = ToryburchConstant.warningdiscount;

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
		ToryburchMailListJSP jsp = new ToryburchMailListJSP();
		ToryburchMailList afMailList = jsp.loadMail("308003936@qq.com");
		System.out.println(afMailList);
		if (afMailList != null) {

			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				afMailList.valideTime = df.parse("2013-11-19");
				// afMailList.userType="buyer";
				ToryburchMailListMongoDao dao = new ToryburchMailListMongoDao();
				dao.update(afMailList);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// List<ToryburchMailList> afmailist =
		// ToryburchMailList.getinstance();
		// for (int i = 0; i < afmailist.size(); i++) {
		// ToryburchMailList afMailList = afmailist.get(i);
		// System.out.println(afMailList);
		//
		// Calendar rightNow = Calendar.getInstance();
		// rightNow.setTime(afMailList.valideTime);
		// rightNow.add(Calendar.DAY_OF_YEAR, 7);// 日期加7天
		// afMailList.valideTime = rightNow.getTime();
		//
		// try {
		// ToryburchMailListMongoDao dao = new ToryburchMailListMongoDao();
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
