package uz.pdp.springbootapelsinffull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootapelsinffull.dto.ProductDto;
import uz.pdp.springbootapelsinffull.entity.Category;
import uz.pdp.springbootapelsinffull.entity.Product;
import uz.pdp.springbootapelsinffull.repository.CategoryRepository;
import uz.pdp.springbootapelsinffull.repository.ProductRepository;
import uz.pdp.springbootapelsinffull.service.OrderServise;
import uz.pdp.springbootapelsinffull.service.ProductsService;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductsService productsService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
     CategoryRepository categoryRepository;
    @GetMapping
    public String getProduct(Model model){
        productsService.getAll(model);
        return "product/list";
    }
    @GetMapping("/add")
    public String addProductPage(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categoryList",categories);
        return "product/add";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductDto productDto,Model model){
        productsService.addproduct(productDto,model);
        return "product/list";
    }

    @GetMapping("/edit/{id}")
    public String updateProductPage(@PathVariable Integer id,Model model){
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            model.addAttribute("product",product);
            List<Category> categories = categoryRepository.findAll();
            categories.remove(product.getCategory());
            categories.add(0,product.getCategory());
            model.addAttribute("categoryList",categories);
        }

        return "product/edit";
    }
    @PostMapping("/edit/{id}")
        public String updateProduct(@PathVariable Integer id,@ModelAttribute ProductDto productDto,Model model){

        productsService.updateproduct(id,productDto,model);
        return "product/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id,Model model){
        productRepository.deleteById(id);
        model.addAttribute("list",productRepository.findAll());
        return "product/list";
    }


}
