package com.bruno.cursomongo.resources;

import com.bruno.cursomongo.domain.Post;
import com.bruno.cursomongo.domain.User;
import com.bruno.cursomongo.dto.UserDTO;
import com.bruno.cursomongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User user = service.findById(id);
        UserDTO usrDTO = new UserDTO(user);
        return ResponseEntity.ok().body(usrDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO usrDTO){
        User usr = service.fromDTO(usrDTO);
        service.insert(usr);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usr.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id){
        User user = service.fromDTO(userDTO);
        user.setId(id);
        service.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }
}
