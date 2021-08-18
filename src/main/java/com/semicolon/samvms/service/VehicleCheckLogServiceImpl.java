package com.semicolon.samvms.service;

import com.semicolon.samvms.domain.Vehicle;
import com.semicolon.samvms.domain.VehicleCheckLog;
import com.semicolon.samvms.dto.LogDto;
import com.semicolon.samvms.exceptions.AlreadyExistException;
import com.semicolon.samvms.repository.VehicleCheckLogRepository;
import com.semicolon.samvms.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleCheckLogServiceImpl implements VehicleCheckLogService {

    VehicleCheckLogRepository vehicleCheckLogRepository;

    VehicleRepository vehicleRepository;

    @Autowired
    public VehicleCheckLogServiceImpl(VehicleCheckLogRepository vehicleCheckLogRepository, VehicleRepository vehicleRepository) {
        this.vehicleCheckLogRepository = vehicleCheckLogRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleCheckLog createVehicleCheckLogs(LogDto logDto) throws AlreadyExistException {
        Optional<Vehicle> vehicle1 = vehicleRepository.findById(logDto.getVehicleId());
        if (vehicle1.isPresent()) {
            VehicleCheckLog newVehicleLog = new VehicleCheckLog(vehicle1.get(), logDto.getOdReading());
            VehicleCheckLog vehicleCheckLog1 = vehicleCheckLogRepository.save(newVehicleLog);
            return vehicleCheckLogRepository.save(vehicleCheckLog1);
        }else {
            throw new AlreadyExistException("Vehicle Already Exist");
        }
    }

    @Override
    public List<VehicleCheckLog> getAllVehicleCheckLogs() {
        return vehicleCheckLogRepository.findAll();
    }

    @Override
    public Optional<VehicleCheckLog> findById(Long logId) {
        return vehicleCheckLogRepository.findById(logId);
    }
}
