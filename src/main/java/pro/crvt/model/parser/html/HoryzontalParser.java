package pro.crvt.model.parser.html;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.multipart.MultipartFile;
import pro.crvt.model.entities.Contact;
import pro.crvt.model.error.UnsupportedMediaTypeException;

/**
 * class HoryzontalParser
 *
 * @author ifedorenko
 * @since 27.03.2019
 */
public class HoryzontalParser extends BaseHtml {
    /**
     * Constructor.
     * @param contact contact
     */
    public HoryzontalParser(Contact contact) {
        super(contact);
    }

    @Override
    public Contact parse(MultipartFile file) {
        Element table = this.loadTable(file);
        if (table == null) {
            throw new UnsupportedMediaTypeException("Wrong format. Need text/html");
        }
        Elements rows = table.select("tr");
        if (rows.first().select("td").size() <= COL_SIZE) {
            throw new UnsupportedMediaTypeException("Wrong type. Need horyzontal type");
        }
        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            for (int j = 0; j < cols.size(); j++) {
                if (this.map.containsKey(cols.get(j).text())) {
                    String value = rows.get(i + 1).select("td").get(j).text();
                    this.map.put(cols.get(j).text(), value);
                }
            }
        }
        return this.getResultParse();
    }
}
