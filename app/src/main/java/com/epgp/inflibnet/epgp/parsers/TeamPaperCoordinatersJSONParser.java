package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.TeamPaperCoordinaters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 09-07-2015.
 */
public class TeamPaperCoordinatersJSONParser {

    public static List<TeamPaperCoordinaters> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<TeamPaperCoordinaters> teamPaperCoordinatersList = new ArrayList<TeamPaperCoordinaters>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                TeamPaperCoordinaters teamPaperCoordinaters = new TeamPaperCoordinaters();

//                subject.setId(obj.getString("id"));
//                subject.setPi_name(obj.getString("pi_name"));
//                subject.setDesignation_affilation(obj.getString("designation_affilation"));
//                subject.setAnchor_institute(obj.getString("anchor_institute"));
//                subject.setSubject(obj.getString("subject"));
//                subject.setEmailid(obj.getString("emailid"));
//                subject.setPhone_number(obj.getString("phone_number"));
//                subject.setStatic_module(obj.getString("static_module"));
//                subject.setFour_module(obj.getString("four_module"));

//                subject.setCat_name(obj.getString("cat_name"));
//                subject.setCsrno(obj.getString("csrno"));

                teamPaperCoordinaters.setCat_name(obj.getString("cat_name"));
                teamPaperCoordinaters.setFname(obj.getString("fname"));
                teamPaperCoordinaters.setMname(obj.getString("mname"));
                teamPaperCoordinaters.setLname(obj.getString("lname"));
                teamPaperCoordinaters.setCsrno(obj.getString("csrno"));
                teamPaperCoordinaters.setR_paper(obj.getString("r_paper"));
                teamPaperCoordinaters.setR_subject(obj.getString("r_subject"));





                teamPaperCoordinatersList.add(teamPaperCoordinaters);
            }

            return teamPaperCoordinatersList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
