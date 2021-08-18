package com.semicolon.samvms.domain;

import com.semicolon.samvms.domain.audit.DateAudit;
import lombok.NonNull;

import javax.persistence.*;

@Entity
public class VehicleCheckLog extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private Long odReading;

    public VehicleCheckLog(){
    }

    public VehicleCheckLog(Vehicle vehicle, Long odReading) {
        this.vehicle = vehicle;
        this.odReading = odReading;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getOdReading() {
        return odReading;
    }

    public void setOdReading(Long odReading) {
        this.odReading = odReading;
    }
}
