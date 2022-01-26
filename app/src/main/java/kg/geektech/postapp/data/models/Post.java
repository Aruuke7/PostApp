package kg.geektech.postapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {

    private int id;
    private String title;
    private String content;
    @SerializedName("user")
    private int userId;
    @SerializedName("group")
    private int groupId;

    public Post(String title, String content, int userId, int groupId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
