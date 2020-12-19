package com.beifeng.service.impl;

import com.beifeng.dao.FriendLinkMapper;
import com.beifeng.domain.FriendLink;
import com.beifeng.service.FriendLinkService;
import com.beifeng.util.DateTimeUtil;
import com.beifeng.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/29 21:22
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public List<FriendLink> getFriends() {

        List<FriendLink> friendLinks = friendLinkMapper.getFriendList();
        return friendLinks;
    }

    @Transactional
    @Override
    public String saveFriend(FriendLink friendLink) {
        String msg = "添加失败";

        friendLink.setId(UUIDUtil.getUUID());
        friendLink.setCreateTime(DateTimeUtil.getSysTime());

        Integer count = friendLinkMapper.saveFriend(friendLink);
        if (count==1){
            msg = "添加成功";
        }

        return msg;
    }

    @Override
    public FriendLink getFriendById(String id) {

        FriendLink friend = friendLinkMapper.getFriendById(id);

        return friend;
    }

    @Transactional
    @Override
    public String updateFriendById(FriendLink friendLink) {
        String msg = "更新失败";

        Integer count = friendLinkMapper.updateFriendById(friendLink);
        if (count==1){
            msg = "更新成功";
        }

        return msg;
    }

    @Transactional
    @Override
    public String deleteFriendById(String id) {
        String msg = "删除失败";

        Integer count = friendLinkMapper.deleteFriendById(id);
        if (count==1){
            msg = "删除成功";
        }

        return msg;
    }

    @Override
    public List<FriendLink> getFriendsByASC() {

        List<FriendLink> Friends = friendLinkMapper.getFriendsByASC();

        return Friends;
    }
}







