package pro.crvt.model.parser;

import pro.crvt.model.Contact;
import pro.crvt.model.configure.Configuration;
import pro.crvt.model.error.UnsupportedMediaTypeException;
import pro.crvt.model.parser.html.HoryzontalParser;
import pro.crvt.model.parser.html.VerticalParser;
import pro.crvt.model.parser.text.TextParser;

/**
 * Class ParserFactory.
 *
 * @author ifedorenko
 * @since 24.03.2019
 */
public class ParserFactory {
    private static final String HORYZONTAL = "1";
    private static final String VERTICAL = "2";
    private static final String TEXT = "3";

    /**
     * Method getParser
     * @param contentType contentType
     * @return parser
     */
    public static MailParser createParser(final String contentType) {
        MailParser parser = null;
        Contact contact = Configuration.loadContactParam(contentType);
        if (contentType.equals(HORYZONTAL)) {
            parser = new HoryzontalParser(contact);
        } else if (contentType.equals(VERTICAL)) {
            parser = new VerticalParser(contact);
        } else if (contentType.equals(TEXT)) {
            parser = new TextParser(contact);
        } else {
            throw new UnsupportedMediaTypeException("Unsupported type");
        }
        return parser;
    }
}
