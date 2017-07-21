package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.StaticModuleDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 13-07-2015.
 */
public class StaticModuleDetailJSONParser {

    public static List<StaticModuleDetail> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<StaticModuleDetail> staticModuleDetailList = new ArrayList<StaticModuleDetail>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                StaticModuleDetail staticModuleDetail = new StaticModuleDetail();

//                subject.setId(obj.getString("id"));
//                subject.setPi_name(obj.getString("pi_name"));
//                subject.setDesignation_affilation(obj.getString("designation_affilation"));
//                subject.setAnchor_institute(obj.getString("anchor_institute"));
//                subject.setSubject(obj.getString("subject"));
//                subject.setEmailid(obj.getString("emailid"));
//                subject.setPhone_number(obj.getString("phone_number"));
//                subject.setStatic_module(obj.getString("static_module"));
//                subject.setFour_module(obj.getString("four_module"));

                staticModuleDetail.setCat_name(obj.getString("cat_name"));
                staticModuleDetail.setTotal(obj.getString("total"));
                staticModuleDetail.setMcatid(obj.getString("mcatid"));
                staticModuleDetail.setCat_parent(obj.getString("cat_parent"));


                staticModuleDetailList.add(staticModuleDetail);
            }

            return staticModuleDetailList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
