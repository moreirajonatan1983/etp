package org.utn.frd.dds.etp.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author jonatan.moreira
 *
 */
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;

    @Column(name="code", nullable=false, length=50)
    private String code;

}
