package fileReaders;

import models.ReactorType;

import java.util.Map;

public class DefaultFileReader extends AbstractFileReader{

    public DefaultFileReader() {
        super(null, null);
    }

    @Override
    public Map<String, ReactorType> readReactorTypesFromFile(String fileName, FileTypeEnum fileType) {
        throw new RuntimeException("File reader for file '" + fileName + "' with file format '" +
                fileType.getFileFormat() + "' not found. \n");
    }
}
