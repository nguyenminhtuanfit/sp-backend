package com.sp.app.repository.rsql;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sp.app.model.Appliance;


@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long>, JpaSpecificationExecutor<Appliance> {
	List<Appliance> findByStatusIn(Set<String> setStatus);
	List<Appliance> findBySerialNumberAndBrandAndModel(String serialNumber, String brand, String model);
}
