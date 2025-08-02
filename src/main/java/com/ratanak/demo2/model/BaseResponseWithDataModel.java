package com.ratanak.demo2.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseResponseWithDataModel extends BaseResponseModel{
    private Object data;
    public BaseResponseWithDataModel(String status, String message,Object data) {
        super(status, message);
        this.data= data;
    }
}
