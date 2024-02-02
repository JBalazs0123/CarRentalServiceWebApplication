package com.udemx.carRentalService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "car_id")
    private int carId;

    @Column(name = "reservation_startdate")
    private String reservationStartdate;

    @Column(name = "reservation_enddate")
    private String reservationEnddate;

    @NotNull(message="Name field is required")
    @Size(min = 1, message = "Name field is required")
    @Column(name = "name")
    private String name;

    @NotNull(message="Email field is required")
    @Size(min = 1, message = "Email field is required")
    @Column(name = "email")
    private String email;

    @NotNull(message="Address field is required")
    @Size(min = 1, message = "Address field is required")
    @Column(name = "address")
    private String address;

    @NotNull(message="Phone Number is required")
    @Size(min = 1, message = "Phone Number  is required")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "total_amount")
    private int totalAmount;

    public Reservation() {
    }

    public Reservation(int carId, String reservationStartdate, String reservationEnddate, String name, String email, String address, String phoneNumber, int totalAmount) {
        this.carId = carId;
        this.reservationStartdate = reservationStartdate;
        this.reservationEnddate = reservationEnddate;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getReservationStartdate() {
        return reservationStartdate;
    }

    public void setReservationStartdate(String reservationStartdate) {
        this.reservationStartdate = reservationStartdate;
    }

    public String getReservationEnddate() {
        return reservationEnddate;
    }

    public void setReservationEnddate(String reservationEnddate) {
        this.reservationEnddate = reservationEnddate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", carId=" + carId +
                ", reservationStartdate='" + reservationStartdate + '\'' +
                ", reservationEnddate='" + reservationEnddate + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
