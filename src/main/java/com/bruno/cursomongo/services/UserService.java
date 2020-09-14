package com.bruno.cursomongo.services;

import com.bruno.cursomongo.domain.User;
import com.bruno.cursomongo.dto.UserDTO;
import com.bruno.cursomongo.repository.UserRepository;
import com.bruno.cursomongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User fromDTO(UserDTO usrDTO){
        return new User(usrDTO.getId(), usrDTO.getName(), usrDTO.getEmail());
    }

    public User insert(User obj){
        return repository.insert(obj);
    }

    public User findById(String id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public void update(User usr){
        User usrToBeUpdated = findById(usr.getId());
        usrToBeUpdated.setName(usr.getName());
        usrToBeUpdated.setEmail(usr.getEmail());
        repository.save(usrToBeUpdated);
    }

}
