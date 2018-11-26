package com.invillia.acme.domain.provider;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProviderResponse implements Serializable {
    private long id;
    private String name;
    private String address;
}
