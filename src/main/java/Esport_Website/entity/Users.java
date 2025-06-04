package Esport_Website.entity;

import java.util.Date;

import jakarta.persistence.Column;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Users")
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    // gender kiểu bit, ánh xạ với Boolean
    private Boolean gender;

    private Date birthday;
    private String phone;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer remainingPoint = 0;
}
