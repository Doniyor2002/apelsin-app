package uz.pdp.springbootapelsinffull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.pdp.springbootapelsinffull.dto.ProductDto;
import uz.pdp.springbootapelsinffull.entity.Category;
import uz.pdp.springbootapelsinffull.entity.Product;
import uz.pdp.springbootapelsinffull.repository.CategoryRepository;
import uz.pdp.springbootapelsinffull.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    public void getAll(Model model) {
        List<Product> list = productRepository.findAll();
        model.addAttribute("list",list);
    }

    public void addproduct(ProductDto productDto, Model model) {
        Product product=new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        Optional<Category> byId = categoryRepository.findById(productDto.getCatId());
        if (byId.isPresent()){
            Category category = byId.get();
            product.setCategory(category);
        }
        productRepository.save(product);
        model.addAttribute("list",productRepository.findAll());
    }
    public void updateproduct(Integer id, ProductDto productDto, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
            Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCatId());
            if (categoryOptional.isPresent()) {
                Category category = categoryOptional.get();
                product.setCategory(category);
            }
            productRepository.save(product);
        }
        model.addAttribute("list",productRepository.findAll());
    }
}
