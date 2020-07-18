package com.zavosh.itfamily.retrofit.mymodels.likerequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeSender {
    @SerializedName("ContentId")
    @Expose
    private String contentId;

    @SerializedName("IsLike")
    @Expose
    private boolean isLike;

    public LikeSender(String contentId, boolean isLike) {
        this.contentId = contentId;
        this.isLike = isLike;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
