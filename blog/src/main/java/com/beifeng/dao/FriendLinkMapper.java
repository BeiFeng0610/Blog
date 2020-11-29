package com.beifeng.dao;

import com.beifeng.domain.FriendLink;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/29 21:25
 */
public interface FriendLinkMapper {

    List<FriendLink> getFriendList();

    Integer saveFriend(FriendLink friendLink);

    FriendLink getFriendById(String id);

    Integer updateFriendById(FriendLink friendLink);

    Integer deleteFriendById(String id);

    List<FriendLink> getFriendsByASC();
}
