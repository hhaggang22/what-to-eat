package com.example.whattoeat_for_sungshin;

public class Menu {
    String name;
    String hashtag;

    public Menu(String name, String hashtag) {
        this.name = name;
        this.hashtag = hashtag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
