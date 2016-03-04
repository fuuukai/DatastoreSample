package com.nifty.cloud.mb.datastoresample;

import android.widget.ImageView;

/**
 * Created by fukai on 16/03/03.
 */
public class Team {

    String team_name;
    String location;
    String sex;
    String level;


    public Team(String team_name, String location, String sex, String level){
        this.team_name = team_name;
        this.location = location;
        this.sex = sex;
        this.level = level;
    }
}
