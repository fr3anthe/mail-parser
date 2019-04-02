package pro.crvt.model.parser.html;

import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pro.crvt.model.entities.Contact;
import pro.crvt.model.configure.Configuration;
import pro.crvt.model.parser.MailParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HoryzontalParserTest {
    private final String type = "1";
    private final String msg = "horyzontal.msg";
    private final String horyzont = "company=, name=Сергей Александрович, number=+79250407624, email=auto-pik@mail.ru";

    /**
     * Test horyzontal type msg.
     * @throws IOException exception
     */
    @Test
    public void testHoryzontParse() throws IOException {
        File file = new File(ClassLoader.getSystemResource(msg).getFile());
        Contact contact = Configuration.loadContactParam(type);
        MailParser parser = new HoryzontalParser(contact);
        contact = parser.parse(fileToMultipartFile(file));
        assertThat(horyzont, is(contact.toString()));
    }

    /**
     * Convert file to MultiPartFile.
     * @param file file
     * @return MultiPartFile
     * @throws IOException exception
     */
    private MultipartFile fileToMultipartFile(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
    }
}