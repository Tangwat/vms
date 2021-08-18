package com.semicolon.samvms.service;

import com.semicolon.samvms.domain.Vehicle;
import com.semicolon.samvms.dto.VehicleDto;
import com.semicolon.samvms.exceptions.AlreadyExistException;
import com.semicolon.samvms.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle createVehicle(VehicleDto vehicleDto) throws AlreadyExistException;
    List <Vehicle> getAllVehicle();
    Optional <Vehicle> findById(Long vehicleId);
    List <Vehicle> findByStatus(boolean status);
    Vehicle updateVehicle(Long vehicleId, Vehicle updatedVehicle)throws NotFoundException;
    void deleteVehicle(Long vehicleId) throws NotFoundException;
    Optional <Vehicle> findByVin(String vin);
}
