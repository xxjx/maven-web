package DB;
public class Item {
    private String title;
    private String content;
    private String url;
    private String ID;

    public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Item(String title, String content, String url,String ID) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.ID = ID;
    }

    public String toString(){
        return "title:"+title+"\ncontent:"+content+"\nurl:"+url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
