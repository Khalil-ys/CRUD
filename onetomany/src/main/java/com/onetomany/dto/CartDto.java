package com.onetomany.dto;

import com.onetomany.entity.Cart;
import com.onetomany.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Integer id;
    private String name;
    private List<ItemDto> itemsDto=new ArrayList<>();

    public static CartDto from(Cart cart){
        CartDto cartDto=new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setName(cart.getName());
        cartDto.setItemsDto(cart.getItems().stream().map(ItemDto::from).collect(Collectors.toList()));
        return cartDto;
    }
}
