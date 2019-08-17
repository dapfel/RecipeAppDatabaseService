package DatabaseAccess;


public class ProfilePic {
    
    private byte[] picture;

    public ProfilePic(byte[] picture) {
        this.picture = picture;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
    
}
