package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.TeamContentWriters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 09-07-2015.
 */
public class TeamContentWritersJSONParser {

    public static List<TeamContentWriters> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<TeamContentWriters> teamContentWritersList = new ArrayList<TeamContentWriters>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                TeamContentWriters teamContentWriters = new TeamContentWriters();

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

                teamContentWriters.setCwname(obj.getString("cwname"));
                teamContentWriters.setFname(obj.getString("fname"));
                teamContentWriters.setMname(obj.getString("mname"));
                teamContentWriters.setLname(obj.getString("lname"));
                teamContentWriters.setMcatid(obj.getString("mcatid"));
                teamContentWriters.setCat_name(obj.getString("cat_name"));
 //               teamContentWriters.setR_subject(obj.getString("r_subject"));





                teamContentWritersList.add(teamContentWriters);
            }

            return teamContentWritersList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
