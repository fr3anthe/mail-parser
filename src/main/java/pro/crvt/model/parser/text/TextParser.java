package pro.crvt.model.parser.text;

import com.auxilii.msgparser.Message;
import com.auxilii.msgparser.MsgParser;
import org.springframework.web.multipart.MultipartFile;
import pro.crvt.model.entities.Contact;
import pro.crvt.model.parser.BaseParser;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class TextParser.
 *
 * @author ifedorenko
 * @since 24.03.2019
 */
public class TextParser extends BaseParser {
    private static final String REG_NUMBER = "[7]*[0-9]{10}";
    private static final String REG_MAIL = "[^ ][a-zA-Z0-9-.]+@[a-zA-Z0-9-.]+[a-zA-Z]";
    private static final String REG_NAME = "[^ ][а-яА-Яa-zA-Z ]+[а-яА-Яa-zA-Z]";
    private final String name;
    private final String company;
    private final String email;
    private final String number;

    /**
     * Constructor.
     * @param contact contact
     */
    public TextParser(Contact contact) {
        super(contact);
        this.name = this.contact.getName();
        this.company = this.contact.getCompany();
        this.email = this.contact.getEmail();
        this.number = this.contact.getNumber();
    }

    @Override
    public Contact parse(MultipartFile file) {
        String body = this.getBody(file);
        for (String s : body.split(System.lineSeparator())) {
            if (s.contains(name)) {
              this.contact.setName(this.getValue(s, REG_NAME));
            } else if (s.contains(company)) {
                this.contact.setCompany(this.getValue(s, REG_NAME));
            } else if (s.contains(email)) {
                this.contact.setEmail(this.getValue(s, REG_MAIL));
            } else if (s.contains(number)) {
                this.contact.setNumber(this.getValue(s, REG_NUMBER));
            }
        }
        return contact;
    }

    /**
     * Method getBody.
     * @param file InputStream
     * @return message body
     */
    private String getBody(MultipartFile file) {
        MsgParser parser = new MsgParser();
        Message message = null;
        try {
            message = parser.parseMsg(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message.getBodyText();
    }

    /**
     * Method getValue.
     * @param str str
     * @param regex regex
     * @return value from str
     */
    private String getValue(String str, String regex) {
        str = str.substring(str.lastIndexOf(":") + 1);
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            result = str.substring(matcher.start(), matcher.end());
        }
        return result;
    }
}


