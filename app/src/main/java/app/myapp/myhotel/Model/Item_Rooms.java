package app.myapp.myhotel.Model;

public class Item_Rooms {

    private int id;
    private String name_room;
    private String type_room;
    private String price_room;
    private String image;
    private String enable;
    private String show;


    public Item_Rooms() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_room() {
        return name_room;
    }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    public String getType_room() {
        return type_room;
    }

    public void setType_room(String type_room) {
        this.type_room = type_room;
    }

    public String getPrice_room() {
        return price_room;
    }

    public void setPrice_room(String price_room) {
        this.price_room = price_room;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }


    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
}
