package DatabaseAccess;

import java.io.Serializable;

public class Comment implements Serializable {
    
    private String author;
    private String comment; 
    private String authorName;

    public Comment() {
    }

    public Comment(String author, String comment, String authorName) {
        this.author = author;
        this.comment = comment;
        this.authorName = authorName;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
