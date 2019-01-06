package com.shalahuddin.web.repository.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.shalahuddin.web.model.Anggota;
import com.shalahuddin.web.repository.AnggotaRepositoryCustom;

public class AnggotaRepositoryImpl implements AnggotaRepositoryCustom {
	@PersistenceContext
	private EntityManager em2;

	@SuppressWarnings("unchecked")
	//	@Override
	//	public List<Anggota> findAll(Long idAnggota, String nama, Date tanggalLahir, String alamat, String prodi,
	//			String universitas, String angkatan, String kontak, String email, Long departemenId, String keanggotaan,
	//			Boolean isActive) {
	@Override
	public List<Anggota> findAll(String nama, Date tanggalLahir, String alamat, String prodi, String universitas,
			String angkatan, String kontak, String email, String keanggotaan) {
		// TODO Auto-generated method stub
		String hql = "SELECT t from Anggota t where t.nama like:nama ";
		//		if(idAnggota !=null) {
		//			hql=hql.concat(" and t.idAnggota=:idAnggota ");
		//		}
		if(tanggalLahir !=null) {
			hql=hql.concat(" and t.tanggalLahir like:tanggalLahir ");
		}
		if(alamat !=null) {
			hql=hql.concat(" and t.alamat like:alamat ");
		}
		if(prodi !=null) {
			hql=hql.concat(" and t.prodi like:prodi ");
		}
		if(universitas !=null) {
			hql=hql.concat(" and t.universitas like:universitas ");
		}
		if(angkatan !=null) {
			hql=hql.concat(" and t.angkatan like:angkatan ");
		}
		if(kontak !=null) {
			hql=hql.concat(" and t.kontak like:kontak ");
		}
		if(email !=null) {
			hql=hql.concat(" and t.email like:email ");
		}
		//		if(departemenId !=null) {
		//			hql=hql.concat(" and t.departemenId=:departemenId ");
		//		}
		if(keanggotaan !=null) {
			hql=hql.concat(" and t.keanggotaan like:keanggotaan ");
		}

		Query query = em2.createQuery(hql);
		query.setParameter("nama", "%"+nama+"%");
		if(alamat !=null) {
			query.setParameter("alamat", "%"+alamat+"%");
		}
		if(prodi !=null) {
			query.setParameter("prodi", "%"+prodi+"%");
		}
		if(universitas !=null) {
			query.setParameter("universitas", "%"+universitas+"%");
		}
		if(angkatan !=null) {
			query.setParameter("angkatan", "%"+angkatan+"%");
		}
		if(kontak !=null) {
			query.setParameter("kontak", "%"+kontak+"%");
		}
		if(email !=null) {
			query.setParameter("email", "%"+email+"%");
		}
		if(keanggotaan !=null) {
			query.setParameter("keanggotaan", "%"+keanggotaan+"%");
		}
		return query.getResultList();
	}


}
