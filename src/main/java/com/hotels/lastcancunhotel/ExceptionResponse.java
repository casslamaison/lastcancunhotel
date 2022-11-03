package com.hotels.lastcancunhotel;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {

    private final String message;
    private final Set<String> details;

}
