package Esport_Website.entity;

import java.util.Date;

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

@Entity
@Table(name = "Help_Detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelpDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer helpDetailId;

    @ManyToOne
    @JoinColumn(name = "help_id")
    private Help help;

    private Date helpTime;

    @Column(columnDefinition = "TEXT")
    private String detail;
}
