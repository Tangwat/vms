//package com.semicolon.samvms.repository;
//
//import com.semicolon.samvms.domain.Vehicle;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//class VehicleRepositoryTest {
//
//    @Autowired
//    private VehicleRepository underTest;
//
//    @Test
//    void shouldCheckIfVehicleExistByVin() {
//        //given
//        Vehicle vehicle = new Vehicle("wty123", "Howon", "2013", 2390L, localDate.now, false);
//        underTest.save(vehicle);
//        log.info("vechicle object--> {}", vehicle);
//
//        //when
//        boolean expected = underTest.findByVin("wty123");
//
//        //then
//        assertThat(expected).isTrue();
//
//    }
//}