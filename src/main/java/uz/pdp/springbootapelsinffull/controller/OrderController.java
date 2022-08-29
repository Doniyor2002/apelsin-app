package uz.pdp.springbootapelsinffull.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapelsinffull.dto.OrderDTO;
import uz.pdp.springbootapelsinffull.repository.CustomerRepository;
import uz.pdp.springbootapelsinffull.repository.DetailRepository;
import uz.pdp.springbootapelsinffull.repository.OrderRepository;
import uz.pdp.springbootapelsinffull.repository.ProductRepository;
import uz.pdp.springbootapelsinffull.service.OrderServise;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    OrderServise orderServise;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final DetailRepository detailRepository;


    @GetMapping
    public String getOrder(Model model){
       model.addAttribute("list",orderRepository.findAll());
        return "order/list";
    }
    @GetMapping("/add")
    public String addOrderPage(Model model){
        model.addAttribute("customerList",customerRepository.findAll());
        model.addAttribute("productList",productRepository.findAll());
        return "order/add";
    }
    @PostMapping("/add")
    public String addOrder(@ModelAttribute OrderDTO orderDTO,Model model){
        orderServise.addorders(orderDTO,model);
        return "redirect:";
    }
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id, Model model){
        System.out.println("id");
        orderServise.deleteorder(id);
        model.addAttribute("list",orderRepository.findAll());
        return "order/list";
    }

}
