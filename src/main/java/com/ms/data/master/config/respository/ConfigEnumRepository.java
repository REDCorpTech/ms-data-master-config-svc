package com.ms.data.master.config.respository;

import com.ms.data.master.config.model.ConfigEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConfigEnumRepository extends JpaRepository<ConfigEnum, UUID>, JpaSpecificationExecutor<ConfigEnum> {
    List<ConfigEnum> findByType(String type);
}
