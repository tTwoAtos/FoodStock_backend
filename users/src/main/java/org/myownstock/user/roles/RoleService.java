package org.myownstock.user.roles;

import org.myownstock.user.helpers.services.Service;

import java.util.List;

public interface RoleService extends Service<Role> {
    /**
     * @param Role role
     * @return Role
     */
    public Role add(Role role);

    /**
     *
     * @return Role[]
     */
    public List<Role> getAll();

    /**
     * @param Role role
     * @return Role[]
     */
    public Role update(Role role);
}
