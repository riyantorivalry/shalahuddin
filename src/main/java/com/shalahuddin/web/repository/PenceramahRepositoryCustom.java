package com.shalahuddin.web.repository;

import java.util.List;

import com.shalahuddin.web.model.Penceramah;

public interface PenceramahRepositoryCustom {
	List<Penceramah> findAll(String nama, String bidangKajian, String alamat);

}
