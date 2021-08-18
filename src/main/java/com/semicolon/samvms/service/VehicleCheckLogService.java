package com.semicolon.samvms.service;

import com.semicolon.samvms.domain.VehicleCheckLog;
import com.semicolon.samvms.dto.LogDto;
import com.semicolon.samvms.exceptions.AlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface VehicleCheckLogService {
    VehicleCheckLog createVehicleCheckLogs(LogDto logDto) throws AlreadyExistException;
    List <VehicleCheckLog> getAllVehicleCheckLogs();
    Optional <VehicleCheckLog> findById(Long logId);
}
