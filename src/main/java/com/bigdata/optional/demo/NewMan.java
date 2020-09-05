package com.bigdata.optional.demo;

import java.util.Optional;

public class NewMan {

    private Optional<Godness> godness = Optional.empty();

    public NewMan() {
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public Optional<Godness> getGodness() {
        return godness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }
}
