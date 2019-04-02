package pro.crvt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.crvt.model.entities.Contact;
import pro.crvt.model.error.UnsupportedMediaTypeException;
import pro.crvt.model.parser.ParserFactory;


/**
 * class MailController
 *
 * @author ifedorenko
 * @since 24.03.2019
 */
@RestController
public class MailController {
    private static final String EXTENSION = "msg";

    /**
     * Method createContact.
     * @param mail mail msg
     * @param type mail type. 1 = horyzontal, 2 = vertical, 3 = text;
     * @return contact json
     */
    @RequestMapping(value = "/mail/{type}", method = RequestMethod.POST)
    public ResponseEntity<Contact> createContact(@PathVariable String type, @RequestParam("file") MultipartFile mail) {
        String ext = this.getFileExtension(mail);
        if (!ext.equals(EXTENSION)) {
            throw new UnsupportedMediaTypeException("Extension " + ext + " not supported. Only " + EXTENSION);
        }
        return new ResponseEntity<Contact>(ParserFactory.createParser(type).parse(mail), HttpStatus.OK);
    }

    /**
     * Method getFileExtension.
     * @param file file
     * @return extension
     */
    private String getFileExtension(MultipartFile file) {
        String result = "";
        String fileName = file.getOriginalFilename();
        if (fileName.contains(".")) {
            result = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
       return result;
    }
}
