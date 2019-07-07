/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseAccess;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Comment.class)
public class CommentList extends ArrayList<Comment> {
       private static final long serialVersionUID = 1L;
       public CommentList() {
        super();
    }
    public CommentList(List<Comment> c) {
        super(c);
    }
    @XmlElement(name = "comment")
    public List<Comment> getComments() {
        return this;
    }
    public void setComments(List<Comment> comments) {
        this.addAll(comments);
    }
}
