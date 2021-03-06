package kxg.searchaf.url.gymboree;

public class GymboreeProduct implements Comparable<Object> {
	public String addReason = "";
	public String type = "";
	public String category = "";
	public String name;
	public int productid;
	public float listprice = 0;
	public float price = 0;
	public float realdiscount;
	public String url;
	public String img_src;
	public String tagcontext;

	public String toString() {
		String matchpro = "";
		if (!"".equals(addReason)) {
			matchpro = matchpro + "[" + addReason + "]";
		}
		// matchpro = matchpro + "[" + type;
		// matchpro = matchpro + "][" + category + "]" ;

		matchpro = matchpro + "[" + name;
		// matchpro = matchpro + "][productid:" + productid;
		matchpro = matchpro + "][标价:" + listprice;
		matchpro = matchpro + "][现价:" + price;
		matchpro = matchpro + "][折扣:" + realdiscount + "]";
		// matchpro = matchpro + "][color:";
		// if (colorList != null) {
		// for (int i = 0; i < colorList.length; i++) {
		// matchpro = matchpro + colorList[i] + ",";
		// }
		// }
		// matchpro = matchpro + "]";
		return matchpro;
	}

	@Override
	public int compareTo(Object o) {
		GymboreeProduct comp = (GymboreeProduct) o;
		// return this.realdiscount - comp.realdiscount > 0.01 ? 1 : -1;

		int flag = this.type.compareTo(comp.type);
		if (flag != 0)
			return flag;
		flag = this.category.compareTo(comp.category);
		if (flag != 0)
			return flag;
		flag = this.name.compareTo(comp.name);
		if (flag != 0)
			return flag;
		return 0;
	}
}
