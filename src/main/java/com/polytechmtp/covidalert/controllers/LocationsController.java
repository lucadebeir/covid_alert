package com.polytechmtp.covidalert.controllers;

import com.polytechmtp.covidalert.models.Location;
import com.polytechmtp.covidalert.models.User;
import com.polytechmtp.covidalert.repositories.LocationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationsController {
    @Autowired
    private LocationRepository locationRepository ;

    @GetMapping
    public List<Location> list(){
        return locationRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id) {
        return locationRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location user) {
        return locationRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE )
    public void delete ( @PathVariable Long id){
        // TODO: Toujours verifier s’il faut les enregistrements enfants
        locationRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Location update (@PathVariable Long id , @RequestBody User user) {
        // TODO: Ajouter ici une validation si tous les champs ont ete passes
        // TODO: Sinon, retourner une erreur 400 (Bad Payload)
        Location existingUser = locationRepository.getOne(id);
        BeanUtils.copyProperties(user,existingUser ,"user_id");
        return locationRepository.saveAndFlush(existingUser);
    }
}
