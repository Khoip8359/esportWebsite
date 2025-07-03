package Esport_Website.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "News_Letter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsLetter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newsletterId;

    private String email;
}
