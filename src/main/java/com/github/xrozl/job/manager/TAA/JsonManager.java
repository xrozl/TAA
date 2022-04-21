package com.github.xrozl.job.manager.TAA;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;

public class JsonManager {

    public boolean newUser(String userid, String fullname, String password) {
        if (existsUser(userid)) return false;
        JSONObject json = new JSONObject();
        json.put("userid", userid);
        json.put("fullname", fullname);
        json.put("password", password);
        return saveToFile(userid, json);
    }

    public boolean saveToFile(String userid, JSONObject json) {
        File f = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + userid + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(f.toPath())) {
            json.write(writer);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean existsUser(String userid) {
        return new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + userid + ".json").exists();
    }
}
