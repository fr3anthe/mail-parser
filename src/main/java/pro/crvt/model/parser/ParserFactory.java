package pro.crvt.model.parser;

import pro.crvt.model.entities.Contact;
import pro.crvt.model.configure.Configuration;
import pro.crvt.model.error.UnsupportedMediaTypeException;
import pro.crvt.model.parser.html.HoryzontalParser;
import pro.crvt.model.parser.html.VerticalParser;
import pro.crvt.model.parser.text.TextParser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class ParserFactory.
 *
 * @author ifedorenko
 * @since 24.03.2019
 */
public class ParserFactory {
    private static Map<String, Function<Contact, MailParser>> map = new HashMap<>();
    private static final String TYPE = "type";
    private static final String CONTACT = "contact";
    private static final String HORYZONTAL = "horyzontal";
    private static final String VERTICAL = "vertical";
    private static final String TEXT = "text";

     static {
        map.put(HORYZONTAL, contact -> new HoryzontalParser(contact));
        map.put(VERTICAL, contact -> new VerticalParser(contact));
        map.put(TEXT, contact -> new TextParser(contact));
    }

    /**
     * Method getParser
     * @param contentType contentType
     * @return parser
     */
    public static MailParser createParser(final String contentType) {
        Contact contact = Configuration.loadContactParam(contentType);
        if (contact == null) {
            throw new UnsupportedMediaTypeException("Unsupported type");
        }
        return map.get(contact.getParserType()).apply(contact);
    }
}
