package kxg.searchaf.url.jcrew;

import java.util.ArrayList;
import java.util.List;

public class JcrewPage {

	public String url;

	public JcrewPage(String url) {
		this.url = url;
	}

	public static List<JcrewPage> getMenSale() {
		ArrayList<JcrewPage> urllist = new ArrayList<JcrewPage>();
		// String type = "sale";
		// String category = "";

		String url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nu%3DP_productid%26N%3D20%2B16&FOLDER<>folder_id=1408474395181063&bmUID=jVDtqH0&navLoc=left_nav&NUM_ITEMS=90";
		JcrewPage page = new JcrewPage(url);
		urllist.add(page);

		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D90%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B16&NUM_ITEMS=90&PRODUCT%3C%3Eprd_id=845524441829378&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVA3rto";
		page = new JcrewPage(url);
		urllist.add(page);

		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D180%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B16&NUM_ITEMS=90&PRODUCT%3C%3Eprd_id=845524441829378&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVA3rtp";
		page = new JcrewPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<JcrewPage> getWomenSale() {
		ArrayList<JcrewPage> urllist = new ArrayList<JcrewPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVFb_pF";
		JcrewPage page = new JcrewPage(url);
		urllist.add(page);

		// 2
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D90%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVFbtLQ";
		page = new JcrewPage(url);
		urllist.add(page);

		// 3
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D180%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVBaD36";
		page = new JcrewPage(url);
		urllist.add(page);

		// 4
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D270%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVBaD37";
		page = new JcrewPage(url);
		urllist.add(page);

		// 5
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D360%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVBaD38";
		page = new JcrewPage(url);
		urllist.add(page);

		// 6
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D450%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVBaD39";
		page = new JcrewPage(url);
		urllist.add(page);

		// 7
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D540%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVBaD3a";
		page = new JcrewPage(url);
		urllist.add(page);

		// 8
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D630%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVBaD3b";
		page = new JcrewPage(url);
		urllist.add(page);

		// 9
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D720%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jXCx9JP";
		page = new JcrewPage(url);
		urllist.add(page);

		// 10
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D810%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B17&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jXCx9JQ";
		page = new JcrewPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<JcrewPage> getBoysInstance() {
		ArrayList<JcrewPage> urllist = new ArrayList<JcrewPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		// big boy
		String url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B18&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVFcoMz";
		JcrewPage page = new JcrewPage(url);
		urllist.add(page);
		
		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D90%26Nk%3Dnone%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%2B225%26Nu%3DP_productid_compositekey%26N%3D20%2B18&NUM_ITEMS=90&PRODUCT%3C%3Eprd_id=845524441829378&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=k7JBfeh";
		page = new JcrewPage(url);
		urllist.add(page);
		
			
		return urllist;
	}

	public static List<JcrewPage> getGirlsInstance() {
		ArrayList<JcrewPage> urllist = new ArrayList<JcrewPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		// big gril
		String url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B19&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVFcoMy";
		JcrewPage page = new JcrewPage(url);
		urllist.add(page);

		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D90%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B19&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVFcpCW";
		page = new JcrewPage(url);
		urllist.add(page);

		url = "http://www.jcrew.com/search/searchNavigation.jsp?eneQuery=Nao%3D180%26Ne%3D1%2B2%2B3%2B22%2B4294967294%2B20%26Nu%3DP_productid%26N%3D20%2B19&NUM_ITEMS=90&FOLDER%3C%3Efolder_id=1408474395181063&bmUID=jVFcpCY";
		page = new JcrewPage(url);
		urllist.add(page);

		return urllist;
	}

}
