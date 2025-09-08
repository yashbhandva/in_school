package com.shop.in.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Response {
    public String statusCode;
    public String statusMessage;
}
