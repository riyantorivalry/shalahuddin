package com.shalahuddin.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shalahuddin.web.dto.ListComboDto;

@Service
public class ListComboService {
	@Autowired
	ListComboService listComboService;

	public List<ListComboDto> findDepartmentName() {
		List<ListComboDto> listData = listComboService.findDepartmentName();
		return listData;
	}

	public List<ListComboDto> findKepanitianName(){
		List<ListComboDto> listData = listComboService.findKepanitianName();
		return listData;
	}

}
