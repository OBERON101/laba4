package fileReaders;

import models.ReactorType;
import models.Source;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;

public class YamlFileReader extends AbstractFileReader {

    public YamlFileReader(FileTypeEnum accepts, AbstractFileReader next) {
        super(next, accepts);
    }

    @Override
    public Map<String, ReactorType> readReactorTypesFromFile(String fileName, FileTypeEnum fileType) {
        if (!accepts.equals(fileType)) {
            return nextFileReader.readReactorTypesFromFile(fileName, fileType);
        }
        try {
            InputStream inputStream = new FileInputStream(fileName);
            Source source = new Source(fileName, fileType);
            Yaml yaml = new Yaml(new Constructor(Map.class, new LoaderOptions()));
            Map<String, Map<String, Object>> map = yaml.load(inputStream);
            return map.keySet().stream()
                    .map((type)-> ReactorType.buildFromMapAndSource(type, map.get(type), source))
                    .collect(Collectors.toMap(ReactorType::getType, (reactorType -> reactorType)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Incorrect file format");
        }
    }
}
