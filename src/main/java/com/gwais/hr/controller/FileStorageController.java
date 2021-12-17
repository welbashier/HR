package com.gwais.hr.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gwais.hr.service.FileStorageService;

@RestController
@RequestMapping("/File")
@Secured({"ROLE_ADMIN","ROLE_USER"})
public class FileStorageController {

	@Autowired
	FileStorageService fileStorageService;
	
	/*
	 * you can access this by typing this in Git Bash terminal:
	 * $ curl -u welbashier:willi123 -X GET localhost:8011/File/Test/?msg=hello -i -v
	 * or
	 * $ curl -u welbashier:willi123 -X GET localhost:8011/File/Test?msg=hello -i -v
	 */
	@GetMapping("/Test")
	public String testGetMapping(@RequestParam("msg") String msg) {
		
		// return the message that was sent to us
		return "SUCCESS " + msg;
	}
	/*
	 * Access by:
	 * $ curl -u welbashier:willi123 localhost:8011/File -v
	 */
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

	/*
	 * Access by:
	 * $ curl -u welbashier:willi123 -F "file=@/c/tmp/history.txt" localhost:8011/File -v
	 */
	@PostMapping("")
	public String handleFileUploadAndRedirect(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

		fileStorageService.store(file);
		
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:";
	}
	
	/*
	 * Access by:
	 * $ curl -u welbashier:willi123 -F "file=@/c/tmp/hello.txt" localhost:8011/File/UploadFile -v
	 */
	@PostMapping("/UploadFile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		
		// pass the file to the service to store the file (it returns nothing!)
		fileStorageService.store(file);
		
		// if no exception caught return a success message
		return "File successfully uploaded";
	}
}
