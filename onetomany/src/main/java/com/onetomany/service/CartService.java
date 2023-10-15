package com.onetomany.service;

import com.onetomany.entity.Cart;
import com.onetomany.entity.Item;
import com.onetomany.exception.CartNotFoundException;
import com.onetomany.exception.ItemIsAlreadyAssignedException;
import com.onetomany.repository.CartRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepo cartRepo;
    private final ItemService itemService;

    public Cart addCart(Cart cart) {
        log.info(String.valueOf(cart.getId()+" bu car idsidir"));
        log.info("Bu cart idisidir");
        return cartRepo.save(cart);
    }

    public List<Cart> getCarts() {
        return StreamSupport.stream(cartRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Cart getCart(Integer id) {
        return cartRepo.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
    }

    public Cart deleteCart(Integer id) {
        Cart cart = getCart(id);
        cartRepo.delete(cart);
        return cart;
    }

    @Transactional
    public Cart editCart(Integer id, Cart cart) {
        Cart result = getCart(id);
        result.setName(cart.getName());
        return result;
    }

    @Transactional
    public Cart addItemToCart(Integer cartId,Integer itemId){
        Cart cart=getCart(cartId);
        Item item=itemService.getItem(itemId);
        if (Objects.nonNull(item.getCart())){
            throw new ItemIsAlreadyAssignedException(itemId,item.getCart().getId());
        }
        cart.addItem(item);
        return cart;
    }

    @Transactional
    public Cart removeItemFromCart(Integer cartId,Integer itemId){
        Cart cart=getCart(cartId);
        Item item=itemService.getItem(itemId);
        cart.removeItem(item);
        return cart;
    }

}
