package org.example.exceptions;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String filePath) {
        super(String.format("File with path: \"%s\" could not be found", filePath));
    }
}
