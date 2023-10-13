package com.ThankSen.BaiTap2.Controller;

import com.ThankSen.BaiTap2.Payload.CreateDto;
import com.ThankSen.BaiTap2.Service.InterfaceService.StorageServiceImp;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    private StorageServiceImp storageServiceImp;

    @Autowired
    public FileController(StorageServiceImp storageServiceImp){
        this.storageServiceImp = storageServiceImp;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            storageServiceImp.store(file);
            return new ResponseEntity<>(new CreateDto(true, "upload Successful", null), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(new CreateDto(false, e.getMessage() , null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<?> getFileDetail(@PathVariable String filename){

        try {
            Resource file = storageServiceImp.loadAsResource(filename);
            return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }catch (Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }



    }
}
