package pro.crvt.model.parser;

import pro.crvt.model.Contact;

/**
 * abstract class BaseParser
 *
 * @author ifedorenko
 * @since 27.03.2019
 */
public abstract class BaseParser implements MailParser {
    protected final Contact contact;

    /**
     * Constructor.
     * @param contact contact
     */
    public BaseParser(Contact contact) {
        this.contact = contact;
    }
}
