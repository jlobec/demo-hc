package com.example.demo.infrastructure.repository.mysql.hco;

import com.example.demo.infrastructure.repository.mysql.address.AddressEntity;
import com.example.demo.infrastructure.repository.mysql.affiliation.AffiliationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Table(name = "hco")
@Data
public class HealthcareOrganizationEntity {

    @Id
    private Integer id;

    private String name;

    @OneToMany
    @JoinColumn(name = "parent_link", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "parent_type = 'HCO'")
    private Set<AddressEntity> addresses;

    @OneToMany
    @JoinColumn(name = "parent_link", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "type like 'HCO_%'")
    private Set<AffiliationEntity> affiliationsAsParent;

    @OneToMany
    @JoinColumn(name = "child_link", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "type like '%_HCO'")
    private Set<AffiliationEntity> affiliationsAsChild;

    private String status;
}
