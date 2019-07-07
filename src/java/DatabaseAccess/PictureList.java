
package DatabaseAccess;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
public class PictureList extends ArrayList<byte[]> {
       private static final long serialVersionUID = 1L;
       public PictureList() {
        super();
    }
    public PictureList(List<byte[]> c) {
        super(c);
    }
    @XmlElement(name = "picture")
    public List<byte[]> getpicture() {
        return this;
    }
    public void setComments(List<byte[]> pictures) {
        this.addAll(pictures);
    }
}
