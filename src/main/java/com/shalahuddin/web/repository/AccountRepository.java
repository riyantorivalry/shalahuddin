package com.shalahuddin.web.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shalahuddin.web.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	Account findOneByEmail(String email);
	Account findOneByEmailAndActive(String email, boolean active);
	Account findOneByUserIdAndActive(String userId, boolean active);

	@Query("select count(*) from Account a where a.email = :email")
	Optional<Long> existsEmail(@Param("email") String email);

	// This ensures that the EntityManager is automatically cleared when the query has executed,
	// ensuring that no entities are unsynchronized
	@Modifying(clearAutomatically=true)
	@Query(value="UPDATE ADM_USER SET FAIL_COUNTER = :count WHERE USER_ID = :userId", nativeQuery=true)
	void updateLoginFailCounter(@Param("userId") String userId, @Param("count") int count);

	@Modifying(clearAutomatically=true)
	@Query(value="UPDATE ADM_USER SET IS_NON_LOCKED = :accountNonLocked WHERE USER_ID = :userId", nativeQuery=true)
	void setAccountNonLocked(@Param("userId") String userId, @Param("accountNonLocked") boolean accountNonLocked);

	@Modifying(clearAutomatically=true)
	@Query(value="UPDATE ADM_USER SET LAST_LOGIN = :lastLogin WHERE USER_ID = :userId", nativeQuery=true)
	void updateLastLogin(@Param("userId") String userId, @Param("lastLogin") Date lastLogin);

	@Query("SELECT t.failCounter FROM Account t where t.userId = :userId")
	Optional<Integer> findFailCounterByUserId(@Param("userId") String userId);
}