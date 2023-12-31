package com.onetomany.controller;

import com.onetomany.dto.CartDto;
import com.onetomany.entity.Cart;
import com.onetomany.service.CartService;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> addCart(@RequestBody final CartDto cartDto) {
        Cart cart = cartService.addCart(Cart.from(cartDto));
        log.info(String.valueOf(cart.getId()));
        log.info("Cart id : {}"+cart.getId());
        return new ResponseEntity<>(CartDto.from(cart), OK);
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts() {
        List<Cart> carts = cartService.getCarts();
        List<CartDto> dtos = carts.stream()
                .map(CartDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable final Integer id) {
        Cart cart = cartService.getCart(id);
        return new ResponseEntity<>(CartDto.from(cart), OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CartDto> deleteCart(@PathVariable Integer id) {
        Cart cart = cartService.deleteCart(id);
        return new ResponseEntity<>(CartDto.from(cart), OK);

    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CartDto> editeCart(@PathVariable Integer id, @RequestBody final CartDto cartDto) {
        Cart cart = cartService.editCart(id, Cart.from(cartDto));
        return new ResponseEntity<>(CartDto.from(cart), OK);

    }

    @PostMapping(value = "{cartId}/items/{itemId}/add")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable final Integer cartId,
                                                 @PathVariable final Integer itemId){
        Cart cart = cartService.addItemToCart(cartId, itemId);
        return new ResponseEntity<>(CartDto.from(cart),OK);
    }

    @DeleteMapping(value = "{cartId}/items/{itemId}/remove")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable final Integer cartId,
                                                    @PathVariable final Integer itemId){
        Cart cart = cartService.removeItemFromCart(cartId, itemId);
        return new ResponseEntity<>(CartDto.from(cart),OK);
    }







}
