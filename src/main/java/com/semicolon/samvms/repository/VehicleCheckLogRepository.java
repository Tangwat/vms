package com.semicolon.samvms.repository;

import com.semicolon.samvms.domain.VehicleCheckLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleCheckLogRepository extends JpaRepository<VehicleCheckLog, Long> {
    Optional <VehicleCheckLog> findById(Long logId);
}
