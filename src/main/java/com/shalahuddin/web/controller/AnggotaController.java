package com.shalahuddin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shalahuddin.web.model.Anggota;
import com.shalahuddin.web.repository.AnggotaRepository;
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

	@InitBinder // untuk menampilkan tanggal
	private void dateBinder(WebDataBinder binder2) {
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat2.setLenient(false);
		CustomDateEditor editor2 = new CustomDateEditor(dateFormat2, true);
		binder2.registerCustomEditor(Date.class, editor2);
	}

	@GetMapping({ " ", "listAnggota" })
	public String list(Model model) {
		List<Anggota> list = anggotaRepo.findAll();
		model.addAttribute("list", list);
		return "anggota/listAnggota";
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
	public String save(@ModelAttribute Anggota anggota, Errors errors, RedirectAttributes ra) {
		System.out.println("save form Anggota=" + anggota);
		try {
			if (anggota.getIdAnggota() != null) {
				Anggota entity = anggotaRepo.findOne(anggota.getIdAnggota());
				BeanUtils.copyProperties(anggota, entity, "idAnggota", "version");
				// entity.setNamaTraining(training.getNamaTraining());
				// entity.setTanggalTraining(training.getTanggalTraining());
				// entity.setJumlahPeserta(training.getJumlahPeserta());
				// entity.setCompany(training.getCompany());
				Anggota saveAnggota = anggotaRepo.save(entity);
				System.out.println("saved: " + saveAnggota);
			} else {
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
