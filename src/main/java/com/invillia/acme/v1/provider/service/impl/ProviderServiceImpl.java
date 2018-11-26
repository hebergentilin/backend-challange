package com.invillia.acme.v1.provider.service.impl;

import com.invillia.acme.domain.provider.ProviderRequest;
import com.invillia.acme.entity.Provider;
import com.invillia.acme.exceptions.EntityNotFound;
import com.invillia.acme.log.MessageLog;
import com.invillia.acme.v1.provider.mapper.ProviderMapper;
import com.invillia.acme.v1.provider.repository.ProviderRepository;
import com.invillia.acme.v1.provider.service.ProviderService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService, Serializable {

    @Autowired
    @Setter
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper providerMapper;


    @Override
    public Provider save(ProviderRequest providerRequest) {
        log.info(MessageLog.BC0004.text());
        return providerRepository.save(providerMapper.requestToEntity(providerRequest));
    }

    @Override
    public Provider update(Long providerId, ProviderRequest providerRequest) throws EntityNotFound {
        if (!providerRepository.existsById(providerId)) {
            throw new EntityNotFound(MessageLog.BC0005.text());
        }

        return providerRepository.save(providerMapper.requestToEntity(providerRequest));
    }
}
