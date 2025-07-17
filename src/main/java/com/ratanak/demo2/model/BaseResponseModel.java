package com.ratanak.demo2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponseModel {
    private String status;
    private  String message ;
}
