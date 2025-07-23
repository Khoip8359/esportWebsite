package Esport_Website.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User_Transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private Integer total;
    
    @Default
    private Date createdDate = new Date();
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package Package;
}
