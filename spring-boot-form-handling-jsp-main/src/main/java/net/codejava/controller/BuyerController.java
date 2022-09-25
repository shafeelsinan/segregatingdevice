package net.codejava.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.codejava.BeanUtils;
import net.codejava.BuyValidator;
import net.codejava.HisUser;
import net.codejava.domain.BuyMain;
import net.codejava.domain.Currenstock;
import net.codejava.domain.PaymentDetails;
import net.codejava.domain.ProductMain;
import net.codejava.repository.BuyRepository;
import net.codejava.repository.CurrenstockRepository;
import net.codejava.repository.PaymentRepository;
import net.codejava.repository.ProductRepository;
import net.codejava.repository.SellRepository;
import net.codejava.service.CurrentstockService;
import net.codejava.vo.BuyVo;
import net.codejava.vo.PaymentDetailsVo;
import net.codejava.vo.StockInsertVo;

@Controller
public class BuyerController {

	@Autowired
	@Qualifier("buyValidator")
	private BuyValidator validator;

	   @InitBinder
	   private void initBinder(WebDataBinder binder) {
	      binder.setValidator(validator);
	   }
	   
	@Autowired
	private ProductRepository prodrepository;
	
	@Autowired
	private SellRepository sellrepository;
	
	@Autowired
	private CurrentstockService service;
	
	@Autowired
	private CurrenstockRepository currentstkrepository;
	
	@Autowired
	private BuyRepository buyRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	private ModelMapper mapper;

	
	
	public BuyerController(BuyValidator validator, ProductRepository prodrepository, SellRepository sellrepository,
			CurrentstockService service, CurrenstockRepository currentstkrepository, BuyRepository buyRepository,
			PaymentRepository paymentRepository, ModelMapper mapper) {
		this.validator = validator;
		this.prodrepository = prodrepository;
		this.sellrepository = sellrepository;
		this.service = service;
		this.currentstkrepository = currentstkrepository;
		this.buyRepository = buyRepository;
		this.paymentRepository = paymentRepository;
		this.mapper = mapper;
	}

	@GetMapping("/viewallcurrenstocklist")
	public String showAllCurrenstockReq(Model model,HttpSession session) {
			List<Currenstock> productList = currentstkrepository.findByCurrenstockqtyGreaterThan(0l);
			model.addAttribute("msg", productList);  
			return "buycartlist";
		}
	
	@GetMapping("/navigatetosell/{cuurentstockid}")
	public String navigateTOSellForm(@PathVariable Long cuurentstockid,Model model,HttpSession session) {
			if(session.getAttribute("USER_DETAILS") == null)
			{
				return "register_form";
			}
			else
			{
				HisUser hisuser = (HisUser) session.getAttribute("USER_DETAILS");
				Currenstock curStock = currentstkrepository.findById(cuurentstockid).get();
				//create buyvo object from currenstock
				BuyVo buyvo= new BuyVo();
				buyvo.setCurrenstockId(curStock);
				buyvo.setProductid(curStock.getProductid().getId());
				buyvo.setProductname(curStock.getProductid().getName());
				buyvo.setQty(0l);
				buyvo.setSellingprice(curStock.getSellingprice());
				buyvo.setStatus("ORDERED");
				buyvo.setUserid(hisuser.getId());
				buyvo.setUsername(hisuser.getUsername());
				PaymentDetailsVo paymnet = new PaymentDetailsVo();
				paymnet.setPaymentmode("CASH");
				paymnet.setPaymentmodeid(114l);
				
				buyvo.getPaymentList().add(paymnet);
				model.addAttribute("buy", buyvo);  
				return "buycartform";
			}
		}
	
	@PostMapping("/savebuydetails")
	public String saveBuyDetails(@ModelAttribute("buy") @Validated BuyVo buyvo,BindingResult bindingResult) throws Exception {
		
		if(BeanUtils.isNullOrZero(buyvo.getQty()))
		{
			ObjectError error = new ObjectError("globalError", "Quantity Should Be GreaterThan Zero");
			bindingResult.addError(error);
			buyvo.setQty(0l);
		}
		if(buyvo.getQty() > buyvo.getCurrenstockId().getCurrenstockqty())
		{
			ObjectError error = new ObjectError("globalError", "Should Not Be Greater Than Currenstock");
			bindingResult.addError(error);
			buyvo.setQty(0l);
		}
		
		
		if (bindingResult.hasErrors()) {
	         return "buycartform";
	     }
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		String strDate= formatter.format(date); 
		buyvo.setCreatedtime(strDate);
		buyvo.setTotalAmount(buyvo.getQty()*buyvo.getSellingprice());
		BuyMain buyMain = new BuyMain();
		buyMain = mapper.map(buyvo, BuyMain.class);
		
		buyRepository.save(buyMain);
		buyMain.setDocnum("BUY-"+buyMain.getId());
		buyRepository.save(buyMain);
		
		PaymentDetails payDet = new PaymentDetails();
		payDet = mapper.map(buyvo.getPaymentList().get(0), PaymentDetails.class);
		payDet.setBuy(buyMain);
		paymentRepository.save(payDet);
		service.processStockOut(createStockVoFromBuy(buyMain));
		return "buycartlist";
	}
	
	public StockInsertVo createStockVoFromBuy(BuyMain sell)
	{
		ProductMain prod =  prodrepository.findById(sell.getProductid()).get();
		StockInsertVo stockvo = new StockInsertVo();
		stockvo.setDocid(sell.getId());
		stockvo.setDocnum(sell.getDocnum());
		stockvo.setDoctypenum(101l);//Buy
		stockvo.setStockinqty(0l);
		stockvo.setInserttype(2l);//stockOut
		stockvo.setSellerprice(prod.getPrice());
		stockvo.getProductid().setId(sell.getProductid());
		stockvo.getUserid().setId(sell.getUserid());
		stockvo.setQty(sell.getQty());
		stockvo.setSellingprice(prod.getPrice());
		stockvo.setStockoutqty(sell.getQty());
		stockvo.setCurrentstockid(sell.getCurrenstockId().getId());
		return stockvo;
	}
	

	
	
	
	
}
