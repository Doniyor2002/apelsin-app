package uz.pdp.springbootapelsinffull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapelsinffull.entity.Category;
import uz.pdp.springbootapelsinffull.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
