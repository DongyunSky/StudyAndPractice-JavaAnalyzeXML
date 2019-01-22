package fun.prodev.xml.sax;

import fun.prodev.xml.Book;
import fun.prodev.xml.Config;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Wing Gao
 * @date 2019/1/22 13:09
 * @description
 */
public class SAXParserHandler extends DefaultHandler {

    String value = null;
    Book book = null;
    private ArrayList<Book> bookList = new ArrayList<>();
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    int bookIndex = 0;

    /**
     * 用来标识解析开始
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("SAX解析开始");
    }

    /**
     * 用来标识解析结束
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("SAX解析结束");
    }

    /**
     * 解析xml元素
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        //调用DefaultHandler类的startElement方法
        super.startElement(uri, localName, qName, attributes);
        if ("book".equals(qName)) {
            bookIndex++;
            //创建一个book对象
            book = new Book();
            //开始解析book元素的属性
            System.out.println("======================开始遍历某一本书的内容=================");
            //不知道book元素下属性的名称以及个数，如何获取属性名以及属性值
            int num = attributes.getLength();
            for(int i = 0; i < num; i++){
                System.out.print("book元素的第" + (i + 1) +  "个属性名是："
                        + attributes.getQName(i));
                System.out.println("---属性值是：" + attributes.getValue(i));
                if ("id".equals(attributes.getQName(i))) {
                    book.setId(attributes.getValue(i));
                }
            }
        }
        else if (!"name".equals(qName) && !"bookstore".equals(qName)) {
            System.out.print("节点名是：" + qName + "---");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        //调用DefaultHandler类的endElement方法
        super.endElement(uri, localName, qName);
        //判断是否针对一本书已经遍历结束
        if ("book".equals(qName)) {
            bookList.add(book);
            book = null;
            System.out.println("======================结束遍历某一本书的内容=================");
        }
        else if ("name".equals(qName)) {
            book.setName(value);
        }
        else if ("author".equals(qName)) {
            book.setAuthor(value);
        }
        else if ("year".equals(qName)) {
            book.setYear(value);
        }
        else if ("price".equals(qName)) {
            book.setPrice(value);
        }
        else if ("language".equals(qName)) {
            book.setLanguage(value);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        value = new String(ch, start, length);
        if (!"".equals(value.trim())) {
            System.out.println("节点值是：" + value);
        }
    }

    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            SAXParserHandler handler = new SAXParserHandler();
            parser.parse(Config.XML_DATA_FILE_URI, handler);
            System.out.println("共有" + handler.getBookList().size() + "本书");
            for (Book book : handler.getBookList()) {
                System.out.println(book.getId());
                System.out.println(book.getName());
                System.out.println(book.getAuthor());
                System.out.println(book.getYear());
                System.out.println(book.getPrice());
                System.out.println(book.getLanguage());
                System.out.println("----finish----");
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
