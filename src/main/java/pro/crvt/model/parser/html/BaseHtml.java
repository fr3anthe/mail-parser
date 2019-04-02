package pro.crvt.model.parser.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.multipart.MultipartFile;
import pro.crvt.model.entities.Contact;
import pro.crvt.model.parser.BaseParser;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseHtml extends BaseParser {
    protected static final int COL_SIZE = 2;
    protected final Map<String, String> map = new HashMap<>();

    /**
     * Constructor.
     * @param contact contact
     */
    public BaseHtml(Contact contact) {
        super(contact);
        map.put(this.contact.getCompany(), "");
        map.put(this.contact.getName(), "");
        map.put(this.contact.getEmail(), "");
        map.put(this.contact.getNumber(), "");
    }

    /**
     * Method getTable.
     * @param file MultipartFile
     * @return element table
     */
    public Element loadTable(MultipartFile file) {
        Document document = null;
        try {
            document = Jsoup.parse(file.getInputStream(), "utf-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document.select("table").last();
    }

    /**
     * Method getResultParse.
     * @return result
     */
    public Contact getResultParse() {
        this.contact.setNumber(map.get(this.contact.getNumber()));
        this.contact.setCompany(map.get(this.contact.getCompany()));
        this.contact.setEmail(map.get(this.contact.getEmail()));
        this.contact.setName(map.get(this.contact.getName()));
        return this.contact;
    }
}
