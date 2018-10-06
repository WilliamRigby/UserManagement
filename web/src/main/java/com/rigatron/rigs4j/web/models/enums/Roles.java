package com.rigatron.rigs4j.web.models.enums;

public enum Roles {

    REGULARUSER(1) {
        public String toString() {
            return "RegularUser";
        }
    },
    ADMIN(2) {
        public String toString() {
            return "Administrator";
        }
    };

    private int id;
    Roles(int value) {

        this.id = value;
    }

    public int getValue() {
        return this.id;
    }
}
