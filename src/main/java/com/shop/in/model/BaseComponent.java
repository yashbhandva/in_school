package com.shop.in.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass

public class BaseComponent {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String CreatedBy;
}
