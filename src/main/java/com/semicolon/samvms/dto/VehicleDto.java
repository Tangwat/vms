package com.semicolon.samvms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class VehicleDto {

    @NonNull
    private String vin;

    @NonNull
    private String manufacturer;

    @NonNull
    private String model;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(locale = "dd-MM-yyyy")
    private LocalDate purchaseDate;

    @NotBlank
    private boolean status = true;


    @NonNull
    private Long odReading;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
