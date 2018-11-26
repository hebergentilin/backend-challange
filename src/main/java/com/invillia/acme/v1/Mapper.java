package com.invillia.acme.v1;

public interface Mapper<REQUEST, ENTITY, RESPONSE> {
    ENTITY requestToEntity(REQUEST objectRequest);
    RESPONSE entityToResponse(ENTITY entity);
}
