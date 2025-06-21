package com.shop.in.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name must be not blank!")
    private String name;

    @Pattern(
            regexp = "^(\\+91[\\-\\s]?)?[6-9]\\d{9}$",
            message = "mobile number must be 10 digits"
    )
    private String mobileNum;

    @Email(message = "Enter valid email structure")
    @NotBlank(message = "mobile number must be not blank!")
    private String email;

    @NotBlank(message = "mobile number must be not blank!")
    @Size(min = 5)
    private String subject;

    private String message;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @Column(updatable = false)
    private String CreatedBy;

//    @PrePersist
//    public void onCreate(){
//        this.createdAt = LocalDateTime.now();
//    }
}
