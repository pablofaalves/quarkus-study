package br.com.pfaa.quarkusstudy.respository.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class ProductPanacheEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Max(value = 50, message = "Name is too long")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal price;

    @CreationTimestamp
    private Date creationTimestamp;

    @UpdateTimestamp
    private Date updateTimestamp;
}

