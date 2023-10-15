package com.onetomany.entity;

import com.onetomany.dto.ItemDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serialNumber;
    @ManyToOne
    private Cart cart;

    public static Item from(ItemDto itemDto){
        Item item=new Item();
        item.setSerialNumber(itemDto.getSerialNumber());
        return item;
    }

}
