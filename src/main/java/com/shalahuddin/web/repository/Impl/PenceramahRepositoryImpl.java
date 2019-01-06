package com.shalahuddin.web.repository.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.shalahuddin.web.model.Penceramah;
import com.shalahuddin.web.repository.PenceramahRepositoryCustom;

public class PenceramahRepositoryImpl implements PenceramahRepositoryCustom {
	@PersistenceContext
	private EntityManager em2;

	@SuppressWarnings("unchecked")
	@Override
	public List<Penceramah> findAll(String nama, String bidangKajian, String alamat) {
		// TODO Auto-generated method stub
		String hql = "SELECT t from Penceramah t where t.nama like:nama ";

		if (bidangKajian != null) {
			hql = hql.concat(" and t.bidangKajian like:bidangKajian ");
		}
		if (alamat != null) {
			hql = hql.concat(" and t.alamat like:alamat ");
		}

		Query query = em2.createQuery(hql);
		query.setParameter("nama", "%"+nama.toUpperCase()+"%");

		if(bidangKajian != null) {
			query.setParameter("bidangKajian", "%"+bidangKajian+"%");
		}

		if(alamat != null) {
			query.setParameter("alamat", "%"+alamat+"%");
		}

		return query.getResultList();
	}

}
