package com.gwais.hr.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

	void init();

	void store(MultipartFile file) throws Exception;

	Stream<Path> loadAll();

	Path load(String filename);

	Resource downloadAsResource(String filename);

	void deleteAll();

	void delete(String filename);
}
