package com.sorb.mybatisapp.model;

import lombok.Data;

@Data
public class Region {

    int id;
    String fullName;
    String shortName;

    public Region trimFields() {
        this.setFullName(this.getFullName().trim());
        this.setShortName(this.getShortName().trim());
        return this;
    }

    public boolean hasAllFilledFields() {
        return !this.getFullName().trim().isEmpty() && !this.getShortName().trim().isEmpty();
    }
}
