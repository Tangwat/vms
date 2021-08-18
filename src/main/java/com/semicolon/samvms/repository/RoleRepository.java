package com.semicolon.samvms.repository;

import com.semicolon.samvms.domain.Role;
import com.semicolon.samvms.domain.projectenum.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
