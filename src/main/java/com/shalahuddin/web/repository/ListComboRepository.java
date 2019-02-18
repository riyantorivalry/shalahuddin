package com.shalahuddin.web.repository;

import java.util.List;

import com.shalahuddin.web.dto.ListComboDto;

//@Repository
public interface ListComboRepository{
	public List<ListComboDto> findDepartmentName();

	public List<ListComboDto> findKepanitianName();

}
