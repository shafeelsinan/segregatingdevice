package net.codejava.vo;

import java.util.ArrayList;
import java.util.List;

import net.codejava.domain.PaymentDetails;

public class BuyVo {

	private Long id;
	private String docnum;
	private String createdtime ;
	private Boolean active = Boolean.TRUE;
	private String status;
	private String productname;
	private String username;
	private Long userid;
	private Long productid;
	private Double sellingprice;
	private Long qty;
	private String remarks;
	private Long currenstockId;
	private Double totalAmount = 0d;
	private List<PaymentDetails> paymentList = new ArrayList<PaymentDetails>();
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocnum() {
		return docnum;
	}
	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public Double getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(Double sellingprice) {
		this.sellingprice = sellingprice;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getCurrenstockId() {
		return currenstockId;
	}
	public void setCurrenstockId(Long currenstockId) {
		this.currenstockId = currenstockId;
	}
	public List<PaymentDetails> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<PaymentDetails> paymentList) {
		this.paymentList = paymentList;
	}
	
}
