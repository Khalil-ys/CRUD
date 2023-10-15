package com.onetomany.controller;

import com.onetomany.dto.ItemDto;
import com.onetomany.entity.Item;
import com.onetomany.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestBody final ItemDto itemDto) {
        Item item=itemService.addItem(Item.from(itemDto));
        log.info("bu item idisidir {}"+item.getId()+" dto id: "+itemDto.getId());
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(){
        List<Item> items = itemService.getItems();
        List<ItemDto> dtos=items.stream()
                .map(ItemDto::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable final Integer id){
        Item item=itemService.getItem(id);
        return new ResponseEntity<>(ItemDto.from(item),HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ItemDto> deleteItem(@PathVariable final Integer id){
        Item item=itemService.deleteItem(id);
        return new ResponseEntity<>(ItemDto.from(item),HttpStatus.OK);

    }
    @PutMapping(value = "{id}")
    public ResponseEntity<ItemDto> editeItem(@PathVariable final Integer id,
                                             @RequestBody final ItemDto itemDto){
        Item editedItem=itemService.editItem(id,Item.from(itemDto));
        return new ResponseEntity<>(ItemDto.from(editedItem),HttpStatus.OK);
    }


}
