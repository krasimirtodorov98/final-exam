package com.endava.exam.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "supermarkets", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Supermarket extends BaseEntity {

    @NotBlank
    @Length(max = 64)
    @Column
    private String name;

    @NotBlank
    @Length(max = 128)
    @Column(name = "address")
    private String address;

    @NotBlank
    @Length(max = 10)

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    @Column(name = "work_hours")
    private String workHours;

    @OneToMany(mappedBy = "supermarket", cascade = CascadeType.ALL)
    private List<Item> items;

}
