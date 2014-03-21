package kxg.searchaf.url.chunqiu;

public class ChunqiuProduct implements Comparable<Object> {
	public String addReason = "";
	public String hangban = "";
	public String qifeitime = "";
	public String qifei;
	public String daoda;
	public String cangwei;
	public float price = 0;

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

		matchpro = matchpro + "[航班" + hangban + "]";
		matchpro = matchpro + "[" + qifei + "-" + daoda + "]";
		matchpro = matchpro + "[时间:" + qifeitime;
		matchpro = matchpro + "][现价:" + price + "]";

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
		String matchpro = "";
		if (!"".equals(addReason)) {
			matchpro = matchpro + "[" + addReason + "]";
		}
		// matchpro = matchpro + "[" + type;
		// matchpro = matchpro + "][" + category + "]" ;

		matchpro = matchpro + "[航班" + hangban;
		matchpro = matchpro + "][时间:" + qifeitime;
		matchpro = matchpro + "][现价:" + price + "]";

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hangban == null) ? 0 : hangban.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result
				+ ((qifeitime == null) ? 0 : qifeitime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChunqiuProduct other = (ChunqiuProduct) obj;
		if (hangban == null) {
			if (other.hangban != null)
				return false;
		} else if (!hangban.equals(other.hangban))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (qifeitime == null) {
			if (other.qifeitime != null)
				return false;
		} else if (!qifeitime.equals(other.qifeitime))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		ChunqiuProduct comp = (ChunqiuProduct) o;
		int flag = 0;

		flag = (int) this.price - (int) comp.price;
		if (flag != 0)
			return flag;

		flag = this.qifeitime.compareTo(comp.qifeitime);
		if (flag != 0)
			return flag;

		return 0;
	}

}
