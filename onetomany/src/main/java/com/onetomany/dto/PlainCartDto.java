package com.onetomany.dto;

import com.onetomany.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlainCartDto {

    private Integer id;
    private String name;

    public static PlainCartDto from(Cart cart){
        PlainCartDto plainCartDto=new PlainCartDto();
        plainCartDto.setId(cart.getId());
        plainCartDto.setName(cart.getName());
        return plainCartDto;
    }
}
