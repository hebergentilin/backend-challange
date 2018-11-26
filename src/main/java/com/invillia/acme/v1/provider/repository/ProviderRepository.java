package com.invillia.acme.v1.provider.repository;

import com.invillia.acme.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
