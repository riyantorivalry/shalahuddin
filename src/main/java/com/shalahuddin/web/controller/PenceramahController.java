package com.shalahuddin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shalahuddin.web.model.Penceramah;
import com.shalahuddin.web.repository.PenceramahRepository;
import com.shalahuddin.web.support.web.Ajax;
import com.shalahuddin.web.support.web.MessageHelper;

@Controller
@RequestMapping(value = "penceramah")
public class PenceramahController {
	private static final Logger logger = LoggerFactory.getLogger(PenceramahController.class);

	@ModelAttribute(name = "module")
	String module() {
		return "penceramah";
	}
	@Autowired
	PenceramahRepository penceramahRepo;

	@InitBinder // show Date in "dd-MM-yyyy" format
	private void dateBinder(WebDataBinder binder2) {
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat2.setLenient(false);
		CustomDateEditor editor2 = new CustomDateEditor(dateFormat2, true);
		binder2.registerCustomEditor(Date.class, editor2);
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
			throws ServletException {
		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}


	@GetMapping("listPenceramah/pic/{idPenceramah}") //to load profile photo
	@ResponseBody
	public byte[] getPic(Model model,@PathVariable("idPenceramah")Long idPenceramah, HttpServletResponse response) {
		Penceramah penceramah = penceramahRepo.findOne(idPenceramah);
		return penceramah.getPic();
	}

	@GetMapping({ " ", "listPenceramah" })
	public String list(Model model) {
		List<Penceramah> list = penceramahRepo.findAll();
		model.addAttribute("list", list);
		return "penceramah/listPenceramah";
	}

	@GetMapping({ "addPenceramah", "editPenceramah" })
	public String form(Model model, String idPenceramah, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		if (idPenceramah != null) {
			model.addAttribute(penceramahRepo.findOne(Long.valueOf(idPenceramah)));
		} else {
			model.addAttribute(new Penceramah());
		}
		if (Ajax.isAjaxRequest(requestedWith)) {
			return "penceramah/formPenceramah".concat(" ::penceramahForm");
		}
		return "penceramah/formPenceramah";
	}

	@PostMapping("savePenceramah")
	public String save(@ModelAttribute Penceramah penceramah, @RequestParam("pic") MultipartFile pic,Errors errors, RedirectAttributes ra) {
		System.out.println("save form penceramah=" + penceramah);
		try {
			if (penceramah.getIdPenceramah() != null) {
				penceramah.setPic(pic.getBytes());
				Penceramah entity = penceramahRepo.findOne(penceramah.getIdPenceramah());
				BeanUtils.copyProperties(penceramah, entity, "idPenceramah", "version");
				Penceramah savePenceramah = penceramahRepo.save(entity);
				System.out.println("saved: " + savePenceramah);
			} else {
				penceramah.setPic(pic.getBytes());
				Penceramah savedPenceramah = penceramahRepo.save(penceramah);
				System.out.println("saved 2: " + savedPenceramah);
			}
			MessageHelper.addSuccessAttribute(ra, "penceramah.save.success");
		}
		catch (Exception e) {
			// TODO: handle exception
			MessageHelper.addErrorAttribute(ra, "penceramah.save.error");
		}

		return "redirect:listPenceramah";
	}

	@GetMapping("deletePenceramah")
	public String delete(String idPenceramah,RedirectAttributes ra) {
		try {
			penceramahRepo.delete(Long.valueOf(idPenceramah));
			MessageHelper.addSuccessAttribute(ra, "penceramah.delete.success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MessageHelper.addErrorAttribute(ra, "penceramah.delete.error");
		}
		return "redirect:listPenceramah";
	}

}
