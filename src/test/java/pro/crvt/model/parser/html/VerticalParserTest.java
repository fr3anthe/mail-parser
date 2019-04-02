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

public class VerticalParserTest {
    private final String text = "2";
    private final String msg = "vertical.msg";
    private final String vert = "company=ООО Кедр Сибири, name=Евгений, number=+79230009222, email=kedrsibiri22@mail.ru";

    /**
     * Test vertical type msg.
     * @throws IOException exception
     */
    @Test
    public void testVerticalParse() throws IOException {
        File file = new File(ClassLoader.getSystemResource(msg).getFile());
        Contact contact = Configuration.loadContactParam(text);
        MailParser parser = new VerticalParser(contact);
        contact = parser.parse(fileToMultipartFile(file));
        assertThat(vert, is(contact.toString()));
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