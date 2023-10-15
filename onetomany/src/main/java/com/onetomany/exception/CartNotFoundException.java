package com.onetomany.exception;

import java.text.MessageFormat;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(Integer id){
        super(MessageFormat.format("Could not find cart with id: {0}",id));
    }
}
