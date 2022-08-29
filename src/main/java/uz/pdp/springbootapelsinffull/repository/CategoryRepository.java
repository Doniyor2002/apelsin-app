package uz.pdp.springbootapelsinffull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapelsinffull.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
