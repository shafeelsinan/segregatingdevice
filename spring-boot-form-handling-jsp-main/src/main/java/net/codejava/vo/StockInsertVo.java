package net.codejava.vo;

import net.codejava.HisUser;
import net.codejava.domain.ProductMain;

public class StockInsertVo {
	private Long id;
	private ProductMain productid = new ProductMain();
	private HisUser userid = new HisUser();
	private Long inserttype;
	private Long qty=0l;
	private Long currenstockqty;
	private Long currentstockid;
	private String docnum;
	private Long docid;
	private Long doctypenum;
	private String createdtime;
	private String updatedtime;
	private String updatedby;
	private Long stockinqty=0l;;
	private Long stockoutqty=0l;;
	private Double sellerprice;
	private Double sellingprice;
	private Double discount;
	
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(String updatedtime) {
		this.updatedtime = updatedtime;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public Long getStockinqty() {
		return stockinqty;
	}
	public void setStockinqty(Long stockinqty) {
		this.stockinqty = stockinqty;
	}
	public Long getStockoutqty() {
		return stockoutqty;
	}
	public void setStockoutqty(Long stockoutqty) {
		this.stockoutqty = stockoutqty;
	}
	public Double getSellerprice() {
		return sellerprice;
	}
	public void setSellerprice(Double sellerprice) {
		this.sellerprice = sellerprice;
	}
	public Double getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(Double sellingprice) {
		this.sellingprice = sellingprice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public ProductMain getProductid() {
		return productid;
	}
	public void setProductid(ProductMain productid) {
		this.productid = productid;
	}
	public HisUser getUserid() {
		return userid;
	}
	public void setUserid(HisUser userid) {
		this.userid = userid;
	}
	public Long getInserttype() {
		return inserttype;
	}
	public void setInserttype(Long inserttype) {
		this.inserttype = inserttype;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public Long getCurrenstockqty() {
		return currenstockqty;
	}
	public void setCurrenstockqty(Long currenstockqty) {
		this.currenstockqty = currenstockqty;
	}
	public Long getCurrentstockid() {
		return currentstockid;
	}
	public void setCurrentstockid(Long currentstockid) {
		this.currentstockid = currentstockid;
	}
	public String getDocnum() {
		return docnum;
	}
	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}
	public Long getDocid() {
		return docid;
	}
	public void setDocid(Long docid) {
		this.docid = docid;
	}
	public Long getDoctypenum() {
		return doctypenum;
	}
	public void setDoctypenum(Long doctypenum) {
		this.doctypenum = doctypenum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
