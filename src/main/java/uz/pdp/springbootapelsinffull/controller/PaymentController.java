package uz.pdp.springbootapelsinffull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapelsinffull.dto.PaymentDto;
import uz.pdp.springbootapelsinffull.repository.InvoiceRepository;
import uz.pdp.springbootapelsinffull.repository.PaymentRepository;
import uz.pdp.springbootapelsinffull.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PaymentService paymentService;
    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("list",paymentRepository.findAll());
        return "payment/list";
    }
    @GetMapping("/add")
    public String addPaymentPage(Model model){
        model.addAttribute("invoiceList",invoiceRepository.findAll());
        return "payment/add";
    }
    @PostMapping("/add")
    public String addPayment(@ModelAttribute PaymentDto paymentDto,Model model){
        paymentService.add(paymentDto,model);
        return "payment/list";
    }
    @GetMapping("/edit/{id}")
    public String updatePaymentPage(@PathVariable Integer id,Model model){
        paymentService.update(id,model);
        return "payment/edit";
    }
    @PostMapping("/edit/{id}")
    public String updatePayment(@PathVariable Integer id,@ModelAttribute PaymentDto paymentDto, Model model){
        paymentService.updatepayment(id,paymentDto,model);
        return "payment/list";
    }
    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable Integer id,Model model){
        paymentRepository.deleteById(id);
        model.addAttribute("list",paymentRepository.findAll());
        return "payment/list";
    }
}
