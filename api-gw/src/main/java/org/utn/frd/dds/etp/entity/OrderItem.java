package org.utn.frd.dds.etp.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author jonatan.moreira
 *
 */
@Entity
@Table(name="order_items")
public class OrderItem {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;

    @OneToOne(mappedBy = "product_uuid", cascade = CascadeType.ALL, orphanRemoval = true)
    private Product product;

    @Column(name="presentation")
    private Integer presentation;

    @Column(name="count")
    private Integer count;

    @OneToOne(mappedBy = "order_uuid", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPresentation() {
        return presentation;
    }

    public void setPresentation(Integer presentation) {
        this.presentation = presentation;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "uuid='" + uuid + '\'' +
                ", product=" + product +
                ", presentation=" + presentation +
                ", count=" + count +
                ", order=" + order +
                '}';
    }

}
