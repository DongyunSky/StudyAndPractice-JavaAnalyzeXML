package fun.prodev.xml;

/**
 * @author Wing Gao
 * @date 2019/1/22 13:12
 * @description
 */
public class Book {
    private String id;
    private String name;
    private String author;
    private String year;
    private String price;
    private String language;

    public Book() {
    }

    public Book(String id, String name, String author, String year, String price, String language) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.price = price;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
