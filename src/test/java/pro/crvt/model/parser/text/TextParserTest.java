package pro.crvt.model.parser.text;

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

public class TextParserTest {
    private final String text = "3";
    private final String msg1 = "text1.msg";
    private final String msg2 = "text2.msg";
    private final String text1 = "company=Дмитрий, name=Дмитрий, number=9124467370, email=zavarzindv@mail.ru";
    private final String text2 = "company=Евровидение, name=Дмитрий Билан Евгеньевич, number=9011234567, email=zavarzindv@yahoo.ru";


    /**
     * Test text1 type msg.
     * @throws IOException exception
     */
    @Test
    public void testText1parse() throws IOException {
        File file = new File(ClassLoader.getSystemResource(msg1).getFile());
        Contact contact = Configuration.loadContactParam(text);
        MailParser parser = new TextParser(contact);
        contact = parser.parse(fileToMultipartFile(file));
        assertThat(text1, is(contact.toString()));
    }

    /**
     * Test text2 type msg.
     * @throws IOException exception
     */
    @Test
    public void testText2parse() throws IOException {
        File file = new File(ClassLoader.getSystemResource(msg2).getFile());
        Contact contact = Configuration.loadContactParam(text);
        MailParser parser = new TextParser(contact);
        contact = parser.parse(fileToMultipartFile(file));
        assertThat(text2, is(contact.toString()));
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