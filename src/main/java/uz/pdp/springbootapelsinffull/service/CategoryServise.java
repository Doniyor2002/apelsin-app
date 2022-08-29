package uz.pdp.springbootapelsinffull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.springbootapelsinffull.entity.Category;
import uz.pdp.springbootapelsinffull.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServise {

    @Autowired
    CategoryRepository categoryRepository;

    public void getAll(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("list",categories);
    }
    public void getAdd(Model model,Category category){
        categoryRepository.save(category);
        model.addAttribute("list",categoryRepository.findAll());
    }

    public void edit(Integer id,Model model, Category category) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()){
            Category category1 = optional.get();
            category1.setName(category.getName());
            categoryRepository.save(category1);
        }
        model.addAttribute("list",categoryRepository.findAll());
    }
}
