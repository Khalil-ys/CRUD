package com.onetomany.service;

import com.onetomany.entity.Item;
import com.onetomany.exception.ItemNotFoundException;
import com.onetomany.repository.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepo itemRepo;

    public Item addItem(Item item){
        return itemRepo.save(item);
    }

    public List<Item> getItems(){
        return StreamSupport.stream(itemRepo.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Item getItem(Integer id){
        return itemRepo.findById(id)
                .orElseThrow(()->new ItemNotFoundException(id));
    }

    public Item deleteItem(Integer id){
        Item item=getItem(id);
        itemRepo.delete(item);
        return item;
    }
    @Transactional
    public Item editItem(Integer id,Item item){
        Item result=getItem(id);
        result.setSerialNumber(item.getSerialNumber());
        return result;
    }





}
