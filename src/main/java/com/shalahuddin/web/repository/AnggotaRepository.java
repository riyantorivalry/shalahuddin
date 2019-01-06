package com.shalahuddin.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shalahuddin.web.model.Anggota;

public interface AnggotaRepository extends JpaRepository<Anggota, Long>, AnggotaRepositoryCustom {
	//	@Query("select t from Anggota t where t.nama like:nama and t.tanggalLahir like:tanggalLahir and t.alamat like:alamat and t.prodi like:prodi and t.universitas like:universitas and t.angkatan like:angkatan and t.keanggotaan like:keanggotaan")
	//	List<Anggota> findByNamaTglLahirAlamatProdiUnivAngkatanKeanggotaanAktif(@Param("nama") String nama,@Param("tanggalLahir") Date tanggalLahir, @Param("alamat") String alamat, @Param("prodi") String prodi, @Param("universitas") String universitas, @Param("angkatan") String angkatan,@Param("keanggotaan")String keanggotaan );
	@Query("select t from Anggota t where t.nama like:nama ")
	List<Anggota> findByNama(@Param("nama") String nama);

}
