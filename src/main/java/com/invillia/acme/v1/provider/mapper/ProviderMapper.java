package com.invillia.acme.v1.provider.mapper;

import com.invillia.acme.domain.provider.ProviderRequest;
import com.invillia.acme.domain.provider.ProviderResponse;
import com.invillia.acme.entity.Provider;
import com.invillia.acme.v1.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper implements Mapper<ProviderRequest, Provider, ProviderResponse> {
    @Override
    public Provider requestToEntity(ProviderRequest objectRequest) {
        return Provider.builder()
                .id(objectRequest.getId())
                .name(objectRequest.getName())
                .address(objectRequest.getAddress())
                .build();
    }

    @Override
    public ProviderResponse entityToResponse(Provider provider) {
        return ProviderResponse.builder()
                .id(provider.getId())
                .name(provider.getName())
                .address(provider.getAddress())
                .build();
    }
}
