package pro.crvt.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


public class Contact implements Serializable {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String parserType;
    private String company;
    private String name;
    private String number;
    private String email;

    /**
     * Constructor.
     */
    public Contact() {
    }

    /**
     * Constrcutor.
     * @param company company
     * @param name name
     * @param number number
     * @param email email
     */
    public Contact(String company, String name, String number, String email) {
        this.company = company;
        this.name = name;
        this.number = number;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contact contact = (Contact) o;

        if (company != null ? !company.equals(contact.company) : contact.company != null) {
            return false;
        }
        if (name != null ? !name.equals(contact.name) : contact.name != null) {
            return false;
        }
        if (number != null ? !number.equals(contact.number) : contact.number != null) {
            return false;
        }
        return email != null ? email.equals(contact.email) : contact.email == null;
    }

    @Override
    public int hashCode() {
        int result = company != null ? company.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParserType() {
        return parserType;
    }

    public void setParserType(String parserType) {
        this.parserType = parserType;
    }

    @Override
    public String toString() {
        return "company=" + company
                + ", name=" + name
                + ", number=" + number
                + ", email=" + email;
    }
}
