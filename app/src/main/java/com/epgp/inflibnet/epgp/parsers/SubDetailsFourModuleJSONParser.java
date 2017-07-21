package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.SubDetailsFourModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 13-07-2015.
 */
public class SubDetailsFourModuleJSONParser {
    public static List<SubDetailsFourModule> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<SubDetailsFourModule> subDetailsFourModuleList = new ArrayList<SubDetailsFourModule>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                SubDetailsFourModule subDetailsFourModule = new SubDetailsFourModule();

//                subject.setId(obj.getString("id"));
//                subject.setPi_name(obj.getString("pi_name"));
//                subject.setDesignation_affilation(obj.getString("designation_affilation"));
//                subject.setAnchor_institute(obj.getString("anchor_institute"));
//                subject.setSubject(obj.getString("subject"));
//                subject.setEmailid(obj.getString("emailid"));
//                subject.setPhone_number(obj.getString("phone_number"));
//                subject.setStatic_module(obj.getString("static_module"));
//                subject.setFour_module(obj.getString("four_module"));

                subDetailsFourModule.setCat_name(obj.getString("cat_name"));
                subDetailsFourModule.setTotal(obj.getString("total"));
                subDetailsFourModule.setSubject_id(obj.getString("subject_id"));



                subDetailsFourModuleList.add(subDetailsFourModule);
            }

            return subDetailsFourModuleList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
