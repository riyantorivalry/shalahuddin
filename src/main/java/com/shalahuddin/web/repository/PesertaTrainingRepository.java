/**
 *
 */
package com.shalahuddin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shalahuddin.web.model.PesertaTraining;

/**
 * @author USER11
 *
 */

@Repository
public interface PesertaTrainingRepository extends JpaRepository<PesertaTraining, Long>, PesertaTrainingRepositoryCustom {
	//	List<PesertaTraining> findAll(String namaDepan, String namaBelakang, Date tanggalLahir, Integer nilaiTraining, String company);
	@Query("select t from PesertaTraining t where t.namaDepan like:namaDepan")  //using HQL, setelah tanda":" gak boleh ada spasi dan "PesertaTraining" bukan dari nama tabel tapi dari nama model
	List<PesertaTraining> findByNamaDepan(@Param("namaDepan") String namaDepan);
}
