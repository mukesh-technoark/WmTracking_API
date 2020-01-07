/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "ma_invoice")
@NamedQueries({
    @NamedQuery(name = "MaInvoice.findAll", query = "SELECT m FROM MaInvoice m")})
public class MaInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "driverid")
    private Long driverid;
    @Size(max = 2147483647)
    @Column(name = "amount")
    private String amount;
    @Size(max = 2147483647)
    @Column(name = "comments")
    private String comments;
    @Column(name = "createdby")
    private Long createdby;
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "modifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "modifiedby")
    private Long modifiedby;
    @Size(max = 2147483647)
    @Column(name = "status")
    private String status;
    @Size(max = 2147483647)
    @Column(name = "fromaddress")
    private String fromaddress;
    @Size(max = 2147483647)
    @Column(name = "toaddress")
    private String toaddress;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MaDriver maDriver;
    @JoinColumn(name = "jobid", referencedColumnName = "id")
    @ManyToOne
    private MaJobs jobid;

    public MaInvoice() {
    }

    public MaInvoice(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverid() {
        return driverid;
    }

    public void setDriverid(Long driverid) {
        this.driverid = driverid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Long getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(Long modifiedby) {
        this.modifiedby = modifiedby;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFromaddress() {
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        this.fromaddress = fromaddress;
    }

    public String getToaddress() {
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        this.toaddress = toaddress;
    }

    public MaDriver getMaDriver() {
        return maDriver;
    }

    public void setMaDriver(MaDriver maDriver) {
        this.maDriver = maDriver;
    }

    public MaJobs getJobid() {
        return jobid;
    }

    public void setJobid(MaJobs jobid) {
        this.jobid = jobid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaInvoice)) {
            return false;
        }
        MaInvoice other = (MaInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wmtrucking.entity.MaInvoice[ id=" + id + " ]";
    }
    
}
