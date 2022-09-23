package net.codejava.controller;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import net.codejava.ProductValidator;
import net.codejava.domain.Product;
import net.codejava.domain.ProductMain;
import net.codejava.repository.ProductRepository;

@Controller
public class ProductViewController {

	@Autowired
	@Qualifier("productValidator")
	private ProductValidator validator;

	   @InitBinder
	   private void initBinder(WebDataBinder binder) {
	      binder.setValidator(validator);
	   }
	   
	@Autowired
	private ProductRepository prodrepository;
	
	private ModelMapper mapper;

	public ProductViewController(ProductValidator validator, ProductRepository prodrepository, ModelMapper mapper) {
		super();
		this.validator = validator;
		this.prodrepository = prodrepository;
		this.mapper = mapper;
	}
	
	@GetMapping("/viewproduct")
	public String showProductForm(Model model) {
		
		List<ProductMain> productList = prodrepository.findAll();
		model.addAttribute("msg", productList);  
		return "productlist";
	}
	
	@GetMapping(value="/viewprod/{prodid}")    
    public String edit(@PathVariable Long prodid,Model m){  
		ProductMain prod = prodrepository.findById(prodid).get();
		Product productdao = mapper.map(prod, Product.class);
        m.addAttribute("product", productdao);
        return "productform";    
    }    
	
	
}
