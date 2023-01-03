package com.tim.user_service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity()
@Table(name = "USER")
@Getter @Setter
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;
}