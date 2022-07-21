package org.mjtech.tourguide.web.controller;

import org.mjtech.tourguide.dto.UserDto;
import org.mjtech.tourguide.model.user.User;
import org.mjtech.tourguide.web.service.UserService;
import org.mjtech.tourguide.utility.ConvertTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  @RequestMapping("/")
  public String index() {
        return "Greetings from TourGuide!";
    }

  @GetMapping("/users/{username}")
  public UserDto getUser(@PathVariable String username) {
    return ConvertTo.convertToUserDto(userService.getUser(username));
  }

  @GetMapping("/users")
  public Iterable<UserDto> getUsers() {
    	return ConvertTo.convertToUserListDto(userService.getAllUsers());
    }

  @PostMapping("/users")
  public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {

    return new ResponseEntity<UserDto>(ConvertTo.convertToUserDto(userService.addUser(userDto)), HttpStatus.CREATED);
  }

  @PutMapping("/users")
  public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
    return ConvertTo.convertToUserDto(userService.updateUser(userDto));
  }

  @DeleteMapping("/users")
  public ResponseEntity<Void> deleteUser(@RequestBody UserDto userDto) {
     userService.deleteUser(userDto);
    return ResponseEntity.noContent().build();
  }

}