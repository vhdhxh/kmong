package com.longlive.kmong.service;

import com.longlive.kmong.DAO.Profile;
import com.longlive.kmong.DAO.User;
import com.longlive.kmong.DTO.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private final Profile profile;

    public void insertProfile(long user_id) {
        profile.insertProfile(user_id);
    }

    public ProfileDTO selectProfile(long user_id){

        return profile.selectProfile(user_id);
    }
    public void updateName(Map<String,Object> map) {
        profile.updateName(map);
    }
    public void updateService(Map<String,Object> map) {
        profile.updateService(map);
    }
    public void updateIntroduce(Map<String,Object> map) {
          profile.updateIntroduce(map);
    }

    public void updateAddress(Map<String,Object> map) {
        profile.updateAddress(map);
    }
    public void updateTime(Map<String,Object> map) {
        profile.updateTime(map);
    }

    public void updateDeployee(Map<String,Object> map) {
        profile.updateDeployee(map);
    }
    public void updateDetail(Map<String,Object> map) {
        profile.updateDetail(map);
    }
}
