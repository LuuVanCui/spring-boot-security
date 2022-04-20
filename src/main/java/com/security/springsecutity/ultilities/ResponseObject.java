package com.security.springsecutity.ultilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ResponseObject {
    private int statusCode;
    private String message;
    private Object data;
}