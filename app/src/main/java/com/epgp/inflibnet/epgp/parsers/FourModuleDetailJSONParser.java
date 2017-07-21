package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.FourModuleDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 13-07-2015.
 */
public class FourModuleDetailJSONParser {

    public static List<FourModuleDetail> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<FourModuleDetail> fourModuleDetailList = new ArrayList<FourModuleDetail>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                FourModuleDetail fourModuleDetail = new FourModuleDetail();

//                subject.setId(obj.getString("id"));
//                subject.setPi_name(obj.getString("pi_name"));
//                subject.setDesignation_affilation(obj.getString("designation_affilation"));
//                subject.setAnchor_institute(obj.getString("anchor_institute"));
//                subject.setSubject(obj.getString("subject"));
//                subject.setEmailid(obj.getString("emailid"));
//                subject.setPhone_number(obj.getString("phone_number"));
//                subject.setFour_module(obj.getString("four_module"));
//                subject.setFour_module(obj.getString("four_module"));

                fourModuleDetail.setCat_name(obj.getString("cat_name"));
                fourModuleDetail.setTotal(obj.getString("total"));
                fourModuleDetail.setMcatid(obj.getString("mcatid"));
                fourModuleDetail.setCat_parent(obj.getString("cat_parent"));


                fourModuleDetailList.add(fourModuleDetail);
            }

            return fourModuleDetailList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
