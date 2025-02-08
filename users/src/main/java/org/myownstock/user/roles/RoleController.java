package org.myownstock.user.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping
    public Role add(@RequestBody Role role){
        return roleService.add(role);
    }
    @PutMapping
    public Role update(@RequestBody Role role){
        return roleService.update(role);
    }

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }
}
