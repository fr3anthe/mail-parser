package pro.crvt.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnsupportedMediaTypeException extends RuntimeException {

    /**
     * Constructor.
     * @param s error message
     */
    public UnsupportedMediaTypeException(String s) {
        super(s);
    }
}
