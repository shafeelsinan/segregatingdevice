package net.codejava;

import java.util.Arrays;
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

import net.codejava.domain.Product;
import net.codejava.domain.ProductMain;
import net.codejava.repository.ProductRepository;

@Controller
public class ProductController {
	
	
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
	
	
	public ProductController(ProductValidator validator, ProductRepository prodrepository, ModelMapper mapper) {
		this.validator = validator;
		this.prodrepository = prodrepository;
		this.mapper = mapper;
	}

	@GetMapping("/saveproduct/{prodid}")
	public String showProductForm(@PathVariable Long prodid,Model m){ 
		
		ProductMain prod = new ProductMain();
		if(!BeanUtils.isNullOrZero(prodid))
		{
			prod = prodrepository.findById(prodid).get();
		}
		Product productdao = mapper.map(prod, Product.class);
	    m.addAttribute("product", productdao);
		
		return "productform";
	}
	
	@PostMapping("/saveproduct")
	public String submitProductForm(@ModelAttribute("product") Product product,BindingResult bindingResult,HttpSession session) {
		System.out.println(product);
		session.getAttribute("USER_DETAILS");
		if(BeanUtils.isNullOrEmpty(product.getName()))
		{
			ObjectError error = new ObjectError("globalError", "Name Is Required");
			bindingResult.addError(error);
		}
		if(BeanUtils.isNullOrEmpty(product.getCode()))
		{
			ObjectError error = new ObjectError("globalError", "Code Is Required");
			bindingResult.addError(error);
		}
		
		
		if (bindingResult.hasErrors()) {
		         return "productform";
		     }
		ProductMain productmain = new ProductMain();
		productmain = mapper.map(product, ProductMain.class);
		prodrepository.save(productmain);
		return "productlist";
			
	}

}
