package com.spring.util;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String redirectUrl;

    public CustomException(String message, String redirectUrl) {
        super(message);
        this.redirectUrl = redirectUrl;
    }

}