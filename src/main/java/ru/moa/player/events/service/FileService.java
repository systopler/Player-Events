package ru.moa.player.events.service;

import liquibase.util.file.FilenameUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.moa.player.events.db.entity.FileEntity;
import ru.moa.player.events.db.repository.FileRepository;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    private final ServletContext context;

    public FileEntity save(MultipartFile inputFile) throws IOException {
        FileEntity fileEntity = new FileEntity();

        fileEntity.setFileName(FilenameUtils.removeExtension(FilenameUtils.getName(inputFile.getOriginalFilename())));
        fileEntity.setSize(inputFile.getSize());
        fileEntity.setMimeType(inputFile.getContentType());
        fileEntity.setFileExt(FilenameUtils.getExtension(inputFile.getOriginalFilename()));
        fileEntity = save(fileEntity);

        // copy file
        inputFile.transferTo(getFile(fileEntity));

        return fileEntity;
    }

    public FileEntity save(FileEntity fileEntity){
        return fileRepository.save(fileEntity);
    }

    private File getFile(FileEntity fndFile) {
        return new File(context.getRealPath("/files")+  File.separator + fndFile.getId() + "." +fndFile.getFileExt());
    }

    public void delete(Long id){
        delete(findById(id));
    }

    public void delete(FileEntity fndFile){
        File file = getFile(fndFile);
        if (file.exists()){
            file.delete();
        }
        fileRepository.delete(fndFile);
    }

    public FileEntity findById(Long id){
        return fileRepository.getOne(id);
    }
}
