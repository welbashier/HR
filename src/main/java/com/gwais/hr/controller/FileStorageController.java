package com.gwais.hr.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gwais.hr.service.FileStorageService;

@RestController
@RequestMapping("/File")
@Secured({"ROLE_ADMIN","ROLE_USER"})
public class FileStorageController {

	@Autowired
	FileStorageService fileStorageService;
	
	
	@GetMapping("/Test")
	public String testGetMapping(@RequestParam("msg") String msg) {
		
		// return the message that was sent to us
		return "SUCCESS " + msg;
	}
	
	
	@GetMapping("/Download")
	public Path serveOneFile(@RequestParam("filename") String filename) {

		Path existingFile = fileStorageService.load(filename);
		
		// return the file
		return existingFile;
	}
	
	
	@DeleteMapping("/Delete")
	@Secured({"ROLE_ADMIN"})
	public String deleteFile(@RequestParam("filename") String filename) {

		fileStorageService.delete(filename);
		
		// return the file
		return "SUCCESS. File " + filename + " is deleted.";
	}
	
	
	// in the browser: http://localhost:8011/File/getResource/?filename=hello2.txt
	@GetMapping("/getResource")
	@ResponseBody
	public ResponseEntity<Resource> serveFileAsResource(@RequestParam("filename") String filename) {

		 Resource existingResource = fileStorageService.downloadAsResource(filename);
		 
		 return ResponseEntity.ok()
				 .header(
						 HttpHeaders.CONTENT_DISPOSITION,
						 "attachment; filename=\"" + existingResource.getFilename() + "\""
						 )
				 .body(existingResource);
	}
	
	
	@GetMapping("")
	public String listUploadedFiles(Model model) throws IOException {
		
		Stream<Path> existingFiles = fileStorageService.loadAll();
		
		if (existingFiles != null) {
			model.addAttribute("files", existingFiles.map(
				path -> MvcUriComponentsBuilder.fromMethodName(
						FileStorageController.class,
						"serveOneFile", 
						path.getFileName().toString()
					)
					.build().toUri().toString()
				)
				.collect(Collectors.toList()));
		}
		return "uploadForm";
	}
	
	
	@PostMapping("")
	public String handleFileUploadAndRedirect(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		try {
			fileStorageService.store(file, false);
		} catch (Exception e) {
			return e.toString();
		}
		
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:";
	}
	
	
	@PostMapping("/UploadFile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		
		// pass the file to the service to store the file (it returns nothing!)
		try {
			fileStorageService.store(file, false);
		} catch (Exception e) {
			return e.getMessage();
		}
		// if no exception caught return a success message
		return "File successfully uploaded";
	}
	
	
	@PostMapping("/UploadProfilePhoto")
	public String handleProfilePhotoUpload(@RequestParam("file") MultipartFile file) {
		
		// pass the file to the service to store the file (it returns nothing!)
		try {
			fileStorageService.store(file, true);
		} catch (Exception e) {
			return e.getMessage();
		}
		// if no exception caught return a success message
		return "File successfully uploaded";
	}
	
	
	/*
	 * Static pages
	 */
	
	@GetMapping(value = { "/uploadForm"})
	public ModelAndView filePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("uploadForm");
		return model;
	}
}
