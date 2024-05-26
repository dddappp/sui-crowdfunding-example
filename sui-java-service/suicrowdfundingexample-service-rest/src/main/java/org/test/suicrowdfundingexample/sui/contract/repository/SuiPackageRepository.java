package org.test.suicrowdfundingexample.sui.contract.repository;

import org.test.suicrowdfundingexample.sui.contract.SuiPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuiPackageRepository extends JpaRepository<SuiPackage, String> {
    
}
