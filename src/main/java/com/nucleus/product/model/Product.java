package com.nucleus.product.model;

import com.nucleus.chargepolicy.model.ChargePolicy;
import com.nucleus.eligibilitypolicy.model.EligibilityPolicy;
import com.nucleus.repaymentpolicy.model.RepaymentPolicy;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @NotBlank(message = "Product Code should not be empty")
    @Pattern(regexp = "^([A-Za-z0-9\\_]+)$", message = "Product code cannot contain spaces")
    @Id
    @Column(name = "product_code", length = 10)
    private String productCode;

    @NotBlank(message = "Product Name should not be empty")
    @Size(min = 3, message = "Product name should be at least 3 characters")
    @Column(name = "product_name", length = 30, nullable = false, unique = true)
    private String productName;

    @Column(name = "product_description", length = 200)
    private String productDescription;

    @NotBlank(message = "Product must have a type")
    @Column(name = "product_type", length = 30, nullable = false)
    private String productType;

    @Digits(integer = 10, fraction = 0, message = "Maximum exposure amount must be an integer")
    @Min(value = 0, message = "Maximum exposure amount must be at least 0")
    @Column(name = "max_exposure_amount")
    private Integer maxExposureAmount;

    @ManyToOne
    @JoinColumn(name = "repayment_policy_code", referencedColumnName = "POLICY_CODE", nullable = false)
    private RepaymentPolicy repaymentPolicyCode;

    @ManyToOne
    @JoinColumn(name = "eligibility_policy_code", referencedColumnName = "policy_code", nullable = false)
    private EligibilityPolicy eligibilityPolicyCode;

    @ManyToOne
    @JoinColumn(name = "charge_code_policy", referencedColumnName = "policy_code")
    private ChargePolicy chargeCodePolicy;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "created_by", length = 30)
    private String createdBy;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "modified_by", length = 30)
    private String modifiedBy;

    @Column(name = "authorized_date")
    private LocalDate authorizedDate;

    @Column(name = "authorized_by", length = 30)
    private String authorizedBy;

    @Column(name = "status", length = 30)
    private String status;

    @NotBlank(message = "Product must have an Eligibility Policy")
    private String eligibilityPolicyCodeString;

    @NotBlank(message = "Product must have a Repayment Policy")
    private String repaymentPolicyCodeString;

    private String chargeCodePolicyString;

    public Product(){
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getMaxExposureAmount() {
        return maxExposureAmount;
    }

    public void setMaxExposureAmount(Integer maxExposureAmount) {
        this.maxExposureAmount = maxExposureAmount;
    }

    public RepaymentPolicy getRepaymentPolicyCode() {
        return repaymentPolicyCode;
    }

    public void setRepaymentPolicyCode(RepaymentPolicy repaymentPolicyCode) {
        this.repaymentPolicyCode = repaymentPolicyCode;
    }

    public String getEligibilityPolicyCodeString() {
        return eligibilityPolicyCodeString;
    }

    public EligibilityPolicy getEligibilityPolicyCode() {
        return this.eligibilityPolicyCode;
    }

    public void setEligibilityPolicyCode(EligibilityPolicy eligibilityPolicyCode) {
        this.eligibilityPolicyCode = eligibilityPolicyCode;
    }

    public void setEligibilityPolicyCodeString(String eligibilityPolicyCode) {
        this.eligibilityPolicyCodeString = eligibilityPolicyCode;
    }

    public ChargePolicy getChargeCodePolicy() {
        return chargeCodePolicy;
    }

    public void setChargeCodePolicy(ChargePolicy chargeCodePolicy) {
        this.chargeCodePolicy = chargeCodePolicy;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDate getAuthorizedDate() {
        return authorizedDate;
    }

    public void setAuthorizedDate(LocalDate authorizedDate) {
        this.authorizedDate = authorizedDate;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRepaymentPolicyCodeString() {
        return repaymentPolicyCodeString;
    }

    public void setRepaymentPolicyCodeString(String repaymentPolicyCodeString) {
        this.repaymentPolicyCodeString = repaymentPolicyCodeString;
    }

    public String getChargeCodePolicyString() {
        return chargeCodePolicyString;
    }

    public void setChargeCodePolicyString(String chargeCodePolicyString) {
        this.chargeCodePolicyString = chargeCodePolicyString;
    }
}
