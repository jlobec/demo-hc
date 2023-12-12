package com.example.demo.infrastructure.repository.mysql.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {

    @Id
    private Integer id;

    @Column(name = "parent_link")
    private Integer parentLink;

    @Column(name = "parent_type")
    private String parentType;

    private String addr1;

    private String addr2;

    private String city;

    private String state;

    private String zip;

    private String status;
}
