package uz.pdp.springbootapelsinffull.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @CreatedDate
    @Column(nullable = false)
    private Date date;


    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @OneToOne(mappedBy = "order")
    private Invoice invoice;


    @OneToMany(mappedBy = "order")
    private List<Detail> detail;
}
