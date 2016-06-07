package pl.java.scalatech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3192267601031880565L;
    @Getter
    private final String clazz;
    @Getter
    private final Long id;

    public ResourceNotFoundException(String clazz, Long id) {
        super();
        this.clazz = clazz;
        this.id = id;
    }

    public ResourceNotFoundException(String message, Throwable cause, String clazz, Long id) {
        super(message, cause);
        this.clazz = clazz;
        this.id = id;
    }

    public ResourceNotFoundException(String message, String clazz, Long id) {
        super(message);
        this.clazz = clazz;
        this.id = id;
    }

    public ResourceNotFoundException(Throwable cause, String clazz, Long id) {
        super(cause);
        this.clazz = clazz;
        this.id = id;
    }

}
