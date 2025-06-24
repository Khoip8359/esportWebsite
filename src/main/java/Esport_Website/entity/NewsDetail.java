package Esport_Website.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "News_Detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDetail {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newsDetailId;

    @ManyToOne
    @JoinColumn(name = "news_id")
    @ToString.Exclude
    @JsonBackReference 
    private News news;

    @Column(columnDefinition = "TEXT")
    private String detail;
    
    private int detailsNumber;
}

