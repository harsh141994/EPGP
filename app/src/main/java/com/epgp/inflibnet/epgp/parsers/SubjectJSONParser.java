package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 02-07-2015.
 */
public class SubjectJSONParser {

    public static List<Subject> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<Subject> subjectList = new ArrayList<Subject>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                Subject subject = new Subject();

//                subject.setId(obj.getString("id"));
//                subject.setPi_name(obj.getString("pi_name"));
//                subject.setDesignation_affilation(obj.getString("designation_affilation"));
//                subject.setAnchor_institute(obj.getString("anchor_institute"));
//                subject.setSubject(obj.getString("subject"));
//                subject.setEmailid(obj.getString("emailid"));
//                subject.setPhone_number(obj.getString("phone_number"));
//                subject.setStatic_module(obj.getString("static_module"));
//                subject.setFour_module(obj.getString("four_module"));

                subject.setCat_name(obj.getString("cat_name"));
                subject.setCsrno(obj.getString("csrno"));


                subjectList.add(subject);
            }

            return subjectList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
