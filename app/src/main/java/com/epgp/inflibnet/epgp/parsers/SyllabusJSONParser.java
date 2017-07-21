package com.epgp.inflibnet.epgp.parsers;

import com.epgp.inflibnet.epgp.model.SyllabusforJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 07-07-2015.
 */
public class SyllabusJSONParser {

    public static List<SyllabusforJSON> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<SyllabusforJSON> syllabusList = new ArrayList<SyllabusforJSON>();

            for (int i = 0 ; i<ar.length(); i++){
                JSONObject obj = ar.getJSONObject(i);
                SyllabusforJSON syll = new SyllabusforJSON();

                syll.setSyl_id(obj.getString("syl_id"));
                syll.setLinkfile(obj.getString("linkfile"));
                syll.setLinkfileext(obj.getString("linkfileext"));
                syll.setCat_name(obj.getString("cat_name"));
                syll.setMcatid(obj.getString("mcatid"));
                syll.setMpcatid(obj.getString("mpcatid"));

                syllabusList.add(syll);
            }

            return syllabusList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
