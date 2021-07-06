package app.myapp.myhotel.Model;

import java.util.Comparator;

public class InfoHotelUser {


    private int id;
    private String namehotel;
    private String evaluation;
    private String country;
    private String city;
    private String manger;
    private String number;
    private String email;
    private String latitude;
    private String longtude;
    private String image1;
    private String image2;
    private String image3;
    private String ev;
    private int admin_id;





    public InfoHotelUser() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamehotel() {
        return namehotel;
    }

    public void setNamehotel(String namehotel) {
        this.namehotel = namehotel;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getManger() {
        return manger;
    }

    public void setManger(String manger) {
        this.manger = manger;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtude() {
        return longtude;
    }

    public void setLongtude(String longtude) {
        this.longtude = longtude;
    }


    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }


    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }


    public String getEv() {
        return ev;
    }

    public void setEv(String ev) {
        this.ev = ev;
    }

//    public static Comparator<InfoHotelUser> comparator=new Comparator<InfoHotelUser>() {
//        @Override
//        public int compare(InfoHotelUser o1, InfoHotelUser o2) {
//
//
//            return o2.getEvaluation().compareTo(o1.getEvaluation());
//        }
//    };








    public static Comparator<InfoHotelUser> comparator2=new Comparator<InfoHotelUser>() {
        @Override
        public int compare(InfoHotelUser o1, InfoHotelUser o2) {


            return o1.getNamehotel().compareTo(o2.getNamehotel());
        }
    };









    public static Comparator<InfoHotelUser> comparator3=new Comparator<InfoHotelUser>() {
        @Override
        public int compare(InfoHotelUser o1, InfoHotelUser o2) {


            return o2.getEv().compareTo(o1.getEv());
        }
    };
}
