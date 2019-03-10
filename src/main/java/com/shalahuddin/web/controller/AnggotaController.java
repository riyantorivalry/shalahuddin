package com.shalahuddin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.shalahuddin.web.dto.ListComboDto;
import com.shalahuddin.web.model.Anggota;
import com.shalahuddin.web.repository.AnggotaRepository;
import com.shalahuddin.web.repository.ListComboRepository;
import com.shalahuddin.web.support.web.Ajax;
import com.shalahuddin.web.support.web.MessageHelper;

@Controller
@RequestMapping(value = "anggota")
public class AnggotaController {
	//	private static final Logger logger = LoggerFactory.getLogger(AnggotaController.class);

	@ModelAttribute(name = "module")
	String module() {
		return "anggota";
	}

	@Autowired
	AnggotaRepository anggotaRepo;

	@Autowired
	ListComboRepository listComboRepo;

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

	//	@PostMapping("anggota/upload")
	//	public String uploadMultipartFile(@RequestParam("uploadPic") MultipartFile file) {
	//		try {
	//
	//		}
	//	}

	@ModelAttribute("listDepartemen")
	public List<ListComboDto> listDepartemen() {
		List<ListComboDto> list = listComboRepo.findDepartmentName();
		return list;
	}

	@GetMapping({ " ", "listAnggota" })
	public String list(Model model) {
		List<Anggota> list = anggotaRepo.findAll();
		model.addAttribute("list", list);
		return "anggota/listAnggota";
	}

	@GetMapping("detailListAnggota" )
	public String detailList(Model model) {
		List<Anggota> list = anggotaRepo.findAll();
		model.addAttribute("list", list);
		return "anggota/detailListAnggota";
	}

	@GetMapping("listAnggota/pic/{idAnggota}") //to load profile photo
	@ResponseBody
	public byte[] getPic(Model model,@PathVariable("idAnggota")Long idAnggota, HttpServletResponse response) {
		Anggota anggota = anggotaRepo.findOne(idAnggota);
		return anggota.getPic();
	}

	@GetMapping({ "addAnggota", "editAnggota" })
	public String form(Model model, String idAnggota, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		if (idAnggota != null) {
			model.addAttribute(anggotaRepo.findOne(Long.valueOf(idAnggota)));
		} else {
			model.addAttribute(new Anggota());
		}
		if (Ajax.isAjaxRequest(requestedWith)) {
			return "anggota/formAnggota".concat(" ::anggotaForm");
		}
		return "anggota/formAnggota";
	}

	@PostMapping("saveAnggota")
	public String save(@ModelAttribute Anggota anggota, @RequestParam("pic") MultipartFile pic, Errors errors, RedirectAttributes ra) throws Exception {
		System.out.println("save form Anggota=" + anggota);
		try {
			if (anggota.getIdAnggota() != null) {
				anggota.setPic(pic.getBytes());
				Anggota entity = anggotaRepo.findOne(anggota.getIdAnggota());
				BeanUtils.copyProperties(anggota, entity, "idAnggota", "version");
				Anggota saveAnggota = anggotaRepo.save(entity);
				System.out.println("saved: " + saveAnggota);
			} else {
				anggota.setPic(pic.getBytes());
				Anggota savedAnggota = anggotaRepo.save(anggota);
				System.out.println("saved 2: " + savedAnggota);
			}
			MessageHelper.addSuccessAttribute(ra, "anggota.save.success");
		}
		catch (Exception e) {
			// TODO: handle exception
			MessageHelper.addErrorAttribute(ra, "anggota.save.error");
		}

		return "redirect:listAnggota";
	}

	@GetMapping("deleteAnggota")
	public String delete(String idAnggota,RedirectAttributes ra) {
		try {
			anggotaRepo.delete(Long.valueOf(idAnggota));
			MessageHelper.addSuccessAttribute(ra, "anggota.delete.success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MessageHelper.addErrorAttribute(ra, "anggota.delete.error");
		}
		return "redirect:listAnggota";
	}

}
