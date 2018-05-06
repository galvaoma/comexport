package com.bank.data.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="ACCOUNTY_ENTRY")
public class AccountEntryEntity implements Serializable {

    private static final long serialVersionUID = 4284567237862794696L;

    @Id
    @Column(name="ACCOUNT_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long accountId;

    @Column(name="ACCOUNT_NUMBER")
    private Long accountNumber;

    @Column(name="CREATE_DATE")
    private Date createDate;

    @Column(name="VALOR")
    private BigDecimal valor;

    public AccountEntryEntity() {

    }

    public AccountEntryEntity(Long id) {
        this.accountId = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntryEntity)) return false;
        AccountEntryEntity that = (AccountEntryEntity) o;
        return Objects.equals(getAccountNumber(), that.getAccountNumber()) &&
                Objects.equals(getCreateDate(), that.getCreateDate()) &&
                Objects.equals(getValor(), that.getValor());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAccountNumber(), getCreateDate(), getValor());
    }
}
