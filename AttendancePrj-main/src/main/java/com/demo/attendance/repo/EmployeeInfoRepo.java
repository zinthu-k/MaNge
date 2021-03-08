package com.demo.attendance.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.attendance.entity.EmployeeInfo;

public interface EmployeeInfoRepo extends JpaRepository<EmployeeInfo, Integer> {
	Optional<EmployeeInfo> findOneByMail(String mail);
}
