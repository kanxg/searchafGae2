package kxg.searchaf.url.amazonWeibo;

public class AmazonProduct implements Comparable<Object> {
	public String category;
	public String name;
	public String label;
	public float listprice = 0;
	public float price = 0;
	public String url;
	public String img_src;

	public String toString() {
		String matchpro = "";

		matchpro = matchpro + "][" + category;
		matchpro = matchpro + "][" + name;
		matchpro = matchpro + "][" + label;
		matchpro = matchpro + "][标价:" + listprice;
		matchpro = matchpro + "][现价:" + price;
		matchpro = matchpro + "]";

		return matchpro;
	}

	@Override
	public int compareTo(Object o) {
		AmazonProduct comp = (AmazonProduct) o;

		int flag = this.category.compareTo(comp.category);
		if (flag != 0)
			return flag;

		flag = this.name.compareTo(comp.name);
		if (flag != 0)
			return flag;

		return 0;
	}
}
