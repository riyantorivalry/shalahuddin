package com.shalahuddin.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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

import com.shalahuddin.web.model.Account;
import com.shalahuddin.web.model.Training;
import com.shalahuddin.web.repository.TrainingRepository;
import com.shalahuddin.web.support.web.Ajax;
import com.shalahuddin.web.support.web.MessageHelper;

@Controller
@RequestMapping(value = "training")
public class TrainingController {
	@Autowired
	private TrainingRepository repo;

	@ModelAttribute("module") // method ini akan dipassing pertama kali,
	String module() {
		return "training";
	}

	@InitBinder // untuk menampilkan tanggal
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@GetMapping({ " ", "list" })
	public String list(Model model) {
		model.addAttribute("userlogin", "budi");
		model.addAttribute("today", new Date());
		model.addAttribute("account", createAccount());
		// List<TrainingForm> list = new ArrayList();
		// list.add(new TrainingForm("java Fundamental",new Date(), 20,"Multisoft"));
		// list.add(new TrainingForm("Spring",new Date(), 15,"Multisoft"));
		// list.add(new TrainingForm("Thymeleaf",new Date(), 10,"Multisoft"));
		// model.addAttribute("trainingList",list);

		List<Training> trainingList = repo.findAll();
		model.addAttribute("trainingList", trainingList);
		return "training/list";
	}

	@GetMapping({ "add", "edit" })
	public String form(Model model, String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		if (id != null) {
			model.addAttribute(repo.findOne(Long.valueOf(id)));
		} else {
			model.addAttribute(new Training());
		}
		if (Ajax.isAjaxRequest(requestedWith)) {
			return "training/form".concat(" ::trainingForm");
		}
		return "training/form";
	}

	@PostMapping("save")
	public String save(@ModelAttribute Training training, Errors errors, RedirectAttributes ra) {
		System.out.println("save form=" + training);

		try {
			if (training.getId() != null) {
				Training entity = repo.findOne(training.getId());
				BeanUtils.copyProperties(training, entity, "id", "version");
				// entity.setNamaTraining(training.getNamaTraining());
				// entity.setTanggalTraining(training.getTanggalTraining());
				// entity.setJumlahPeserta(training.getJumlahPeserta());
				// entity.setCompany(training.getCompany());
				Training savedTraining = repo.save(entity);
				System.out.println("saved: " + savedTraining);
			} else {
				Training savedTraining = repo.save(training);
				System.out.println("saved 2: " + savedTraining);
			}
			MessageHelper.addSuccessAttribute(ra, "training.save.success");
		}
		catch (Exception e)
		{
			MessageHelper.addErrorAttribute(ra, "training.save.error");
		}

		return "redirect:list";
	}

	@GetMapping("delete")
	public String delete(String id, RedirectAttributes ra) {
		try {
			repo.delete(Long.valueOf(id));
			MessageHelper.addSuccessAttribute(ra, "training.delete.success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MessageHelper.addErrorAttribute(ra, "training.delete.error");
		}
		return "redirect:list";
	}

	// @PostMapping("search")
	// public String search(Model model,
	// @RequestParam(value="namaTraining",required=true) String namaTraining) {
	// System.out.println("namaTraining= "+ namaTraining);
	// List<Training>list = repo.findByNamaTrainingLike("%"+ namaTraining +"%");
	// model.addAttribute("trainingList", list);
	// List<Training>jmlPeserta =
	// repo.findByJumlahPeserta(Integer.valueOf(jumlahPeserta),
	// "%"+namaTraining+"%");
	// model.addAttribute("trainingList", jmlPeserta);
	// return "training/list";
	// }

	@PostMapping("search")
	public String search(Model model, HttpServletRequest request) {

		String namaTraining = request.getParameter("namaTraining");
		String jumlahPeserta = request.getParameter("jumlahPeserta");
		System.out.println("namaTraining= " + namaTraining);
		System.out.println("jumlahPeserta= " + jumlahPeserta);
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		System.out.println("dateFrom= " + dateFrom);
		System.out.println("dateTo= " + dateTo);

		List<Training> list = Collections.EMPTY_LIST;
		try {
			SimpleDateFormat dateFromat = new SimpleDateFormat("dd-MM-yyyy");

			if (StringUtils.isBlank(jumlahPeserta)) {
				if (StringUtils.isNotBlank(dateFrom) && StringUtils.isNotBlank(dateTo)) {
					list = repo.findBetweenDate(namaTraining, 0, dateFromat.parse(dateFrom), dateFromat.parse(dateTo));
				} else {
					list = repo.findBetweenDate(namaTraining, 0, null, null);
				}
			} else {
				if (StringUtils.isNotBlank(dateFrom) && StringUtils.isNotBlank(dateTo)) {
					list = repo.findBetweenDate(namaTraining, Integer.valueOf(jumlahPeserta),dateFromat.parse(dateFrom), dateFromat.parse(dateTo));
				} else {
					list = repo.findBetweenDate(namaTraining, Integer.valueOf(jumlahPeserta), null, null);
				}
			}

			// if(StringUtils.isEmpty(jumlahPeserta)) {
			// list = repo.findByJumlahPeserta(0, "%"+ namaTraining +"%");
			//// model.addAttribute("trainingList", list);
			// }
			// else {
			// list = repo.findByJumlahPeserta(Integer.valueOf(jumlahPeserta), "%"+
			// namaTraining +"%");
			//// model.addAttribute("trainingList", list);
			// }
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		model.addAttribute("trainingList", list);
		return "training/list";
	}

	private Account createAccount() {
		Account account = new Account();
		account.setUserId("budi");
		account.setEmail("budi@multisoft.co.id");
		account.setDateOfBirth(new Date());
		account.setLastLogin(Instant.now());
		account.setPassword("password");
		return account;
	}

}
