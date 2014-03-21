package kxg.searchaf.url.amazon;

public class AmazonPage {

	public static String salestag_us = "t=ebuybot-20&tag=ebuybot-20";

	public static String salestag_de = "t=ebuybot-21&tag=ebuybot-21";
	public static String salestag_uk = "t=ebuybot00-21&tag=ebuybot00-21";

	public static String salestag_fr = "t=ebuybot0b-21&tag=ebuybot0b-21";
	public static String salestag_es = "t=ebuybot0d0-21&tag=ebuybot0d0-21";
	public static String salestag_it = "t=ebuybot05-21&tag=ebuybot05-21";

	public static String salestag_cn = "t=ebuybot-23&tag=ebuybot-23";
	public static String salestag_jp = "t=ebuybot-23&tag=ebuybot-22";

	public static String salebyAmazonTag = "smid=ATVPDKIKX0DER";
	public String prodname;
	public String url;
	public boolean checksalerisAmazon;
	public float expectPirce;

	public String checkType;

	public float price;
	public String title;
	//public String userid;
	public String mailaddress;
	public String weixin;
	public String tagcontext;

	public boolean salerisAmazon;
	public boolean instock = false;

	public String ASIN;

 
	public AmazonPage() {
	}

	public AmazonPage(String url, String mailaddress) {
		this.mailaddress = mailaddress;
		this.url = url;
	}

	public String getUrl() {
		if (checksalerisAmazon)
			return getSalerisAmazonUrl(this.url);
		return url;
	}

	public String getJspUrl() {
		return addSalesTag(getUrl());
	}

	private String getSalerisAmazonUrl(String url) {
		String newurl = url.replaceAll("smid=", "smiid=");
		int i = newurl.indexOf("?");
		if (i > 0) {
			newurl = newurl + "&" + salebyAmazonTag;
		} else {
			newurl = newurl + "?" + salebyAmazonTag;
		}
		return newurl;
	}

	private String addSalesTag(String url) {
		String newurl = url.replaceAll("tag=", "taag=").replaceAll("t=", "tt=");
		int i = newurl.indexOf("?");
		if (i > 0) {
			newurl = newurl + "&" + getSalestag(url);
		} else {
			newurl = newurl + "?" + getSalestag(url);
		}
		return newurl;
	}

	private String getSalestag(String url) {
		if (url.startsWith("http://www.amazon.com")) {
			return salestag_us;
		} else if (url.startsWith("http://www.amazon.cn")) {
			return salestag_cn;
		} else if (url.startsWith("http://www.amazon.jp")) {
			return salestag_jp;
		} else if (url.startsWith("http://www.amazon.de")) {
			return salestag_de;
		} else if (url.startsWith("http://www.amazon.co.uk")) {
			return salestag_uk;
		} else if (url.startsWith("http://www.amazon.fr")) {
			return salestag_fr;
		} else if (url.startsWith("http://www.amazon.es")) {
			return salestag_es;
		} else if (url.startsWith("http://www.amazon.it")) {
			return salestag_it;
		}
		return salestag_us;
	}

	public String parseHTML() {
		String html = "您观望的产品<" + this.prodname + ">已经到达您预期的价格:" + "<br/>";
		html = html + "产品名称:" + this.title + "<br/>";
		html = html + "期望价格:" + expectPirce + "<br/>";
		html = html + "当前价格:" + price + "<br/>";
		html = html + "去买咯：" + "<br/>";
		html = html + "<a href='" + addSalesTag(getUrl()) + "'>" + "点我购买"
				+ "</a>";
		html = html + "<br/><br/><br/><br/><br/>";
		html = html
				+ "<a href='http://www.ebuybot.com/jsp/amazon/addmaillist.jsp'>"
				+ "不想再接到这个提醒了！" + "</a>";
		return html;
	}

	public String getUIshowforchecksalerisAmazon() {
		if (this.checksalerisAmazon)
			return "是";
		return "";
	}

	public static void main(String[] args) throws Exception {
		String url = "http://www.amazon.com/dp/B000HZEQSU/ref=as_li_ss_til?tag=helmut-20";
		AmazonPage page = new AmazonPage();
		System.out.println(page.addSalesTag(url));
		page.price = 0l;
		if (page.price == 0) {
			// 没有取得数据
			System.out.println("is 0!");
		}

	}

	@Override
	public String toString() {
		return "AmazonPage [prodname=" + prodname + ", url=" + url
				+ ", checksalerisAmazon=" + checksalerisAmazon
				+ ", expectPirce=" + expectPirce + ", checkType=" + checkType
				+ ", price=" + price + ", title=" + title + ", mailaddress="
				+ mailaddress + ", tagcontext=" + tagcontext
				+ ", salerisAmazon=" + salerisAmazon + ", instock=" + instock
				+ ", ASIN=" + ASIN + "]";
	}
}
