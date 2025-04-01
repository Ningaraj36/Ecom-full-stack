package com.mru.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_orders")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer orderId;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private Double totalAmount;
    private String paymentId;

    @Column(columnDefinition="TEXT")
    private String orderDetails;

    private Timestamp createdAt;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "CustomerOrder [orderId=" + orderId + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", address=" + address + ", totalAmount=" + totalAmount + ", paymentId=" + paymentId
				+ ", orderDetails=" + orderDetails + ", createdAt=" + createdAt + "]";
	}

	public CustomerOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerOrder(Integer orderId, String name, String email, String mobile, String address, Double totalAmount,
			String paymentId, String orderDetails, Timestamp createdAt) {
		super();
		this.orderId = orderId;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.totalAmount = totalAmount;
		this.paymentId = paymentId;
		this.orderDetails = orderDetails;
		this.createdAt = createdAt;
	}
    
    
}

