package com.rigatron.rigs4j.BL.entities.enums;

public enum Roles {

    REGULARUSER(1) {
        public String toString() {
            return "ROLE_USER";
        }
    },
    ADMIN(2) {
        public String toString() {
            return "ROLE_ADMIN";
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

