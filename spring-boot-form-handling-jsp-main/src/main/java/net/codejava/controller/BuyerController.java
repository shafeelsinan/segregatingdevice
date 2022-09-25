package net.codejava.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import net.codejava.BuyValidator;
import net.codejava.SellValidator;
import net.codejava.domain.Currenstock;
import net.codejava.domain.Sell;
import net.codejava.repository.CurrenstockRepository;
import net.codejava.repository.ProductRepository;
import net.codejava.repository.SellRepository;
import net.codejava.service.CurrentstockService;

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
	
	private ModelMapper mapper;

	public BuyerController(BuyValidator validator, ProductRepository prodrepository, SellRepository sellrepository,
			CurrentstockService service, CurrenstockRepository currentstkrepository, ModelMapper mapper) {
		this.validator = validator;
		this.prodrepository = prodrepository;
		this.sellrepository = sellrepository;
		this.service = service;
		this.currentstkrepository = currentstkrepository;
		this.mapper = mapper;
	}
	
	@GetMapping("/viewallcurrenstocklist")
	public String showAllCurrenstockReq(Model model,HttpSession session) {
			List<Currenstock> productList = currentstkrepository.findByCurrenstockqtyGreaterThan(0l);
			model.addAttribute("msg", productList);  
//			model.addAttribute("type", "ALLSAVED");
//			model.addAttribute("headersecval", "SELL LIST");
			return "buycartlist";
		}
	
	
	
	
}
