package net.codejava.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCKLEDGER")
public class StockLedger {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ledgerid;
	
	private Long productid;
	private Long userid;
	private Long inserttype;
	private Long qty;
	private Long currenstockqty;
	private Long currentstockid;
	private String docnum;
	private Long docid;
	private Long doctypenum;
	private String createdtime;
	private Long stockinqty;
	private Long stockoutqty;
	private Double sellerprice;
	private Double sellingprice;
	private Double discount;
	
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
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
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
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
	
	

}