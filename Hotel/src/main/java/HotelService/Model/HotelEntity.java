package HotelService.Model;

import jakarta.persistence.*;

@Entity

@Table(name="hotel")
public class HotelEntity {
    @Id
    @Column(name = "hotel_id")
    int id;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "province")
    private String province;


    @Column(name = "available_packages")
    private int available_packages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getAvailable_packages() {
        return available_packages;
    }

    public void setAvailable_packages(int available_packages) {
        this.available_packages = available_packages;
    }
}
