package com.tss.library;

public enum BookCategory {
    SCI_FI("Science Fiction"),
    MYSTERY("Mystery"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    HISTORY("History"),
    SELF_HELP("Self Help"),
    BIOGRAPHY("Biography"),
    CULINARY("Culinary"),
    MARINE_SCI("Marine Science"),
    PHILOSOPHY("Philosophy");

    private String value;
    BookCategory(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
