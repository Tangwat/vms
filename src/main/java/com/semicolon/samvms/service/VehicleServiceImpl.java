package com.semicolon.samvms.service;
import com.semicolon.samvms.domain.Vehicle;
import com.semicolon.samvms.dto.VehicleDto;
import com.semicolon.samvms.exceptions.AlreadyExistException;
import com.semicolon.samvms.exceptions.NotFoundException;
import com.semicolon.samvms.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository repository;

    @Override
    public Vehicle createVehicle(VehicleDto vehicleDto) throws AlreadyExistException {
        Optional <Vehicle> vehicle = repository.findByVin(vehicleDto.getVin());
        if (!vehicle.isPresent()){
            Vehicle vehicle1 = new Vehicle(vehicleDto.getVin(), vehicleDto.getManufacturer(), vehicleDto.getModel(), vehicleDto.getOdReading(), vehicleDto.getPurchaseDate(), vehicleDto.isStatus());
            return repository.save(vehicle1);
        }else {
            throw new AlreadyExistException("Vehicle Already Exist");
        }
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return repository.findAll();
    }
    @Override
    public Optional<Vehicle> findById(Long vehicleId) {
        return repository.findById(vehicleId);
    }

    @Override
    public List<Vehicle> findByStatus(boolean status) {
        return repository.findByStatus(status);
    }

    @Override
    public Vehicle updateVehicle(Long vehicleId, Vehicle updatedVehicle) throws NotFoundException {
        if (repository.existsById(vehicleId)) {
            Optional<Vehicle> vehicleModel = repository.findById(vehicleId);
            if (vehicleModel.isPresent()){
                Vehicle vehicle = vehicleModel.get();
                if (updatedVehicle.getVin() == null)
                    vehicle.setVin(vehicle.getVin());
                else vehicle.setVin(updatedVehicle.getVin());

                if (updatedVehicle.getManufacturer() == null)
                    vehicle.setManufacturer(vehicle.getManufacturer());
                else vehicle.setManufacturer(updatedVehicle.getManufacturer());

                if (updatedVehicle.getModel() == null)
                    vehicle.setModel(vehicle.getModel());
                else vehicle.setModel(updatedVehicle.getModel());

                if (updatedVehicle.getOdReading() == null)
                    vehicle.setOdReading(vehicle.getOdReading());
                else updatedVehicle.setOdReading(vehicle.getOdReading());

                if (updatedVehicle.getPurchaseDate() == null)
                    vehicle.setPurchaseDate(vehicle.getPurchaseDate());
                else updatedVehicle.setPurchaseDate(vehicle.getPurchaseDate());

                repository.save(vehicle);
            }
        }
        return updatedVehicle;
    }

    @Override
    public void deleteVehicle(Long vehicleId) throws NotFoundException {
        Optional<Vehicle> vehicle = repository.findById(vehicleId);
        if (!vehicle.isPresent()) {
            throw new NotFoundException("vehicle does not exist");
        } else {
            vehicle.get().setStatus(false);
            repository.save(vehicle.get());
        }
    }

    @Override
    public Optional <Vehicle> findByVin(String vin) {
        return repository.findByVin(vin);
    }
}
