package entity;

// 图书实体类
public class Book {

    private String isbn, name, author, press;
    private int classId, count, sum;

    public Book(String isbn, String name, String author, String press, int classId, int count, int sum) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.press = press;
        this.classId = classId;
        this.count = count;
        this.sum = sum;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

}
