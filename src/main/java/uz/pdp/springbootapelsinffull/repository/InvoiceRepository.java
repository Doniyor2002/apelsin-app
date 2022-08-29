package uz.pdp.springbootapelsinffull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootapelsinffull.entity.Category;
import uz.pdp.springbootapelsinffull.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
