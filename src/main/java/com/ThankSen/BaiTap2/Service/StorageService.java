package com.ThankSen.BaiTap2.Service;

import com.ThankSen.BaiTap2.Service.InterfaceService.StorageServiceImp;
import com.ThankSen.BaiTap2.Service.StorageConfigs.StorageException;
import com.ThankSen.BaiTap2.Service.StorageConfigs.StorageFileNotFoundException;
import com.ThankSen.BaiTap2.Service.StorageConfigs.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService implements StorageServiceImp {
    private Path rootLocation;

    @Autowired
    public StorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }


    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);

        } catch (Exception e) {
            throw new SecurityException("Cant not initialize storage folder", e);
        }
    }

    @Override
    public void store(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new StorageException("Can not save Empty file");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
            //Check path to file into root location
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Can not save file outside root location");
            }
            //Save file to folder
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {
            throw new StorageException("Fail to save file", e);
        }

    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new StorageException("Can not read file");
            }

        } catch (Exception e) {
           throw new StorageFileNotFoundException("Can not find: " + fileName, e);
        }
    }
}
