package vn.iotstar.service;

import vn.iotstar.dao.FavoriteDAO_24110187;

public class FavoriteService_24110187 {

    private final FavoriteDAO_24110187 favoriteDAO = new FavoriteDAO_24110187();

    public int countByVideo(String videoid) { return favoriteDAO.countByVideo(videoid); }
    public boolean like(String videoid, String username) {
        return favoriteDAO.insert(videoid, username);
    }
}