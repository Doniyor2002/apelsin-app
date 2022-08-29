package uz.pdp.springbootapelsinffull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapelsinffull.entity.Customer;
import uz.pdp.springbootapelsinffull.repository.CustomerRepository;
import uz.pdp.springbootapelsinffull.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerControlleer {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepository;
    @GetMapping
    public String getCustomer(Model model){
        customerService.getAll(model);
        return "customer/list";
    }
    @GetMapping("/add")
    public String getCustomerPage(){
        return "customer/add";
    }
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer, Model model){
        customerService.addcustomer(customer,model);
        return "customer/list";
    }
    @GetMapping("/edit/{id}")
    public String updateCustomerPage(@PathVariable Integer id,Model model){
        Customer customer = customerRepository.getById(id);
        model.addAttribute("customer",customer);
        return "customer/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateCustomer(@PathVariable Integer id,@ModelAttribute Customer customer, Model model){
        customerService.updatecustomer(id,customer,model);
        return "customer/list";
    }
    @GetMapping("/delete/{id}")
    public String DeleteCustomer(@PathVariable Integer id,Model model){
        customerRepository.deleteById(id);
        model.addAttribute("list",customerRepository.findAll());
        return "customer/list";
    }


}
