package com.semicolon.samvms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.semicolon.samvms.domain.audit.DateAudit;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Vehicle extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(unique = true)
    private String vin;

    private String manufacturer;

    private String model;

    private Long odReading;

    private LocalDate purchaseDate;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private List<VehicleCheckLog> vehicleCheckLogList = new ArrayList<>();

    @NotBlank
    private boolean status;

    public Vehicle(){
    }

    public Vehicle(String vin, String manufacturer, String model, Long odReading, LocalDate purchaseDate, boolean status) {
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.odReading = odReading;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private User user;


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getOdReading(){ return odReading;}

    public void setOdReading(Long odReading){ this.odReading = odReading;}

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
