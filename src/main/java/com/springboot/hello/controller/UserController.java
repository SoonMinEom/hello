package com.springboot.hello.controller;

import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.dto.User;
import com.springboot.hello.domain.dto.UserDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> Get(@PathVariable String id) throws SQLException {
        return ResponseEntity.ok().body(this.userDao.findById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<Integer> addAndGet(@RequestBody UserDto userDto) {
        User user = new User(userDto.getId(), userDto.getName(), userDto.getPassword());
        return ResponseEntity.ok().body(userDao.add(user));
    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity
                .ok()
                .body(userDao.deleteAll());
    }
}
