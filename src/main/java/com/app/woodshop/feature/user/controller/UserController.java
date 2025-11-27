package com.app.woodshop.feature.user.controller;

import com.app.woodshop.common.ApiResponse;
import com.app.woodshop.feature.user.dto.request.UserRequest;
import com.app.woodshop.feature.user.dto.response.UserResponse;
import com.app.woodshop.feature.user.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    ApiResponse<List<UserResponse>> findAll(){
        return ApiResponse.<List<UserResponse>>builder()
                .message("Lấy danh sách người dùng")
                .result(userService.findAll())
                .build();
    }

    @PostMapping
    ApiResponse<UserResponse> create(@RequestBody UserRequest request){
        return ApiResponse.<UserResponse>builder()
                .message("Đăng kí người dùng: "+request.getFullName())
                .result(userService.create(request))
                .build();
    }

    @GetMapping("/{userID}")
    ApiResponse<UserResponse> findByID(@PathVariable String userID){
        return ApiResponse.<UserResponse>builder()
                .message("Tìm người dùng dựa vào ID:  "+userID)
                .result(userService.findById(userID))
                .build();
    }

    @PutMapping("/{userID}")
    ApiResponse<UserResponse> update(@PathVariable String userID,
                                     @RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .message("Cập nhật người dùng: " + userID)
                .result(userService.update(userID, request))
                .build();
    }

    @DeleteMapping("/{userID}")
    ApiResponse<String> delete(@PathVariable String userID) {
        userService.delete(userID);
        return ApiResponse.<String>builder()
                .message("Xóa người dùng: " + userID)
                .result(userID)
                .build();
    }





}
