package com.onetomany.exception;

import java.text.MessageFormat;

public class ItemIsAlreadyAssignedException extends RuntimeException{

    public ItemIsAlreadyAssignedException(final Integer itemId,final Integer cartId){
        super(MessageFormat.format("Item: {0} is already assigned to cart: {1},",itemId,cartId));
    }
}
