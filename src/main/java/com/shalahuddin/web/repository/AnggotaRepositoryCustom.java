package com.shalahuddin.web.repository;

import java.util.Date;
import java.util.List;

import com.shalahuddin.web.model.Anggota;

public interface AnggotaRepositoryCustom {
	//	List<Anggota> findAll(Long idAnggota, String nama, Date tanggalLahir, String alamat, String prodi, String universitas, String angkatan, String kontak, String email, Long departemenId,String keanggotaan, Boolean isActive );
	List<Anggota> findAll(String nama, Date tanggalLahir, String alamat, String prodi, String universitas, String angkatan, String kontak, String email, String keanggotaan);

}
