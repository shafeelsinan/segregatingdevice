package net.codejava.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PAYMENTDETAILS")
public class PaymentDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long docid;
	private String docnum;
	private Double amountpaid=0d;
	private Double actualamount=0d;
	private Long paymentmodeid;
	private String paymentmode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="buy_id",nullable = false)
	private BuyMain buy = new BuyMain();
	
	
	public BuyMain getBuy() {
		return buy;
	}
	public void setBuy(BuyMain buy) {
		this.buy = buy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDocid() {
		return docid;
	}
	public void setDocid(Long docid) {
		this.docid = docid;
	}
	public String getDocnum() {
		return docnum;
	}
	public void setDocnum(String docnum) {
		this.docnum = docnum;
	}
	public Double getAmountpaid() {
		return amountpaid;
	}
	public void setAmountpaid(Double amountpaid) {
		this.amountpaid = amountpaid;
	}
	public Double getActualamount() {
		return actualamount;
	}
	public void setActualamount(Double actualamount) {
		this.actualamount = actualamount;
	}
	public Long getPaymentmodeid() {
		return paymentmodeid;
	}
	public void setPaymentmodeid(Long paymentmodeid) {
		this.paymentmodeid = paymentmodeid;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	
	
	

}
