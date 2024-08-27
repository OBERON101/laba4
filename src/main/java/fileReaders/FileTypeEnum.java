package fileReaders;

import java.util.Arrays;

public enum FileTypeEnum {
    JSON(".json"), XML(".xml"), YAML(".yaml");

    private final String fileFormat;

    FileTypeEnum(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public static FileTypeEnum parseFileType(String fileFormat) {
        return Arrays.stream(FileTypeEnum.values())
                .filter((value) -> fileFormat.equals(value.fileFormat))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("File format not found"));
    }
}
