package uz.pdp.springbootapelsinffull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.pdp.springbootapelsinffull.dto.PaymentDto;
import uz.pdp.springbootapelsinffull.entity.Invoice;
import uz.pdp.springbootapelsinffull.entity.Payment;
import uz.pdp.springbootapelsinffull.repository.InvoiceRepository;
import uz.pdp.springbootapelsinffull.repository.PaymentRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    PaymentRepository paymentRepository;
    public void add(PaymentDto paymentDto, Model model) {
        Payment payment=new Payment();
        payment.setAmount(paymentDto.getAmount());
//        Timestamp timestamp=Timestamp.from(Instant.now());
//        payment.setTime(timestamp);
        Optional<Invoice> invoice = invoiceRepository.findById(paymentDto.getInvoiceId());
        if (invoice.isPresent()) {
            Invoice invoice1 = invoice.get();
            payment.setInvoice(invoice1);
        }
        paymentRepository.save(payment);
        model.addAttribute("list",paymentRepository.findAll());
    }

    public void update(Integer id, Model model) {
        Payment payment = paymentRepository.getById(id);
        model.addAttribute("payment",payment);
        Invoice invoice = payment.getInvoice();

        List<Invoice> list = invoiceRepository.findAll();
        list.remove(invoice);
        list.add(0,invoice);
        model.addAttribute("invoiceList",list);
    }

    public void updatepayment(Integer id,PaymentDto paymentDto, Model model) {
        Payment payment = paymentRepository.getById(id);
        payment.setAmount(paymentDto.getAmount());
        Invoice invoice = invoiceRepository.getById(paymentDto.getInvoiceId());
        payment.setInvoice(invoice);
        paymentRepository.save(payment);
        model.addAttribute("list",paymentRepository.findAll());
    }
}
