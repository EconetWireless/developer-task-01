package com.econetwireless.epay.domain;

import javax.persistence.*;

/**
 * Created by tnyamakura on 18/3/2017.
 */
@Entity
@Table(name = "e_req_partner")
public class RequestPartner {
    @Id
    private Long id;
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 30, unique = true)
    private String code;
    @Column(length = 50)
    private String name;
    @Column(length = 125)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestPartner{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
