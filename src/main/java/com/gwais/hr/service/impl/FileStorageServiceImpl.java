package com.gwais.hr.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gwais.hr.service.FileStorageService;
import com.gwais.hr.service.FileStorageException;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private Path rootLocation = Paths.get("/tmp/files/");
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new FileStorageException("File is empty");
			}
			String targetFileFullPath = this.rootLocation + "/" + file.getOriginalFilename();
			File tempFile = new File(targetFileFullPath);
			if (tempFile.exists()) {
				throw new FileStorageException("File already exists");
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new FileStorageException("Failed to store file", e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new FileStorageException("Failed to read stored files", e);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
	
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Resource resource = getFileAsResource(filename);
			
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new FileStorageException("Could not read file");
			}
		}
		catch (MalformedURLException e) {
			throw new FileStorageException("Corrupt file");
		}
	}

	private Resource getFileAsResource(String filename) throws MalformedURLException {
		Path file = load(filename);
		Resource resource = new UrlResource(file.toUri());
		return resource;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
