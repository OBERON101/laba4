package fileReaders;

import models.ReactorType;

import java.util.Map;

public abstract class AbstractFileReader {
    protected final AbstractFileReader nextFileReader;
    protected final FileTypeEnum accepts;

    public AbstractFileReader(AbstractFileReader next, FileTypeEnum accepts) {
        this.nextFileReader = next;
        this.accepts = accepts;
    }

    public abstract Map<String, ReactorType> readReactorTypesFromFile(String fileName, FileTypeEnum fileType);
}
