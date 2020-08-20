package com.artsgard.sociobank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "account") // catalog = "TRANSFER_DB")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false, unique = true)
    private String iban;
    
    @Column(nullable = false)
    private BigDecimal balance;
    
    @Column(nullable = false)
    private String currency;
    
    @Column(nullable = false)
    private Timestamp creationDate;
 
    @Column(nullable = false)
    private Boolean active;
    
    @JsonIgnore
    @OneToMany(targetEntity=AccountTransfer.class, mappedBy="account")
    private List<AccountTransfer> transfers;
    
    @JsonIgnore
    @OneToMany(targetEntity=AccountTransfer.class, mappedBy="account")
    private List<AccountTransfer> accountTransfers;
}
