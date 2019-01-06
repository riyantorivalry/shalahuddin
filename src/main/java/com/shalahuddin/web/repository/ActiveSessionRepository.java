/**
 *
 */
package com.shalahuddin.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shalahuddin.web.model.ActiveSession;

@Repository
public interface ActiveSessionRepository extends
JpaRepository<ActiveSession, String> {
	ActiveSession findOneByUserNameIgnoreCase(String userName);
	@Query("select count(*) from ActiveSession a where upper(a.userName) = upper(:userName)")
	Optional<Long> existsUserName(@Param("userName") String userName);
	@Modifying(clearAutomatically=true)
	@Query(value="delete from ActiveSession a where upper(a.userName) = upper(:userName)")
	void deleteByUserName(@Param("userName") String userName);
}
