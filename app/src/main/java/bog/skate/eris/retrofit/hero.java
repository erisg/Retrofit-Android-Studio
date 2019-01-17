package bog.skate.eris.retrofit;

public class hero {

    private String realname;
    private String imageurl;

    public hero(String realname, String imageurl) {
        this.realname = realname;
        this.imageurl = imageurl;
    }

    public String getRealname() {
        return realname;
    }

    public String getImageurl() {
        return imageurl;
    }
}
