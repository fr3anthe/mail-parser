package pro.crvt.model.parser.html;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.multipart.MultipartFile;
import pro.crvt.model.entities.Contact;
import pro.crvt.model.error.UnsupportedMediaTypeException;

/**
 * class VerticalParser.
 *
 * @author ifedorenko
 * @since 27.03.2019
 */
public class VerticalParser extends BaseHtml {
    /**
     * Constructor.
     * @param contact contact
     */
    public VerticalParser(Contact contact) {
        super(contact);
    }

    @Override
    public Contact parse(MultipartFile file) {
        Element table = this.loadTable(file);
        if (table == null) {
            throw new UnsupportedMediaTypeException("Wrong format. Need text/html");
        }
        Elements rows = table.select("tr");
        if (rows.first().select("td").size() != COL_SIZE) {
            throw new UnsupportedMediaTypeException("Wrong type. Need vertical type");
        }
        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            this.map.computeIfPresent(cols.get(0).text(), (k, v) -> cols.get(1).text());
        }
        return this.getResultParse();
    }
}
