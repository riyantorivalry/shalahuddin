/**
 *
 */
package com.shalahuddin.web.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shalahuddin.web.exception.StorageFileNotFoundException;
import com.shalahuddin.web.form.FileBucket;
import com.shalahuddin.web.service.StorageService;
import com.shalahuddin.web.support.web.MessageHelper;
import com.shalahuddin.web.validator.FileValidator;

@Controller
@RequestMapping(value="upload")
public class FileUploadController{
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	private static final String FORM_VIEW_NAME = "upload/form";
	private final StorageService storageService;

	@Autowired
	FileValidator fileValidator;

	@ModelAttribute("module")
	String module() {
		return "upload";
	}

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
		this.storageService.init();
	}

	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@GetMapping("")
	public String listUploadedFiles(Model model) throws IOException {
		logger.debug("storage service={}", storageService);
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		model.addAttribute("files", storageService
				.loadAll()
				.map(path ->
				MvcUriComponentsBuilder
				.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
				.build().toString())
				.collect(Collectors.toList()));

		return FORM_VIEW_NAME;
	}

	@GetMapping("files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
				.body(file);
	}

	@PostMapping("save")
	public String handleFileUpload(@ModelAttribute FileBucket fileBucket, Errors errors,
			RedirectAttributes ra) {
		logger.debug("store file={}", fileBucket.getFile().getOriginalFilename());
		if (errors.hasErrors()) {
			return FORM_VIEW_NAME;
		}
		storageService.store(fileBucket.getFile());
		MessageHelper.addSuccessAttribute(ra, "file.upload.success");
		//        redirectAttributes.addFlashAttribute("message",
		//                "You successfully uploaded " + fileBucket.getFile().getOriginalFilename() + "!");

		return "redirect:/upload";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
