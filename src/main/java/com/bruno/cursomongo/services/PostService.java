package com.bruno.cursomongo.services;

import com.bruno.cursomongo.domain.Post;
import com.bruno.cursomongo.repository.PostRepository;
import com.bruno.cursomongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRep;

    public Post findById(String id){
        return postRep.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
    }

    public List<Post> findByTitleContainingIgnoreCase(String text){
        return postRep.findByTitleContainingIgnoreCase(text);
    }
}
