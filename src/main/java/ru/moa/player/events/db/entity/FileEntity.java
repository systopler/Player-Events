package ru.moa.player.events.db.entity;

import lombok.Setter;
import ru.moa.player.events.db.entity.common.DeletableEntity;

import javax.persistence.*;

@Entity
@Table(name = "file")
@Setter
public class FileEntity extends DeletableEntity<Long> {
    private String fileName;
    private String fileExt;
    private String mimeType;
    private Long size;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    @Column(name = "file_ext")
    public String getFileExt() {
        return fileExt;
    }

    @Column(name = "mime_type")
    public String getMimeType() {
        return mimeType;
    }

    @Column(name = "file_size")
    public Long getSize() {
        return size;
    }
}
