package com.shalahuddin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shalahuddin.web.model.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long>, TrainingRepositoryCustom{
	List<Training> findByNamaTrainingAndJumlahPesertaAndCompany(String namaTraining, Integer jumlahPeserta, String company);
	@Query("select t from Training t where t.namaTraining like:namaTraining")
	List<Training> findByNamaTrainingLike(String namaTraining);
	@Query("select t from Training t where t.jumlahPeserta>:jumlahPeserta and namaTraining like:namaTraining")  //using HQL, setelah tanda":" gak boleh ada spasi
	List<Training> findByJumlahPeserta(@Param("jumlahPeserta") Integer jumlahPeserta, @Param("namaTraining") String namaTraining);


}
