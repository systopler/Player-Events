package ru.moa.player.events.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.moa.player.events.db.entity.FileEntity;
import ru.moa.player.events.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;

    @GetMapping(value = "/file/download/{fileId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getFile(
            @PathVariable long fileId,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        log.debug("fileId = {}", fileId);


        try {
            FileEntity fileEntity = fileService.findById(fileId);
            log.debug("getFile: {}/files/{}.{}", request.getRealPath("/"), fileId, fileEntity.getFileExt());


            File file = new File(String.format("%s/files/%s.%s", request.getRealPath("/"), fileId, fileEntity.getFileExt()));

            System.out.println("The length of the file is : " + file.length());

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Content-Disposition", "filename=" + file.getName());
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");

            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        //return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping(value = "/file/upload", headers=("content-type=multipart/*"))
    public ResponseEntity<FileEntity> upload(@RequestParam("file") MultipartFile inputFile) {
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("File Uploaded Successfully - ", inputFile.getOriginalFilename());
        if (!inputFile.isEmpty()) {
            try {

                return new ResponseEntity<>(fileService.save(inputFile), headers, HttpStatus.OK);
                //return new ResponseEntity<>(fndFileService.save(inputFile), CorsHeaders.corsHeaders(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/file/delete/{fileId}")
    public void getFile(@PathVariable long fileId){
        fileService.delete(fileId);
    }

}
