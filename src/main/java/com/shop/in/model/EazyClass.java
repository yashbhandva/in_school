package com.shop.in.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@Table(name = "class")
@Entity
public class EazyClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int classId;


    @NotBlank(message = "name must be not blank!")
    private String name;
    private LocalDateTime createdAt;

    @PrePersist
    public void onTime(){
        this.createdAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "eazyClass" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Person> persons;
}
