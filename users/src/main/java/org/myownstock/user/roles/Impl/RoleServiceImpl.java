package org.myownstock.user.roles.Impl;

import org.myownstock.user.roles.IRole;
import org.myownstock.user.roles.Role;
import org.myownstock.user.roles.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private IRole repository;

    @Override
    public Role add(Role role) {
        return repository.save(role);
    }

    @Override
    public List<Role> getAll(){
        return repository.findAll();
    }

    @Override
    public Role update(Role role) {
        return repository.save(role);
    }
}
