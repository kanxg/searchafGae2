package kxg.searchaf.url.aptamil;

public class AptamilProduct implements Comparable<Object> {

	public String website;
	public String name;
	public String duanshu;
	public boolean hasInventory;
	public Integer inventory = 0;
	public String buylink;

	public String toString() {
		String matchpro = "";

		matchpro = matchpro + "网站：" + website;
		matchpro = matchpro + " 产品：" + name;
		if (hasInventory) {
			matchpro = matchpro + " 到货！";
			if (inventory != 0) {
				matchpro = matchpro + " 库存：" + inventory;
			}
			matchpro = matchpro + " <a href=\" " + buylink + "\">购买链接</a>";
		} else {
			matchpro = matchpro + " 无货！";
		}
		return matchpro;
	}

	@Override
	public int compareTo(Object o) {
		AptamilProduct comp = (AptamilProduct) o;
		// return this.realdiscount - comp.realdiscount > 0.01 ? 1 : -1;

		int flag = this.website.compareTo(comp.website);
		if (flag != 0)
			return flag;
		flag = this.name.compareTo(comp.name);
		if (flag != 0)
			return flag;

		// flag = this.name.compareTo(comp.name);
		// if (flag != 0)
		// return flag;
		// return 0;

		return flag;
	}
}
