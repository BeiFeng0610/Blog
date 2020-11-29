package com.beifeng.service;

import com.beifeng.domain.FriendLink;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/29 21:14
 */
public interface FriendLinkService {

    List<FriendLink> getFriends();

    String saveFriend(FriendLink friendLink);

    FriendLink getFriendById(String id);

    String updateFriendById(FriendLink friendLink);

    String deleteFriendById(String id);

    /*根据添加时间展示朋友*/
    List<FriendLink> getFriendsByASC();
}
