package com.example.techthing.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.techthing.dto.request.AddToCartRequest;
import com.example.techthing.dto.response.CartDetailResponse;
import com.example.techthing.dto.response.GetCartResponse;
import com.example.techthing.entity.Cart;
import com.example.techthing.entity.CartDetail;
import com.example.techthing.entity.Product;
import com.example.techthing.entity.User;
import com.example.techthing.exception.ErrorCode;
import com.example.techthing.exception.MyException;
import com.example.techthing.repository.CartDetailRepository;
import com.example.techthing.repository.ProductRepository;
import com.example.techthing.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartDetailService {
    CartDetailRepository cartDetailRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    public CartDetailResponse addToCart(AddToCartRequest addToCartRequest) {
        // Lấy thông username đang Đăng nhập
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Lấy thông tin user đang Đăng nhập
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

        // Lấy thông tin cart của user
        Cart cart = user.getCart();

        // Lấy thông tin product
        Product product = productRepository.findById(addToCartRequest.getProduct_id())
                .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));

        CartDetail cartDetail = new CartDetail();
        // Kiểm tra product tồn tại trong cart
        if (cartDetailRepository.existsByProductAndCart(product, cart)) {
            // Lấy thông tin product mà người dùng đã đặt trong cartdetail
            cartDetail = cartDetailRepository.findByProductAndCart(product, cart)
                    .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));

            cartDetail.setQuantity(cartDetail.getQuantity() + addToCartRequest.getQuantity());
            cartDetailRepository.save(cartDetail);
        } else {
            // build CartDetail
            cartDetail = CartDetail.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(addToCartRequest.getQuantity())
                    .build();
            cartDetailRepository.save(cartDetail);
        }

        return CartDetailResponse.builder()
                .cart_id(cartDetail.getId())
                .product_id(cartDetail.getProduct().getId())
                .quantity(cartDetail.getQuantity())
                .build();
    }

    public List<GetCartResponse> getMyCart() {
        // Lấy thông username đang Đăng nhập
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Lấy thông tin user đang Đăng nhập
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

        // Lấy thông tin cart của user
        Cart cart = user.getCart();

        List<GetCartResponse> myProducts = new ArrayList<>();

        // Lấy danh thông tin cart trong cartdetail
        List<CartDetail> cartDetail = cartDetailRepository.findAllByCart(cart);
        if (!cartDetail.isEmpty()) {
            for (CartDetail detail : cartDetail) {
                // Map CartDetail tp GetCartResponse
                GetCartResponse product = GetCartResponse.builder()
                        .product_id(detail.getProduct().getId())
                        .price(detail.getProduct().getPrice())
                        .quantity(detail.getQuantity())
                        .sub_total(detail.getProduct().getPrice() * detail.getQuantity())
                        .build();
                myProducts.add(product);
            }
        }
        return myProducts;
    }

    public GetCartResponse increaseQuantity(String product_id) {
        // Lấy thông username đang Đăng nhập
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Lấy thông tin user đang Đăng nhập
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

        // Lấy thông tin cart của user
        Cart cart = user.getCart();

        // Lấy thông tin của product
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));

        // Lấy item trong CartDetail
        CartDetail itemProduct = cartDetailRepository.findByProductAndCart(product, cart)
                .orElseThrow();

        itemProduct.setQuantity(itemProduct.getQuantity() + 1);
        cartDetailRepository.save(itemProduct);

        return GetCartResponse.builder()
                .product_id(itemProduct.getId())
                .price(itemProduct.getProduct().getPrice())
                .quantity(itemProduct.getQuantity())
                .sub_total(itemProduct.getProduct().getPrice() * itemProduct.getQuantity())
                .build();
    }

    public GetCartResponse decreaseQuantity(String product_id) {
        // Lấy thông username đang Đăng nhập
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Lấy thông tin user đang Đăng nhập
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_EXISTED));

        // Lấy thông tin cart của user
        Cart cart = user.getCart();

        // Lấy thông tin của product
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new MyException(ErrorCode.PRODUCT_NOT_EXISTED));

        // Lấy item trong CartDetail
        CartDetail itemProduct = cartDetailRepository.findByProductAndCart(product, cart)
                .orElseThrow();

        itemProduct.setQuantity(itemProduct.getQuantity() - 1);
        cartDetailRepository.save(itemProduct);

        return GetCartResponse.builder()
                .product_id(itemProduct.getId())
                .price(itemProduct.getProduct().getPrice())
                .quantity(itemProduct.getQuantity())
                .sub_total(itemProduct.getProduct().getPrice() * itemProduct.getQuantity())
                .build();
    }

    public void delete(String id) {
        cartDetailRepository.deleteById(id);
    }
}
