package org.utn.frd.dds.etp.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="locals")
public class Local {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;

    @OneToOne(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private City city;

    @Column(name="Address", nullable=true, length=50)
    private String address;

    @Column(name="AddressNo", nullable=true, length=50)
    private String addressNo;

    @OneToOne(mappedBy = "commerce_uuid", cascade = CascadeType.ALL, orphanRemoval = true)
    private Commerce commerce;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNo() {
        return addressNo;
    }

    public void setAddressNo(String addressNo) {
        this.addressNo = addressNo;
    }

    public Commerce getCommerce() {
        return commerce;
    }

    public void setCommerce(Commerce commerce) {
        this.commerce = commerce;
    }

    @Override
    public String toString() {
        return "Local{" +
                "uuid='" + uuid + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", addressNo='" + addressNo + '\'' +
                ", commerce=" + commerce +
                '}';
    }

}
