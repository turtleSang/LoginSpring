package com.ThankSen.BaiTap2.Service.InterfaceService;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageServiceImp {
    void init();

    void store(MultipartFile file);

    Path load(String fileName);

    void deleteAll();

    Resource loadAsResource(String fileName);
}
