package com.polytechmtp.covidalert.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.polytechmtp.covidalert.models.User;
import com.polytechmtp.covidalert.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository ;

    @GetMapping
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get (@PathVariable Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with ID "+id+" not found");
        }
        return userRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return userRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "{id}", method=RequestMethod.DELETE )
    public void delete ( @PathVariable Long id){
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with ID "+id+" not found");
        }
        // TODO: Toujours verifier s’il faut les enregistrements enfants
        userRepository.deleteById(id);
    }

    @PutMapping
    @RequestMapping(value="{id}", method=RequestMethod.PUT)
    public User update (@PathVariable Long id , @RequestBody User user) {
        // TODO: Ajouter ici une validation si tous les champs ont ete passes

        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with ID "+id+" not found");
        }
        // TODO: Sinon, retourner une erreur 400 (Bad Payload)
        User existingUser = userRepository.getOne(id);
        //user.setUserId(id);
        BeanUtils.copyProperties(user,existingUser ,"user_id");
        return userRepository.saveAndFlush(existingUser);
    }
}
