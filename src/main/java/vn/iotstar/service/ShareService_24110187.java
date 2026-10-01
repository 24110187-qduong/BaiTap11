package vn.iotstar.service;

import vn.iotstar.dao.ShareDAO_24110187;

public class ShareService_24110187 {

    private final ShareDAO_24110187 shareDAO = new ShareDAO_24110187();

    public int countByVideo(String videoid) { return shareDAO.countByVideo(videoid); }
    public boolean share(String videoid, String username, String email) {
        return shareDAO.insert(videoid, username, email);
    }
}