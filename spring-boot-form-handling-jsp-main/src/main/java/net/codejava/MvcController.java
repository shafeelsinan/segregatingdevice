package net.codejava;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.codejava.domain.Product;
import net.codejava.repository.UserRepository;

@Controller
public class MvcController {
	
	@Autowired
	@Qualifier("signUpValidator")
	private SignUpValidator validator;
	
	@Autowired
	private UserRepository userrepository;

	   @InitBinder
	   private void initBinder(WebDataBinder binder) {
	      binder.setValidator(validator);
	   }
	
	@RequestMapping("/")
	public String home() {
		System.out.println("Going home...");
		return "index";
	}
	
	@GetMapping("/register")
	public String showForm(Model model,HttpServletRequest request) {
		request.getSession().invalidate();
		User user = new User();
		model.addAttribute("user", user);
		
		List<String> professionList = Arrays.asList("Developer", "Designer", "Tester", "Architect");
		model.addAttribute("professionList", professionList);
		
		return "register_form";
	}
	
	@GetMapping("/signup")
	public String signUp(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		List<String> professionList = Arrays.asList("Developer", "Designer", "Tester", "Architect");
		model.addAttribute("professionList", professionList);
		
		return "signup_form";
	}
	
	public static boolean isValidMobileNo(String str)  
	{  
		//(0/91): number starts with (0/91)  
		//[7-9]: starting of the number may contain a digit between 0 to 9  
		//[0-9]: then contains digits 0 to 9  
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");  
		//the matcher() method creates a matcher that will match the given input against this pattern  
		Matcher match = ptrn.matcher(str);  
		//returns a boolean value  
		return (match.find() && match.group().equals(str));  
	}  
	
	@PostMapping("/signup")
	public String signUpForm(@ModelAttribute("user") @Validated User user,BindingResult bindingResult) throws Exception {
		System.out.println(user);
		if(BeanUtils.isNullOrEmpty(user.getName()))
		{
			ObjectError error = new ObjectError("globalError", "Username Is Required");
			bindingResult.addError(error);
		}
		if(BeanUtils.isNullOrEmpty(user.getEmail()))
		{
			ObjectError error = new ObjectError("globalError", "Email Is Required");
			bindingResult.addError(error);
		}
		if(BeanUtils.isNullOrEmpty(user.getPassword()))
		{
			ObjectError error = new ObjectError("globalError", "Password Is Required");
			bindingResult.addError(error);
		}
		if(BeanUtils.isNullOrEmpty(user.getPhone()))
		{
			ObjectError error = new ObjectError("globalError", "Phone No Is Required");
			bindingResult.addError(error);
		}
		if(!isValidMobileNo(user.getPhone()))
		{
			ObjectError error = new ObjectError("globalError", "Invalid Phone Number");
			bindingResult.addError(error);
		}
		if(BeanUtils.isNullOrEmpty(user.getUsertype()))
		{
			ObjectError error = new ObjectError("globalError", "Usertype Is Required");
			bindingResult.addError(error);
		}
		if(userrepository.findByUsernameOrEmail(user.getName(), user.getPassword()).isPresent())
		{
			ObjectError error = new ObjectError("globalError", "Username Or Email Already Exist");
			bindingResult.addError(error);
		}
			if (bindingResult.hasErrors()) {
		         return "signup_form";
		     }
			HisUser huser = new HisUser();
			huser.setEmail(user.getEmail());
			huser.setName(user.getName());
			huser.setUsername(user.getName());
			huser.setPassword(user.getPassword());
			huser.setUsertype(user.getUsertype());
			huser.setAddress(user.getAddress());
			huser.setPhone(user.getPhone());
			userrepository.save(huser);
		return "register_form";
	}
	
	@PostMapping("/register")
	public String submitForm(@ModelAttribute("user") User user,BindingResult bindingResult,HttpServletRequest request) {
		System.out.println(user);
		if(BeanUtils.isNullOrEmpty(user.getName()))
		{
			ObjectError error = new ObjectError("globalError", "Username Is Required");
			bindingResult.addError(error);
		}
		if(BeanUtils.isNullOrEmpty(user.getPassword()))
		{
			ObjectError error = new ObjectError("globalError", "Password Is Required");
			bindingResult.addError(error);
		}
		
		if(!userrepository.existsByUsernameAndPassword(user.getName(),user.getPassword()))
		{
			ObjectError error = new ObjectError("globalError", "Invalid Login");
			bindingResult.addError(error);
		}
			if (bindingResult.hasErrors()) {
		         return "register_form";
		     }
			HisUser hisuser = userrepository.findByUsername(user.getName()).get();
			request.getSession().setAttribute("USER_DETAILS", hisuser);
			if(hisuser.getUsertype().equalsIgnoreCase("BUYER"))
			{
				return "buyerhome";
			}
			else if(hisuser.getUsertype().equalsIgnoreCase("SELLER"))
			{
				return "sellerhome";
			}
			else
			{
				return "adminhome";
			}
	}
	
	@GetMapping("/adminhomepage")
	public String adminhomepage(HttpSession session) {
		if(session.getAttribute("USER_DETAILS") != null)
		{
			HisUser hisuser = (HisUser) session.getAttribute("USER_DETAILS");
			if(hisuser.getUsertype().equalsIgnoreCase("BUYER"))
			{
				return "buyerhome";
			}
			else if(hisuser.getUsertype().equalsIgnoreCase("SELLER"))
			{
				return "sellerhome";
			}
			else
			{
				return "adminhome";
			}
		}
		else
		{
			return "register_form";
		}
//		return "adminhome";
	}
	
	
}
