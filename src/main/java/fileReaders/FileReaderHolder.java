package fileReaders;

import models.ReactorType;

import java.util.Map;

public class FileReaderHolder {
    private final AbstractFileReader fileReader;

    public FileReaderHolder() {
        this.fileReader = new JsonFileReader(
                FileTypeEnum.JSON, new XmlFileReader(
                FileTypeEnum.XML, new YamlFileReader(
                FileTypeEnum.YAML, new DefaultFileReader())));
    }

    public Map<String, ReactorType> readReactorTypesFromFile(String fileName, FileTypeEnum fileType) {
        return fileReader.readReactorTypesFromFile(fileName, fileType);
    }
}
