package com.shop.in.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String name;

    private String fees;

    @ManyToMany(mappedBy = "courses" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Person> persons = new HashSet<>();
}
