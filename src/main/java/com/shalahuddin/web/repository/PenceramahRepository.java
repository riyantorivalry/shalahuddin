package com.shalahuddin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shalahuddin.web.model.Penceramah;

public interface PenceramahRepository extends JpaRepository<Penceramah, Long>, PenceramahRepositoryCustom {
	@Query("select t from Penceramah t where t.nama like:nama")
	List<Penceramah> findByNama (@Param ("nama") String nama);
}
