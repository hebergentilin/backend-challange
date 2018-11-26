package com.invillia.acme.v1.provider;

import com.invillia.acme.domain.provider.ProviderRequest;
import com.invillia.acme.domain.provider.ProviderResponse;
import com.invillia.acme.entity.Provider;
import com.invillia.acme.exceptions.EntityNotFound;
import com.invillia.acme.log.LogUtils;
import com.invillia.acme.log.MessageLog;
import com.invillia.acme.utils.ApiErrors;
import com.invillia.acme.v1.provider.service.ProviderService;
import io.swagger.annotations.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * This class is responsible for v1 of API provider
 *
 * @author Paulo
 * @since 26/11/2018
 */
@RestController
@RequestMapping("/v1/providers")
@Slf4j
@Api("/v1/providers")
public class ProviderController {

    private static final String CODE = "ER0001";

    @Autowired
    @Setter
    private ProviderService providerService;


    /**
     * Create a new provider
     *
     * @param providerRequest
     * @return a provider created
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @RequestMapping(value = { "", "/" },
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus
    @ApiOperation(value = "Find a seller by provider and intermediaryKey")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ProviderResponse.class),
            @ApiResponse(code = 400, message = "Invalid Provider", response = Map.class)
    })
    public ResponseEntity createProvider(
            @ApiParam(required = true, name = "provider")
            @Valid
            @RequestBody ProviderRequest providerRequest,
            Errors errors) {
        log.info(MessageLog.BC0002.text(), providerRequest.toString());

        if (errors != null && errors.hasErrors()) {
            log.info(MessageLog.BC0003.text(), errors.toString());
            return new ResponseEntity(new ApiErrors(errors).errors(), HttpStatus.BAD_REQUEST);
        }

        Provider savedProvider = providerService.save(providerRequest);

        if (savedProvider != null) {
            return ResponseEntity.ok(savedProvider);
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    /**
     * Update provider information
     *
     * @param providerRequest
     * @return a provider created
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @RequestMapping(value = "/{providerId}",
            method = RequestMethod.PATCH,
            produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus
    @ApiOperation(value = "Find a seller by provider and intermediaryKey")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ProviderResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = Map.class)
    })
    public ResponseEntity updateProvider(
            @ApiParam(required = true, name = "providerId")
            @PathVariable(name = "providerId")
            long providerId,
            @ApiParam(required = true, name = "provider")
            @Valid
            @RequestBody ProviderRequest providerRequest,
            Errors errors) {
        log.info(MessageLog.BC0006.text(), providerRequest.toString());

        if (errors != null && errors.hasErrors()) {
            log.info(MessageLog.BC0003.text(), errors.toString());
            return new ResponseEntity(new ApiErrors(errors).errors(), HttpStatus.BAD_REQUEST);
        }

        try {
            Provider updatedProvider = providerService.update(providerId, providerRequest);
            return ResponseEntity.ok(updatedProvider);
        } catch (EntityNotFound ex) {
            LogUtils.printExceptionStack(ex);
            log.info(MessageLog.BC0007.text());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
