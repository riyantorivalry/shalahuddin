/**
 *
 */
package com.shalahuddin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shalahuddin.web.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
	List<Menu> findByActiveOrderByPosition(boolean isActive);
	List<Menu> findByActiveAndParentIdOrderByPosition(boolean isActive, String parentId);
	Menu findOneByIdAndActive(String id, boolean active);
}
