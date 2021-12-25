package com.bank.CUSTACCTRACKER.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.CUSTACCTRACKER.bean.Customer;
import com.bank.CUSTACCTRACKER.bean.Transactions;
import com.bank.CUSTACCTRACKER.service.CustomerService;
import com.bank.CUSTACCTRACKER.service.TransactionService;

@RequestMapping("/bank")
@Controller
public class BankController {
	@Autowired
	CustomerService ser;
	
	@Autowired
	TransactionService ser2;
	
	@GetMapping("/")
	public ModelAndView getHomePage() {
		return new ModelAndView("bankhome");
	}

	@RequestMapping("/customerregister")
	public ModelAndView getRegisterPage() {
		Customer cust=new Customer();
		return new ModelAndView("registerpage","cust",cust);
	}
	
	@PostMapping("/saveForm") 
	public String saveForm(@ModelAttribute("cust") Customer cust, Model m) {
		  ser.save(cust);
		  m.addAttribute("msg", "Inserted/Updated Successfully");
		  m.addAttribute("list", ser.findAll());
		  return "menupage";
		}
	
	@GetMapping("/deleteForm")
	public ModelAndView deleteCust(@ModelAttribute("cust") Customer c)
	{
		return new ModelAndView("deletedata","cust",c);
	}
	
    @PostMapping("/delete")
    public String deleteById(@ModelAttribute("cust")Customer c, Model m) {
    	  String s1="success";
    	  if(s1.equals(ser.deleteById(c.getCustId())))  
    		  m.addAttribute("msg", "Deleted Successfully");
    	  else
    		  m.addAttribute("msg2", "Invalid Id");  
		  m.addAttribute("list", ser.findAll());
		  return "menupage";
    }
    
	@GetMapping("/makeTransac")
	public ModelAndView makeTransac(@ModelAttribute("cust") Customer c)
	{
		Transactions t=new Transactions();
		ModelAndView mv=new ModelAndView("maketransaction","trans",t);
		mv.addObject("cust",c);
		return mv;
	}
    
    @RequestMapping("/maketrans")
    public String makeTransaction(@ModelAttribute("trans")Transactions t,@ModelAttribute("cust") Customer c, Model m) {
    	String s="";
    	if(ser.findById(t.getFromCustId())!=null && ser.findById(t.getToCustId())!=null) 
    	{
    		if(t.getFromCustId()!=t.getToCustId())
    		{
    			s=ser2.sendMoney(t.getFromCustId(), t.getToCustId(), t.getAmount());
            	if(s.equals("success"))
            		m.addAttribute("msg","Money Sent successfully");
            	else
            		m.addAttribute("msg2","Invalid data");
    		}
    	}
    	else
    	m.addAttribute("msg2","Invalid Id's entered");
		m.addAttribute("list", ser.findAll());
    	return "menupage";
    }

    @GetMapping("/updateForm")
    public ModelAndView updateCust(@ModelAttribute("cust") Customer c)
    {
    	return new ModelAndView("updatepage","cust",c);
    }
    
    @PostMapping("/update")
    public String updatebyId(@ModelAttribute("cust") Customer c,Model m)
    {
    	if(ser.findById(c.getCustId()) != null)
    	{
    		m.addAttribute("cust",ser.findById(c.getCustId()));
    		//m.addAttribute("list",ser.findAll());
    		return "updatedetails";
    	}
    	else
    	{
    		m.addAttribute("msg2","Invalid Cust Id");
    		m.addAttribute("list", ser.findAll());
    	    return "menupage";
    	}
    }
    
    @PostMapping("/updateCustData")
    public String updateDetails(@ModelAttribute("cust") Customer c,Model m)
    {
    	ser.update(c);
    	m.addAttribute("list",ser.findAll());
    	return "menupage";
    }
	
    @GetMapping("/retrieveForm")
	public ModelAndView retrieveCust(@ModelAttribute("cust") Customer c)
	{
		return new ModelAndView("retrievedata","cust",c);
	}
    @PostMapping("/retrieve")
    public String retrieveById(@ModelAttribute("cust")Customer c, Model m) {
    	  Customer cust=ser.findById(c.getCustId());
          m.addAttribute("cust", cust);
		  return "retrievedData";
    }
}
