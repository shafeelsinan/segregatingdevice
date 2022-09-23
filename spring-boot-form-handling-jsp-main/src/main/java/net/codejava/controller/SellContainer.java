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
import net.codejava.vo.SellVo;

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
	
	private ModelMapper mapper;

	public SellContainer(SellValidator validator, ProductRepository prodrepository, ModelMapper mapper) {
		this.validator = validator;
		this.prodrepository = prodrepository;
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
		
		if (bindingResult.hasErrors()) {
		         return "sellform";
		     }
		HisUser hisuser = (HisUser) session.getAttribute("USER_DETAILS");
		ProductMain prod = prodrepository.findById(sellvo.getProductid()).get();
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
			return "selllistuser";
		}
	}
	
	
	
	
	
	
}
