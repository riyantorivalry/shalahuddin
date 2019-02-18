package com.shalahuddin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shalahuddin.web.dto.ListComboDto;
import com.shalahuddin.web.repository.ListComboRepository;

@Controller
@RequestMapping(value = "listCombo")
public class ListComboController {
	@Autowired
	ListComboRepository listComboRepo;

	@ModelAttribute(name = "module")
	String module() {
		return "listCombo";
	}

	@ModelAttribute("listDepartemen")
	public List<ListComboDto> listDepartemen() {
		List<ListComboDto> list = listComboRepo.findDepartmentName();
		return list;
	}

	@GetMapping({ " ", "kepanitiaan" })
	public String listKepanitian(Model model) {
		List<ListComboDto> list = listComboRepo.findKepanitianName();
		model.addAttribute("list", list);
		return "listCombo/kepanitiaan";
	}

}
