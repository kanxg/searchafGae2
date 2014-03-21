package kxg.searchaf.url.af;

public class AfCode implements Comparable<Object> {
	public String name;
	public String url;
	public String tagcontext;

	public String toString() {
		String matchpro = "";
		matchpro = matchpro + "折扣:" + name;
		return matchpro;
	}

	public String toJspString() {
		String matchpro = "";
		// matchpro = matchpro + "折扣:" + name + "<br/>" ;
		matchpro = matchpro + tagcontext;
		return matchpro;
	}

	@Override
	public int compareTo(Object o) {
		AfCode comp = (AfCode) o;
		// return this.realdiscount - comp.realdiscount > 0.01 ? 1 : -1;

		int flag = 0;
		flag = this.name.compareTo(comp.name);
		if (flag != 0)
			return flag;
		// flag = this.name.compareTo(comp.name);
		// if (flag != 0)
		// return flag;
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AfCode other = (AfCode) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
