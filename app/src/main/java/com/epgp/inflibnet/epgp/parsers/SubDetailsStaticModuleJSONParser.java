package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.SubDetailsStaticModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 13-07-2015.
 */
public class SubDetailsStaticModuleJSONParser {
    public static List<SubDetailsStaticModule> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<SubDetailsStaticModule> subDetailsStaticModuleList = new ArrayList<SubDetailsStaticModule>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                SubDetailsStaticModule subDetailsStaticModule = new SubDetailsStaticModule();

//                subject.setId(obj.getString("id"));
//                subject.setPi_name(obj.getString("pi_name"));
//                subject.setDesignation_affilation(obj.getString("designation_affilation"));
//                subject.setAnchor_institute(obj.getString("anchor_institute"));
//                subject.setSubject(obj.getString("subject"));
//                subject.setEmailid(obj.getString("emailid"));
//                subject.setPhone_number(obj.getString("phone_number"));
//                subject.setStatic_module(obj.getString("static_module"));
//                subject.setFour_module(obj.getString("four_module"));

                subDetailsStaticModule.setCat_name(obj.getString("cat_name"));
                subDetailsStaticModule.setTotal(obj.getString("total"));
                subDetailsStaticModule.setSubject_id(obj.getString("subject_id"));



                subDetailsStaticModuleList.add(subDetailsStaticModule);
            }

            return subDetailsStaticModuleList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
