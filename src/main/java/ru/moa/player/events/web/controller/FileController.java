package ru.moa.player.events.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.moa.player.events.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class FileController {
    private FileService fileService;

    @GetMapping(value = "/file/download/{fileId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getFile(
            @PathVariable long fileId,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        log.debug("fileId = {}", fileId);


        try {
            FndFile fndFile = fndFileService.findOne(fileId);
            LOGGER.debug("getFile: {}/files/{}.{}", request.getRealPath("/"), fileId, fndFile.getFileExt());


            File file = new File(String.format("%s/files/%s.%s", request.getRealPath("/"), fileId, fndFile.getFileExt()));

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
            LOGGER.error(e.getMessage(), e);
        }

        //return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping(value = "/file/upload", headers=("content-type=multipart/*"))
    public ResponseEntity<FndFile> upload(@RequestParam("file") MultipartFile inputFile) {
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("File Uploaded Successfully - ", inputFile.getOriginalFilename());
        if (!inputFile.isEmpty()) {
            try {

                return new ResponseEntity<>(fndFileService.save(inputFile), headers, HttpStatus.OK);
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
        fndFileService.delete(fileId);
    }

}
