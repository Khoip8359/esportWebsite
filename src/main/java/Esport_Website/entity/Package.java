package Esport_Website.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Package")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageId;

    @Column(columnDefinition = "TEXT")
    private String packageItems;

    private Integer price;
}
