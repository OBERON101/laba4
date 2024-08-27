package models;

import fileReaders.FileTypeEnum;

public class Source {
    private String fileName;
    private FileTypeEnum fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileTypeEnum getFileType() {
        return fileType;
    }

    public void setFileType(FileTypeEnum fileType) {
        this.fileType = fileType;
    }

    public Source(String fileName, FileTypeEnum fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "Source{" +
                "fileName='" + fileName + '\'' +
                ", fileType=" + fileType +
                '}';
    }
}
