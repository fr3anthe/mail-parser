package pro.crvt.model.parser;

import org.springframework.web.multipart.MultipartFile;
import pro.crvt.model.entities.Contact;

/**
 * Interface MailParser.
 *
 * @author ifedorenko
 * @since 24.03.2019
 */
public interface MailParser {
    /**
     * Method parse.
     * @param file file for parsing
     * @return result
     */
    Contact parse(MultipartFile file);
}
