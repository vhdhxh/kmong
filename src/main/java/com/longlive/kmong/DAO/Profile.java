package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.ProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface Profile {
    public void insertProfile(long user_id);
    ProfileDTO selectProfile(long user_id);
    void updateName(Map<String,Object> map);
    void updateService(Map<String,Object> map);
    void updateIntroduce(Map<String,Object> map);
    void updateAddress(Map<String,Object> map);
    void updateTime(Map<String,Object> map);
    void updateDeployee(Map<String,Object> map);
    void updateDetail(Map<String,Object> map);

    String getProfileId(String boardId);
}
