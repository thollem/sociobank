package com.artsgard.sociobank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author wdragstra
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "account_transfer") //catalog = "TRANSFER_DB")
public class AccountTransfer implements Serializable {

    @Id
    private Long accountId;

    @Id
    private Long accountTransferId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", updatable = false, insertable = false,
            referencedColumnName = "id")
    private Account account;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountTransferId", updatable = false, insertable = false,
            referencedColumnName = "id")
    private Account accountTransfer;
    
    @Column(nullable = false)
    private BigDecimal amount;
 
    @Column(nullable = false)
    private Timestamp transferDate;
  
    @Column(nullable = true)
    private String description;
}
