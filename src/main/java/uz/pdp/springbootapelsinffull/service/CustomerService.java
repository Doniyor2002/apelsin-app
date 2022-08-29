package uz.pdp.springbootapelsinffull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.pdp.springbootapelsinffull.entity.Customer;
import uz.pdp.springbootapelsinffull.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public void getAll(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("list",customers);
    }

    public void addcustomer(Customer customer, Model model) {
    customerRepository.save(customer);
    model.addAttribute("list",customerRepository.findAll());
    }

    public void updatecustomer(Integer id, Customer customer, Model model) {
        Optional<Customer> customer1 = customerRepository.findById(id);
        if (customer1.isPresent()) {
            Customer customer2 = customer1.get();
            customer2.setName(customer.getName());
            customer2.setCountry(customer.getCountry());
            customer2.setAddress(customer.getAddress());
            customer2.setPhone(customer.getPhone());
            customerRepository.save(customer2);
        }
        model.addAttribute("list",customerRepository.findAll());
    }
}
