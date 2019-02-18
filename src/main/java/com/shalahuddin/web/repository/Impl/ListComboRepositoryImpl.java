package com.shalahuddin.web.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shalahuddin.web.dto.ListComboDto;
import com.shalahuddin.web.repository.ListComboRepository;

@Repository
@Qualifier("listComboDao")
public class ListComboRepositoryImpl implements ListComboRepository{
	@Autowired
	JdbcTemplate jdbcTemplate;


	@Override
	public List<ListComboDto> findDepartmentName() {
		// TODO Auto-generated method stub
		String sql = "SELECT distinct id,nama from STRUKTUR_ORGANISASI";
		List<ListComboDto> listData = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ListComboDto.class));
		return listData;
	}

	@Override
	public List<ListComboDto> findKepanitianName() {
		// TODO Auto-generated method stub
		String sql = "SELECT distinct id,nama from KEPANITIAAN";
		List<ListComboDto> listData = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ListComboDto.class));
		return listData;
	}


}
