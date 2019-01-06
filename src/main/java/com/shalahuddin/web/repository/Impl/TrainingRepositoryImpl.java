package com.shalahuddin.web.repository.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.shalahuddin.web.model.Training;
import com.shalahuddin.web.repository.TrainingRepositoryCustom;

public class TrainingRepositoryImpl implements TrainingRepositoryCustom{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Training> findBetweenDate(String namaTraining, Integer jumlahPeserta, Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		String hql=("select t from Training t where t.namaTraining like:namaTraining");
		if(jumlahPeserta!=null) {
			hql=hql.concat(" and t.jumlahPeserta>:jumlahPeserta");
		}
		if(dateFrom!=null) {
			hql=hql.concat(" and t.tanggalTraining>:dateFrom");
		}
		if(dateTo!=null) {
			hql=hql.concat(" and t.tanggalTraining<:dateTo");
		}
		hql=hql.concat(" order by t.tanggalTraining");

		Query query = em.createQuery(hql);
		query.setParameter("namaTraining", "%"+namaTraining.toUpperCase()+"%");
		if(jumlahPeserta!=null) {
			query.setParameter("jumlahPeserta", jumlahPeserta);
		}
		if(dateFrom!=null) {
			query.setParameter("dateFrom", dateFrom);
		}
		if(dateTo!=null) {
			query.setParameter("dateTo", dateTo);
		}

		return query.getResultList();
	}

}
