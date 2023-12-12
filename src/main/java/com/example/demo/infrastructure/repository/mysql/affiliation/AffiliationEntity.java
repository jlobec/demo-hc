package com.example.demo.infrastructure.repository.mysql.affiliation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "affiliations")
@Data
public class AffiliationEntity {

    @Id
    private Integer id;

    @Column(name = "parent_link")
    private Integer parentLink;

    @Column(name = "child_link")
    private Integer childLink;

    @Column(name = "status")
    private String status;

    @Column(name = "type")
    private String type;
}
