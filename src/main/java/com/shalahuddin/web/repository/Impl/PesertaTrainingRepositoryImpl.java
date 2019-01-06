package com.shalahuddin.web.repository.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.shalahuddin.web.model.PesertaTraining;
import com.shalahuddin.web.repository.PesertaTrainingRepositoryCustom;

public class PesertaTrainingRepositoryImpl implements PesertaTrainingRepositoryCustom {

	@PersistenceContext
	private EntityManager em2;

	@Override
	public List<PesertaTraining> findAll(String namaDepan, String namaBelakang, Integer nilaiTraining, String company, Date dateFrom, Date dateTo){
		String hql="select t from PesertaTraining t where t.namaDepan like:namaDepan";

		if(namaBelakang!=null) {
			hql=hql.concat(" and t.namaBelakang like:namaBelakang");
		}
		//		if(tanggalLahir!=null) {
		//			hql=hql.concat(" and t.tanggalLahir:tanggalLahir");
		//		}
		if(nilaiTraining!=null) {
			hql=hql.concat(" and t.nilaiTraining>=:nilaiTraining");
		}

		if(company!=null) {
			hql=hql.concat(" and t.company like:company");
		}

		Query query = em2.createQuery(hql);
		query.setParameter("namaDepan", "%"+namaDepan.toUpperCase()+"%");

		if(namaBelakang!=null) {
			query.setParameter("namaBelakang", "%"+namaBelakang+"%");
		}
		//		if(tanggalLahir!=null) {
		//			query.setParameter("tanggalLahir",tanggalLahir);
		//		}
		if(nilaiTraining!=null) {
			query.setParameter("nilaiTraining", nilaiTraining);
		}
		if(company!=null) {
			query.setParameter("company", "%"+company+"%");
		}

		return query.getResultList();

	}


}
