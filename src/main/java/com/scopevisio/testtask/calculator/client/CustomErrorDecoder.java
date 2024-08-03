package com.scopevisio.testtask.calculator.client;

import com.scopevisio.testtask.calculator.exception.NotFoundException;
import com.scopevisio.testtask.calculator.exception.ProcessingException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 404:
                return new NotFoundException(response.reason());
            default:
                return new ProcessingException("try later");
        }
    }
}
