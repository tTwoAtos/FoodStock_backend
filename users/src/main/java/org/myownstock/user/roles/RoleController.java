package org.myownstock.user.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/roles")
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
