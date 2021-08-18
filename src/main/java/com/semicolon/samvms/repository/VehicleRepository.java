package com.semicolon.samvms.repository;

import com.semicolon.samvms.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByVin(String vin);
    List <Vehicle> findByStatus(boolean status);
    Optional<Vehicle> findById(Long aLong);
}
