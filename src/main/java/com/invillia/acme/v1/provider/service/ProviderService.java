package com.invillia.acme.v1.provider.service;

import com.invillia.acme.domain.provider.ProviderRequest;
import com.invillia.acme.entity.Provider;
import com.invillia.acme.exceptions.EntityNotFound;

public interface ProviderService {
    Provider save(ProviderRequest providerRequest);

    Provider update(Long providerId, ProviderRequest providerRequest) throws EntityNotFound;
}
