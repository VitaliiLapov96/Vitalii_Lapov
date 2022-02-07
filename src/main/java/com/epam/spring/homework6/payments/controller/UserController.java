package com.epam.spring.homework6.payments.controller;

import com.epam.spring.homework6.payments.controller.dto.UserDto;
import com.epam.spring.homework6.payments.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class UserController {

    private final UserService userService;

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ApiOperation("Get user by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{userId}")
    public UserDto getUser(@PathVariable @Min(1) Long userId) {
        return userService.getUser(userId);
    }

    @ApiOperation("Update user by id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/{userId}")
    public UserDto updateUser(@PathVariable @Min(1) Long userId,
                              @RequestBody @Valid UserDto userDto) {
        System.out.println(userDto);
        return userService.updateUser(userId, userDto);
    }

    @ApiOperation("Delete user by id")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Min(1) Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Get users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public List<UserDto> listUsers(@RequestParam(defaultValue = "0") @Min(0) Integer page,
                                   @RequestParam(defaultValue = "10") @Min(0) Integer pageSize,
                                   @RequestParam(defaultValue = "userId") String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return userService.listUsers(pageable);
    }

}
