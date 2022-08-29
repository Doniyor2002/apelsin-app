package uz.pdp.springbootapelsinffull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.springbootapelsinffull.entity.Category;
import uz.pdp.springbootapelsinffull.repository.CategoryRepository;
import uz.pdp.springbootapelsinffull.service.CategoryServise;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryServise categoryServise;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public String getAllCategory(Model model){
        categoryServise.getAll(model);
        return "category/list";
    }

    @GetMapping("/add")
    public String AddCategorypage(){
        return "category/add";
    }
    @PostMapping("/add")
    public String AddCategory(@ModelAttribute Category category,Model model){
        categoryServise.getAdd(model,category);
        return "category/list";
    }
    @GetMapping("/edit/{id}")
    public String updateCategoryPage(@PathVariable Integer id, Model model){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not found!" + id));
        model.addAttribute("category",category);

        return "category/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Integer id,@ModelAttribute Category category,Model model){
        categoryServise.edit(id,model,category);
        return "category/list";
    }
    @GetMapping("/delete/{id}")
    public String deletecategory(@PathVariable Integer id,Model model){
        categoryRepository.deleteById(id);
        model.addAttribute("list",categoryRepository.findAll());
        return "category/list";

    }
}
