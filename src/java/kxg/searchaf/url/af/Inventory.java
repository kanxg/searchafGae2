package kxg.searchaf.url.af;

public class Inventory {

	public String name;
	public String seq;
	public String listprice;
	public String imgPrefix;
	public String longSku;

	public String itemSize;
	public String itemSeq;
	public String itemInventory;
	public String sku;
	public String cat;

	public Inventory(String name, String seq, String listprice,
			String imgPrefix, String longSku, String itemSize, String itemSeq,
			String itemInventory, String sku, String cat) {
		this.name = name;
		this.seq = seq;
		this.listprice = listprice;
		this.imgPrefix = imgPrefix;
		this.longSku = longSku;

		this.itemSize = itemSize;
		this.itemSeq = itemSeq;
		this.itemInventory = itemInventory;
		this.sku = sku;
		this.cat = cat;
	}
}
