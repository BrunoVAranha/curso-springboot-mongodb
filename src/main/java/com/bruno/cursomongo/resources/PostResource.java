package com.bruno.cursomongo.resources;

import com.bruno.cursomongo.domain.Post;
import com.bruno.cursomongo.resources.util.URL;
import com.bruno.cursomongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitleContainingIgnoreCase(@RequestParam(value = "text", defaultValue = "") String text){
        List<Post> list = service.findByTitleContainingIgnoreCase(URL.decodeParam(text));
        return ResponseEntity.ok().body(list);
    }

}
