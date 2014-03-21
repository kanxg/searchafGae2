package kxg.searchaf.url.chunqiu;

public class ChunqiuLaihuiResult implements Comparable<Object> {
	public ChunqiuProduct qu;
	public ChunqiuProduct hui;
	public float price = 0;

	public ChunqiuLaihuiResult(ChunqiuProduct qu, ChunqiuProduct hui) {
		this.qu = qu;
		this.hui = hui;
		this.price = qu.price + hui.price;
	}

	public String toString() {

		String matchpro = "";

		matchpro = matchpro + "[航班" + qu.hangban;
		matchpro = matchpro + "][时间:" + qu.qifeitime;
		matchpro = matchpro + "][现价:" + qu.price + "]";

		matchpro = matchpro + "[航班" + hui.hangban;
		matchpro = matchpro + "][时间:" + hui.qifeitime;
		matchpro = matchpro + "][现价:" + hui.price + "]";

		matchpro = matchpro + "][总价:" + price + "]";

		// matchpro = matchpro + "][color:";
		// if (colorList != null) {
		// for (int i = 0; i < colorList.length; i++) {
		// matchpro = matchpro + colorList[i] + ",";
		// }
		// }
		// matchpro = matchpro + "]";
		return matchpro;
	}

	public String toJspString() {
		return toString();
	}

	@Override
	public int compareTo(Object o) {
		ChunqiuLaihuiResult comp = (ChunqiuLaihuiResult) o;
		int flag = 0;
		flag = (int) this.price - (int) comp.price;
		if (flag != 0)
			return flag;

		flag = this.qu.qifeitime.compareTo(comp.hui.qifeitime);
		if (flag != 0)
			return flag;

		return 0;

	}
}
