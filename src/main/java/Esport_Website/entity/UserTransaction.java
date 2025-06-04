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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User_Transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private Integer total;
    private Date createdDate = new Date();
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package aPackage;
}
