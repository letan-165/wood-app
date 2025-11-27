package com.app.woodshop.feature.order.controller;

import com.app.woodshop.common.ApiResponse;
import com.app.woodshop.common.enums.OrderStatus;
import com.app.woodshop.feature.order.dto.request.OrderCreateRequest;
import com.app.woodshop.feature.order.dto.response.OrderResponse;
import com.app.woodshop.feature.order.entity.Order;
import com.app.woodshop.feature.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @GetMapping
    ApiResponse<List<OrderResponse>> findAll() {
        return ApiResponse.<List<OrderResponse>>builder()
                .message("Danh sách tất cả đơn hàng")
                .result(orderService.findAll())
                .build();
    }

    @GetMapping("/user/{userID}")
    ApiResponse<List<OrderResponse>> findByUser(
            @PathVariable String userID,
            @RequestParam(required = false) OrderStatus status) {

        return ApiResponse.<List<OrderResponse>>builder()
                .message(status == null
                        ? "Danh sách đơn hàng của user " + userID
                        : "Danh sách đơn hàng của user " + userID + " với trạng thái " + status.name())
                .result(orderService.findByUserAndStatus(userID, status))
                .build();
    }

    @PostMapping
    ApiResponse<OrderResponse> create(@RequestBody OrderCreateRequest request) {
        return ApiResponse.<OrderResponse>builder()
                .message("Tạo đơn hàng thành công")
                .result(orderService.create(request))
                .build();
    }

    @GetMapping("/{orderID}")
    ApiResponse<OrderResponse> findById(@PathVariable Long orderID) {
        return ApiResponse.<OrderResponse>builder()
                .message("Lấy đơn hàng:" + orderID)
                .result(orderService.findById(orderID))
                .build();
    }

    @PatchMapping("/{orderID}/status/{status}")
    ApiResponse<OrderResponse> updateStatus(@PathVariable Long orderID,
                                            @PathVariable OrderStatus status) {
        return ApiResponse.<OrderResponse>builder()
                .message("Cập nhật đơn hàng: " + orderID + " với trạng thái: " + status.name())
                .result(orderService.updateStatus(orderID, status))
                .build();
    }
}
