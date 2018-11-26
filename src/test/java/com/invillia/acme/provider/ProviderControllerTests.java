package com.invillia.acme.provider;

import com.invillia.acme.domain.provider.ProviderRequest;
import com.invillia.acme.entity.Provider;
import com.invillia.acme.v1.provider.ProviderController;
import com.invillia.acme.v1.provider.service.ProviderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProviderControllerTests {
    @Mock
    private ProviderService providerService;

    @InjectMocks
    private ProviderController providerController;

    @Autowired
    private Validator validator;

    @Before
    public void beforeTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createProviderSuccess() {
        ProviderRequest providerRequest = ProviderRequest.builder().name("Name").address("Address").build();
        when(providerService.save(providerRequest)).thenReturn(Provider.builder().name("Name").build());
        ResponseEntity response = providerController.createProvider(providerRequest, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createProviderInvalid() {
        ProviderRequest providerRequest = ProviderRequest.builder().name(null).address(null).build();
        Set<ConstraintViolation<ProviderRequest>> violations = validator.validate(providerRequest);
        assertEquals(4, violations.size());
    }
}
