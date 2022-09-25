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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.codejava.BeanUtils;
import net.codejava.HisUser;
import net.codejava.SellValidator;
import net.codejava.domain.ProductMain;
import net.codejava.domain.Sell;
import net.codejava.repository.ProductRepository;
import net.codejava.repository.SellRepository;
import net.codejava.service.CurrentstockService;
import net.codejava.vo.SellVo;
import net.codejava.vo.StockInsertVo;

@Controller
public class SellContainer {

	@Autowired
	@Qualifier("sellValidator")
	private SellValidator validator;

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
	
	private ModelMapper mapper;

	
	
	public SellContainer(SellValidator validator, ProductRepository prodrepository, SellRepository sellrepository,
			CurrentstockService service, ModelMapper mapper) {
		this.validator = validator;
		this.prodrepository = prodrepository;
		this.sellrepository = sellrepository;
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping("/sellproduct/{prodid}")
	public String showProductForm(@PathVariable Long prodid,Model m){ 
		List<ProductMain> prodList = prodrepository.findAll();
		m.addAttribute("productlist", prodList);
		SellVo sellvo = new SellVo();
	    m.addAttribute("sell", sellvo);
		
		return "sellform";
	}
	
	@PostMapping("/sellproduct")
	public String submitProductForm(@ModelAttribute("sell") SellVo sellvo,BindingResult bindingResult,HttpSession session,Model model) {
		System.out.println(sellvo);
		if(session.getAttribute("USER_DETAILS") == null)
		{
			return "register_form";
		}
		
		if(BeanUtils.isNullOrZero(sellvo.getPrice()))
		{
			ObjectError error = new ObjectError("globalError", "Price Is Required");
			bindingResult.addError(error);
		}
		if(BeanUtils.isNullOrZero(sellvo.getQty()))
		{
			ObjectError error = new ObjectError("globalError", "Quantity Is Required");
			bindingResult.addError(error);
		}
		if(BeanUtils.isNullOrZero(sellvo.getProductid()))
		{
			ObjectError error = new ObjectError("globalError", "Product Is Required");
			bindingResult.addError(error);
		}
		ProductMain prod = prodrepository.findById(sellvo.getProductid()).get();
		if(sellvo.getPrice() > prod.getPrice())
		{
			ObjectError error = new ObjectError("globalError", "Price Is GreaterThan Original Amount. Contact Admin");
			bindingResult.addError(error);
		}
		
		if (bindingResult.hasErrors()) {
		         return "sellform";
		     }
		HisUser hisuser = (HisUser) session.getAttribute("USER_DETAILS");
		Sell sell = new Sell();
		sell = mapper.map(sellvo, Sell.class);
		
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		String strDate= formatter.format(date); 
		sell.setCreatedtime(strDate);
		sell.setUserid(hisuser.getId());
		sell.setUsername(hisuser.getName());
		sell.setProductname(prod.getName());
		sell.setStatus("SAVED");
		sellrepository.save(sell);
		sell.setDocnum("SELL-"+sell.getId());
		sellrepository.save(sell);
		
		List<Sell> productList = sellrepository.findByUserid(hisuser.getId());
		model.addAttribute("msg", productList);
		return "selllistuser";
			
	}
	
	@GetMapping("/viewsellrequser")
	public String showSellReq(Model model,HttpSession session) {
		if(session.getAttribute("USER_DETAILS") == null)
		{
			return "register_form";
		}
		else
		{
			HisUser hisuser = (HisUser) session.getAttribute("USER_DETAILS");
			List<Sell> productList = sellrepository.findByUserid(hisuser.getId());
			model.addAttribute("msg", productList);  
			model.addAttribute("type", "USER");
			model.addAttribute("headersecval", hisuser.getUsername()+"'s SELL LIST");
			return "selllistuser";
		}
	}
	
	@GetMapping("/viewallsavedsellrequser")
	public String showAllSellReq(Model model,HttpSession session) {
			List<Sell> productList = sellrepository.findByStatus("SAVED");
			model.addAttribute("msg", productList);  
			model.addAttribute("type", "ALLSAVED");
			model.addAttribute("headersecval", "SELL LIST");
			return "selllistuser";
		}
	
	@GetMapping("/viewallrecycledsellrequser")
	public String showAllrecycledSellReq(Model model,HttpSession session) {
			List<Sell> productList = sellrepository.findByStatus("RECYCLED");
			model.addAttribute("msg", productList);  
			model.addAttribute("type", "ALLRECYCLED");
			model.addAttribute("headersecval", "RECYCLED LIST");
			return "selllistuser";
		}
	
	@GetMapping("/viewallrejectedsellrequser")
	public String showAllRejectedSellReq(Model model,HttpSession session) {
			List<Sell> productList = sellrepository.findByStatus("REJECTED");
			model.addAttribute("msg", productList);  
			model.addAttribute("type", "ALLREJECTED");
			model.addAttribute("headersecval", "REJECTED LIST");
			return "selllistuser";
		}
	
	@GetMapping("/viewallcollectedsellrequser")
	public String showAllcollectedSellReq(Model model,HttpSession session) {
			List<Sell> productList = sellrepository.findByStatus("COLLECTED");
			model.addAttribute("msg", productList);  
			model.addAttribute("type", "ALLCOLLECTED");
			model.addAttribute("headersecval", "COLLECTED LIST");
			return "selllistuser";
		}
	
	public Model getmodaldata(Model model,String header,String type,String status)
	{
		if(type.equalsIgnoreCase("ALLSAVED"))
		{
			header="SELL LIST";
			status="SAVED";
		}
		else if(type.equalsIgnoreCase("ALLRECYCLED"))
		{
			header="RECYCLED LIST";
			status="RECYCLED";
		}
		else if(type.equalsIgnoreCase("ALLREJECTED"))
		{
			header="REJECTED LIST";
			status="REJECTED";
		}
		else if(type.equalsIgnoreCase("ALLCOLLECTED"))
		{
			header="COLLECTED LIST";
			status="COLLECTED";
		}
		List<Sell> productList = sellrepository.findByStatus(status);
		model.addAttribute("msg", productList); 
		model.addAttribute("type", type);
		model.addAttribute("headersecval", header);
		return model;
	}
	
	@GetMapping("/updateselltocollected/{sellid}/{type}")
	public String updatedtoCollected(@PathVariable Long sellid, @PathVariable String type,
			Model model, HttpSession session) {

		if (!BeanUtils.isNullOrZero(sellid)) {
			Sell sell = sellrepository.findById(sellid).get();
			sell.setStatus("COLLECTED");
			sell.setAdminRemarks("COLLECTED BY DELEVER BOY");
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			String strDate= formatter.format(date); 
			sell.setCollectedtime(strDate);
			sellrepository.save(sell);

		}
		String header="";
		String status="";
		
		getmodaldata(model, header, type, status);
		
		return "selllistuser";
	}
	
	@GetMapping("/updateselltorejected/{sellid}/{type}")
	public String updatedtoRejected(@PathVariable Long sellid, @PathVariable String type,
			Model model, HttpSession session) {

		if (!BeanUtils.isNullOrZero(sellid)) {
			Sell sell = sellrepository.findById(sellid).get();
			sell.setStatus("REJECTED");
			sell.setAdminRemarks("ITEM REJECTED");
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			String strDate= formatter.format(date); 
			sell.setRejectedtime(strDate);
			sellrepository.save(sell);

		}
		String header="";
		String status="";
		
		getmodaldata(model, header, type, status);
		
		
		return "selllistuser";
	}
	
	@GetMapping("/updateselltorecycled/{sellid}/{type}")
	public String updatedtoREcycled(@PathVariable Long sellid, @PathVariable String type,
			Model model, HttpSession session) {

		if (!BeanUtils.isNullOrZero(sellid)) {
			Sell sell = sellrepository.findById(sellid).get();
			sell.setStatus("RECYCLED");
			sell.setAdminRemarks("ITEM ACCEPTED AND RECYCLED");
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			String strDate= formatter.format(date); 
			sell.setRecycletime(strDate);
			sellrepository.save(sell);
			service.processStockIn(createStockVoFromSell(sell));
		}
		
		String header="";
		String status="";
		
		getmodaldata(model, header, type, status);
		
		return "selllistuser";
	}
	
	public StockInsertVo createStockVoFromSell(Sell sell)
	{
		ProductMain prod =  prodrepository.findById(sell.getProductid()).get();
		StockInsertVo stockvo = new StockInsertVo();
		stockvo.setDocid(sell.getId());
		stockvo.setDocnum(sell.getDocnum());
		stockvo.setDoctypenum(100l);//sell
		stockvo.setStockinqty(sell.getQty());
		stockvo.setInserttype(1l);//stockin
		stockvo.setSellerprice(sell.getPrice());
		stockvo.getProductid().setId(sell.getProductid());
		stockvo.getUserid().setId(sell.getUserid());
		stockvo.setQty(sell.getQty());
		stockvo.setSellingprice(prod.getPrice());
		stockvo.setStockoutqty(0l);
		return stockvo;
	}

}
	
	
	
	

