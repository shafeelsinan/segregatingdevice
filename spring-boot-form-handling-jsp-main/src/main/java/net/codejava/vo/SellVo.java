package net.codejava.vo;
import java.util.Date;

import net.codejava.HisUser;
import net.codejava.domain.ProductMain;

public class SellVo {
	
	private Long id;
	private String docnum;
	
	private String createdtime ;
	private String recycletime;
	
	private Boolean active;
	private String status;
	
	private String productname;
	private String username;
	private Long userid;
	private Long productid;
	private Double price;
	private Long qty;
	private String remarks;
	private String adminRemarks;
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
	public String getRecycletime() {
		return recycletime;
	}
	public void setRecycletime(String recycletime) {
		this.recycletime = recycletime;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	public String getAdminRemarks() {
		return adminRemarks;
	}
	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}
	
	
}
