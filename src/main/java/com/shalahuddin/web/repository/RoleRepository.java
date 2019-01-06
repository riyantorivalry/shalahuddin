/**
 *
 */
package com.shalahuddin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalahuddin.web.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	List<Role> findByActive(boolean active);
	Role findOneByIdAndActive(String id, boolean active);
}
