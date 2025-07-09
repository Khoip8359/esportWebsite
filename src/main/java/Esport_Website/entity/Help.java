package Esport_Website.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Help")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Help {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer helpId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Users receiver;

    @OneToMany(mappedBy = "help", cascade = CascadeType.ALL)
    private List<HelpDetail> details;
    
    @Column(nullable = false)
    private String merge;
}
