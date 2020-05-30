package by.epam.ultraair.persistence.rest.controller;


import by.epam.ultraair.dao.implementation.UserDAOImpl;
import by.epam.ultraair.persistence.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @GetMapping
    public List<User> getAllUsers(){
        return new UserDAOImpl().getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findUserByLogin(@PathVariable Integer id){
        Optional<User> userOptional = new UserDAOImpl().get(id);
        User user = userOptional.orElse(null);
        if (user != null){
            return ResponseEntity.ok(user);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("create")
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> request){
        String login = request.get("login");
        String password = request.get("password");
        boolean admin = request.get("admin").equals("true");
        User user = new User(login, password, admin);
        new UserDAOImpl().createUser(user);
        return new ResponseEntity<>("Successfully added", HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        ResponseEntity<String> responseEntity = null;
        try{
            new UserDAOImpl().deleteUser(id);
            responseEntity = new ResponseEntity<>("User with id: " + id + " deleted", HttpStatus.OK);
        }
        catch (SQLException e){
            responseEntity = new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Map<String, String> body){
        User user = new UserDAOImpl().get(id).orElse(null);
        ResponseEntity<String> responseEntity = null;
        if (user != null){
            user.setLogin(body.get("login"));
            user.setPassword(body.get("password"));
            user.setAdmin(body.get("admin").equals("true"));
            new UserDAOImpl().updateUser(user);
            responseEntity = new ResponseEntity<>("User with id " + id + " updated", HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>("No such user with id: " + id, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
