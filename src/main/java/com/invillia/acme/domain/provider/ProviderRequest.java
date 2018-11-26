package com.invillia.acme.domain.provider;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@ToString
public class ProviderRequest implements Serializable {
    private long id;

    @NotBlank
    @NotNull
    @Size(min = 3)
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 3)
    private String address;


}
