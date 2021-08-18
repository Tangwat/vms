package com.semicolon.samvms.controllers;

import com.semicolon.samvms.domain.Vehicle;
import com.semicolon.samvms.domain.VehicleCheckLog;
import com.semicolon.samvms.dto.LogDto;
import com.semicolon.samvms.dto.VehicleDto;
import com.semicolon.samvms.exceptions.AlreadyExistException;
import com.semicolon.samvms.exceptions.NotFoundException;
import com.semicolon.samvms.service.VehicleCheckLogService;
import com.semicolon.samvms.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class VehicleController {

    private VehicleService vehicleService;

    private VehicleCheckLogService vehicleCheckLogService;

    @Autowired
    public VehicleController(VehicleService vehicleService,
                             VehicleCheckLogService vehicleCheckLogService){
        this.vehicleService = vehicleService;
        this.vehicleCheckLogService = vehicleCheckLogService;
    }
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    //create vehicle
    @PostMapping("/vehicles")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle1;
        try {
            vehicle1 = vehicleService.createVehicle(vehicleDto);
        }catch (AlreadyExistException existException){
            return new ResponseEntity<>(existException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new  ResponseEntity<>(vehicle1, HttpStatus.CREATED);
    }

    // view vehicles
    @GetMapping("/vehicles")
    public ResponseEntity<?>getVehicles(@RequestParam(required = false) String vin) throws AlreadyExistException{
        if (vin != null && !vin.isEmpty()) {
            Optional <Vehicle> vehicles = vehicleService.findByVin(vin);
            return ResponseEntity.ok(vehicles);
        }else {
            List <Vehicle> vehicles = vehicleService.getAllVehicle();
            return ResponseEntity.ok(vehicles);
        }
    }

    // view vehicle by id
    @GetMapping("/vehicles/{vehicle-id}")
    public ResponseEntity<?>getVehicleById(@Valid @PathVariable("vehicle-id") Long vehicleId){
        Optional<Vehicle> vehicles = vehicleService.findById(vehicleId);
        return ResponseEntity.ok(vehicles);
    }

    // view vehicle by status
    @GetMapping("/vehicles/status")
    public ResponseEntity<?> getVehicleByStatus(@RequestParam String vehicleStatus){
        List <Vehicle> vehicles = vehicleService.findByStatus(vehicleStatus.toLowerCase().equals("true"));
        return ResponseEntity.ok(vehicles);
    }

    // update vehicle record
    @PatchMapping("/vehicles/{vehicle-id}")
    public Vehicle updateVehicle(@Valid @PathVariable("vehicle-id") Long vehicleId, @RequestBody Vehicle updatedVehicle) throws NotFoundException {
        return vehicleService.updateVehicle(vehicleId,updatedVehicle);
    }

    //Disable vehicles from record by id
    @DeleteMapping("/vehicles/{vehicle-id}")
    public String deleteVehicles(@Valid @PathVariable("vehicle-id") Long vehicleId) {
        try {
            vehicleService.deleteVehicle(vehicleId);
            return "Vehicle disabled - " + vehicleId;
        } catch (NotFoundException ex) {
            return ex.getMessage();
        }
    }

    // create vehicle check log
    @PostMapping("/vehicles/logs")
    public ResponseEntity<?> createVehicleCheckLogs(@Valid @RequestBody LogDto logDto){
        log.info("the vehicle check loq is -->{}", logDto);
        VehicleCheckLog vehicleCheckLog;
        try {
            vehicleCheckLog = vehicleCheckLogService.createVehicleCheckLogs(logDto);
        } catch (AlreadyExistException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(vehicleCheckLog, HttpStatus.CREATED);
    }

    // view vehicle records
    @GetMapping("/vehicles/logs")
    public ResponseEntity<?> getVehicleCheckLogs() {
        List<VehicleCheckLog> vehicleCheckLogs = vehicleCheckLogService.getAllVehicleCheckLogs();
        return ResponseEntity.ok(vehicleCheckLogs);
    }

    // view vehicle record by Id
    @GetMapping("/vehicles/logs/{id}")
    public ResponseEntity<?> getVehicleCheckLogsById(@PathVariable("id") Long logId) {
        Optional<VehicleCheckLog> vehicleCheckLogs = vehicleCheckLogService.findById(logId);
        return ResponseEntity.ok(vehicleCheckLogs);
    }

}
