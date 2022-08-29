package uz.pdp.springbootapelsinffull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.pdp.springbootapelsinffull.dto.OrderDTO;
import uz.pdp.springbootapelsinffull.entity.Detail;
import uz.pdp.springbootapelsinffull.entity.Invoice;
import uz.pdp.springbootapelsinffull.entity.Order;
import uz.pdp.springbootapelsinffull.entity.Payment;
import uz.pdp.springbootapelsinffull.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServise {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    PaymentRepository paymentRepository;
    public void addorders(OrderDTO orderDTO, Model model) {
        Order order=new Order();
        order.setCustomer(customerRepository.getById(orderDTO.getCustomerId()));
        order.setDate(new Date());
        Order save = orderRepository.save(order);

        Detail detail=new Detail();
        detail.setOrder(save);
        detail.setProduct(productRepository.getById(orderDTO.getProductId()));
        detail.setQuantity(orderDTO.getAmount());
        detailRepository.save(detail);

        Invoice invoice=new Invoice();
        invoice.setOrder(save);
        BigDecimal price = detail.getProduct().getPrice();
        Short quantity = detail.getQuantity();
        invoice.setAmount(price.multiply(BigDecimal.valueOf(quantity)));
        invoice.setDue(new Date());
        invoiceRepository.save(invoice);
        model.addAttribute("list",orderRepository.findAll());
    }

    public void deleteorder(Integer id) {
        List<Detail> all = detailRepository.findAll();
        Order order = orderRepository.getById(id);
        List<Detail> detail = order.getDetail();
        for (Detail detail1 : detail) {
            for (Detail detail2 : all) {
                if (detail1.getId()==detail2.getId()) {
                    detailRepository.deleteById(detail2.getId());
                    break;
                }
            }
        }
        List<Invoice> invoices = invoiceRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();
        if (invoices!=null) {
            for (Invoice invoice : invoices) {
                if (invoice.getOrder().getId()==id) {
                    int a=invoice.getId();
                    if (payments!=null) {
                        for (Payment payment : payments) {
                            if (payment.getInvoice().getId()==a) {
                                paymentRepository.deleteById(payment.getId());
                            }
                            break;
                        }
                    }
                    invoiceRepository.deleteById(a);
                    break;
                }
            }
        }
        orderRepository.deleteById(id);

    }
}
