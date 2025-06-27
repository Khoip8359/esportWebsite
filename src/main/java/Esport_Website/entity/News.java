package Esport_Website.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "News")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newsId;

    private String title;
    private String status;
    private Integer views = 0;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private Users author;

    @Default
    private Date createdDate = new Date();

    private String thumbnail;
    private String subtitle;

    @Default
    private Integer paidPoint = 0;

    @Default
    private Integer remainingPoint = 0;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<NewsDetail> newsDetails;
}

