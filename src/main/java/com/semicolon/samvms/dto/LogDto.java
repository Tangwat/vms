package com.semicolon.samvms.dto;

import javax.validation.constraints.NotBlank;


public class LogDto {
    @NotBlank
    private Long vehicleId;

    @NotBlank
    private Long odReading;

    public LogDto(Long vehicleId, Long odReading) {
        this.vehicleId = vehicleId;
        this.odReading = odReading;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getOdReading() {
        return odReading;
    }

    public void setReading(Long odReading) {
        this.odReading = odReading;
    }
}


