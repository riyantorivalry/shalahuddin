package com.shalahuddin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.shalahuddin.web.model.PesertaTraining;
import com.shalahuddin.web.repository.PesertaTrainingRepository;
import com.shalahuddin.web.support.web.Ajax;
import com.shalahuddin.web.support.web.MessageHelper;

@Controller
@RequestMapping(value = "pesertaTraining")
public class PesertaController {

	@Autowired
	public PesertaTrainingRepository pesertaRepo;

	@ModelAttribute(name = "module")
	String module() {
		return "pesertaTraining";
	}

	@InitBinder // untuk menampilkan tanggal
	private void dateBinder(WebDataBinder binder2) {
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat2.setLenient(false);
		CustomDateEditor editor2 = new CustomDateEditor(dateFormat2, true);
		binder2.registerCustomEditor(Date.class, editor2);
	}

	@GetMapping({ " ", "listPeserta" })
	public String list(Model model) {
		List<PesertaTraining> list = pesertaRepo.findAll();
		model.addAttribute("list", list);
		return "pesertaTraining/listPeserta";
	}

	@GetMapping({ "addPeserta", "editPeserta" })
	public String form(Model model, String idPeserta, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		if (idPeserta != null) {
			model.addAttribute(pesertaRepo.findOne(Long.valueOf(idPeserta)));
		} else {
			model.addAttribute(new PesertaTraining());
		}
		if (Ajax.isAjaxRequest(requestedWith)) {
			return "pesertaTraining/formPeserta".concat(" ::pesertaForm");
		}
		return "pesertaTraining/formPeserta";
	}

	@PostMapping("savePeserta")
	public String save(@ModelAttribute PesertaTraining ps_training, Errors errors, RedirectAttributes ra) {
		System.out.println("save form peserta=" + ps_training);
		try {
			if (ps_training.getIdPeserta() != null) {
				PesertaTraining entity = pesertaRepo.findOne(ps_training.getIdPeserta());
				BeanUtils.copyProperties(ps_training, entity, "idPeserta", "version");
				// entity.setNamaTraining(training.getNamaTraining());
				// entity.setTanggalTraining(training.getTanggalTraining());
				// entity.setJumlahPeserta(training.getJumlahPeserta());
				// entity.setCompany(training.getCompany());
				PesertaTraining savedPeserta = pesertaRepo.save(entity);
				System.out.println("saved: " + savedPeserta);
			} else {
				PesertaTraining savedPeserta = pesertaRepo.save(ps_training);
				System.out.println("saved 2: " + savedPeserta);
			}
			MessageHelper.addSuccessAttribute(ra, "training.save.success");
		}
		catch (Exception e) {
			// TODO: handle exception
			MessageHelper.addErrorAttribute(ra, "training.save.error");
		}

		return "redirect:listPeserta";
	}

	@GetMapping("deletePeserta")
	public String delete(String idPeserta,RedirectAttributes ra) {
		try {
			pesertaRepo.delete(Long.valueOf(idPeserta));
			MessageHelper.addSuccessAttribute(ra, "training.delete.success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MessageHelper.addErrorAttribute(ra, "training.delete.error");
		}
		return "redirect:listPeserta";
	}

	@PostMapping("cariPeserta")
	// public String search(Model model,
	// @RequestParam(value="namaDepan",required=true)String namaDepan) {
	public String search(Model model, HttpServletRequest request) {
		String namaDepan = request.getParameter("namaDepan");
		String namaBelakang = request.getParameter("namaBelakang");
		// String tanggalLahir=request.getParameter("tanggalLahir");
		String nilaiTraining = request.getParameter("nilaiTraining");
		String company = request.getParameter("company");
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		System.out.println("namaDepan: " + namaDepan);
		System.out.println("namaBelakang: "+namaBelakang);
		System.out.println("nilai: "+nilaiTraining);
		System.out.println("company: "+company);

		List<PesertaTraining> list = Collections.EMPTY_LIST;
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
			if (StringUtils.isBlank(nilaiTraining)) {
				if (StringUtils.isNotBlank(dateFrom) && StringUtils.isNotBlank(dateTo)) {
					list = pesertaRepo.findAll(namaDepan, namaBelakang, 0, company, dateFormat2.parse(dateFrom), dateFormat2.parse(dateTo));
				} else {
					list = pesertaRepo.findAll(namaDepan, namaBelakang, 0, company, null, null);
				}
			} else {
				if (StringUtils.isNotBlank(dateFrom) && StringUtils.isNotBlank(dateTo)) {
					list = pesertaRepo.findAll(namaDepan,namaBelakang, Integer.valueOf(nilaiTraining),company,dateFormat2.parse(dateFrom), dateFormat2.parse(dateTo));
				} else {
					list = pesertaRepo.findAll(namaDepan, namaBelakang, Integer.valueOf(nilaiTraining), company, null, null);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		//		if (StringUtils.isNotEmpty(namaDepan)) {
		//			list = pesertaRepo.findByNamaDepan(namaDepan);
		//			model.addAttribute("list", list);
		//		}
		// List<PesertaTraining> list=pesertaRepo.findByNamaDepan("%"+namaDepan+"%");
		model.addAttribute("list", list);
		System.out.println(list.toString());
		return "pesertaTraining/listPeserta";
	}

}
