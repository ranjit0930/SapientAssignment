package com.sapient.SapientAssignment.model;

import java.io.Serializable;

public class Players implements Serializable {

    private String player_key;
    private String player_name;
    private String player_number;
    private String player_country;
    private String player_type;
    private String player_age;
    private String player_match_played;
    private String player_goals;
    private String player_yellow_cards;
    private String player_red_cards;

    public String getPlayer_key() {
        return player_key;
    }

    public void setPlayer_key(String player_key) {
        this.player_key = player_key;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_number() {
        return player_number;
    }

    public void setPlayer_number(String player_number) {
        this.player_number = player_number;
    }

    public String getPlayer_country() {
        return player_country;
    }

    public void setPlayer_country(String player_country) {
        this.player_country = player_country;
    }

    public String getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(String player_type) {
        this.player_type = player_type;
    }

    public String getPlayer_age() {
        return player_age;
    }

    public void setPlayer_age(String player_age) {
        this.player_age = player_age;
    }

    public String getPlayer_match_played() {
        return player_match_played;
    }

    public void setPlayer_match_played(String player_match_played) {
        this.player_match_played = player_match_played;
    }

    public String getPlayer_goals() {
        return player_goals;
    }

    public void setPlayer_goals(String player_goals) {
        this.player_goals = player_goals;
    }

    public String getPlayer_yellow_cards() {
        return player_yellow_cards;
    }

    public void setPlayer_yellow_cards(String player_yellow_cards) {
        this.player_yellow_cards = player_yellow_cards;
    }

    public String getPlayer_red_cards() {
        return player_red_cards;
    }

    public void setPlayer_red_cards(String player_red_cards) {
        this.player_red_cards = player_red_cards;
    }
}
