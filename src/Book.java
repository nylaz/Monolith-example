public class Book {

    private String title;
    private String cat;
    public Book(String title, String cat){
        this.title=title;
        this.cat=cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String toString(){
        return getTitle() + " : <"+ getCat() + ">";
    }
}
