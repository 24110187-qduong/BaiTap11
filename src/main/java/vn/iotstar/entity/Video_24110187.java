package vn.iotstar.entity;

public class Video_24110187 {
    private String videoid;
    private String title;
    private String poster;
    private Integer views;
    private String description;
    private Boolean active;
    private Integer categoryId;
    private String categoryName;
    private Integer shareCount;
    private Integer likeCount;

    public Video_24110187() {}

    public String getVideoid() { return videoid; }
    public void setVideoid(String videoid) { this.videoid = videoid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public Integer getViews() { return views; }
    public void setViews(Integer views) { this.views = views; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Integer getShareCount() { return shareCount; }
    public void setShareCount(Integer shareCount) { this.shareCount = shareCount; }

    public Integer getLikeCount() { return likeCount; }
    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }
}