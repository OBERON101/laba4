package fileReaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.ReactorType;
import models.Source;

import java.io.File;
import java.util.Map;

public class JsonFileReader extends AbstractFileReader {

    public JsonFileReader(FileTypeEnum accepts, AbstractFileReader next) {
        super(next, accepts);
    }

    @Override
    public Map<String, ReactorType> readReactorTypesFromFile(String fileName, FileTypeEnum fileType) {
        if (!accepts.equals(fileType)) {
            return nextFileReader.readReactorTypesFromFile(fileName, fileType);
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            Source source = new Source(fileName, fileType);
            Map<String, ReactorType> map = mapper.readValue(new File(fileName), new TypeReference<Map<String, ReactorType>>() {
            });
            map.keySet().forEach((key) -> {
                map.get(key).setSource(source);
            });
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Incorrect file format");
        }
    }

}
