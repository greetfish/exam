package com.junle.exam.Repository;

import com.junle.exam.Entity.SystemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemInfoRepository extends JpaRepository<SystemInfo, Integer> {
    SystemInfo getByInfoKey(String infoKey);
}
