package ru.moa.player.events.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.moa.player.events.db.repository.FileRepository;

import javax.servlet.ServletContext;

@AllArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    private final ServletContext context;

    @Transactional(readOnly = true)
    public FndFile findOne(Long id){
        // TODO
        return fndFileRepository.findById(id).get();
    }

    public FndFile save(MultipartFile inputFile) throws IOException {
        FndFile fileInfo = new FndFile();

        fileInfo.setFileName(FilenameUtils.removeExtension(FilenameUtils.getName(inputFile.getOriginalFilename())));
        fileInfo.setSize(inputFile.getSize());
        fileInfo.setMimeType(inputFile.getContentType());
        fileInfo.setFileExt(FilenameUtils.getExtension(inputFile.getOriginalFilename()));
        fileInfo = save(fileInfo);

        // copy file
        inputFile.transferTo(getFile(fileInfo));

        return fileInfo;
    }

    private File getFile(FndFile fndFile) {
        return new File(context.getRealPath("/files")+  File.separator + fndFile.getId() + "." +fndFile.getFileExt());
    }

    @Transactional
    public FndFile save(FndFile fndFile){
        return fndFileRepository.save(fndFile);
    }

    public void delete(Long id){
        delete(findOne(id));
    }

    public void delete(FndFile fndFile){
        File file = getFile(fndFile);
        if (file.exists()){
            file.delete();
        }
        fndFileRepository.delete(fndFile);
    }

}
