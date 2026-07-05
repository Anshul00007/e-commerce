package com.fproject.fcommerce.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.fproject.fcommerce.dto.CartRequestDTO;
import com.fproject.fcommerce.dto.CartResponseDTO;
import com.fproject.fcommerce.entity.Cart;
import com.fproject.fcommerce.entity.CartItem;
import com.fproject.fcommerce.entity.Product;
import com.fproject.fcommerce.exception.CartItemNotFoundException;
import com.fproject.fcommerce.exception.CartNotFoundException;
import com.fproject.fcommerce.exception.ProductNotFoundException;
import com.fproject.fcommerce.mapper.CartMapper;
import com.fproject.fcommerce.repo.CartItemRepo;
import com.fproject.fcommerce.repo.CartRepo;
import com.fproject.fcommerce.repo.ProductRepo;
import com.fproject.fcommerce.entity.User;

@Service
public class CartService {
        private final ProductRepo productrepo;
        private final CartRepo cartrepo;
        private final CartItemRepo cartitemrepo;

        public CartService(ProductRepo productrepo, CartRepo cartrepo, CartItemRepo cartitemrepo) {
                this.productrepo = productrepo;
                this.cartrepo = cartrepo;
                this.cartitemrepo = cartitemrepo;
        }

        private User getCurrentUser() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return (User) authentication.getPrincipal();
        }

        public CartResponseDTO addToCart(CartRequestDTO dto) {
                Product P = productrepo.findById(dto.getProductId())
                                .orElseThrow(() -> new ProductNotFoundException("product not found"));
                User currentUser = getCurrentUser();
             Cart C=   cartrepo.findByUserAndActiveTrue(currentUser)
                                .orElseGet(() -> {
                                        Cart newCart = new Cart();
                                        newCart.setActive(true);
                                        newCart.setUser(currentUser);
                                        return cartrepo.save(newCart);
                                }

                                );

                CartItem cartItem = cartitemrepo.findByCartAndProduct(C, P)
                                .orElseGet(() -> {
                                        CartItem newItem = new CartItem();
                                        newItem.setCart(C);
                                        newItem.setProduct(P);
                                        newItem.setQuantity(0);
                                        return newItem;
                                });

                cartItem.setQuantity(cartItem.getQuantity() + dto.getQuantity());
                cartitemrepo.save(cartItem);
                return CartMapper.toDTO(C);
        }

        public CartResponseDTO removeFromCart(Long productId) {
                 User currentUser = getCurrentUser();
                Cart cart = cartrepo.findByUserAndActiveTrue(currentUser)
                                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

                Product product = productrepo.findById(productId)
                                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

                CartItem cartitem = cartitemrepo.findByCartAndProduct(cart, product)
                                .orElseThrow(() -> new CartItemNotFoundException("Item not found in cart"));

                cartitemrepo.delete(cartitem);
                return CartMapper.toDTO(cart);
        }

        public CartResponseDTO getCart() {
                    User currentUser = getCurrentUser();
                Cart cart = cartrepo.findByUserAndActiveTrue(currentUser)
                                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
                return CartMapper.toDTO(cart);
        }

        public CartResponseDTO updateCart(Long productId, CartRequestDTO dto) {
                  User currentUser = getCurrentUser();
                Cart cart = cartrepo.findByUserAndActiveTrue(currentUser)
                                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

                Product product = productrepo.findById(productId)
                                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

                CartItem cartitem = cartitemrepo.findByCartAndProduct(cart, product)
                                .orElseThrow(() -> new CartItemNotFoundException("Item not found in cart"));

                cartitem.setQuantity(dto.getQuantity());
                cartitemrepo.save(cartitem);
                return CartMapper.toDTO(cart);
        }
}
