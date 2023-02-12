package com.tim.user_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Getter @Setter
public abstract class BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Version
    private int version;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private OffsetDateTime lastModifiedDate;
}
