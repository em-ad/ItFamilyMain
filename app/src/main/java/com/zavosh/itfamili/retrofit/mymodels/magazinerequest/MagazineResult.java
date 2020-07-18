
package com.zavosh.itfamili.retrofit.mymodels.magazinerequest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MagazineResult implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("summery")
    @Expose
    private String summery;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("linkeCount")
    @Expose
    private String linkeCount;
    @SerializedName("linkAddress")
    @Expose
    private String linkAddress;
    @SerializedName("publishDate")
    @Expose
    private String publishDate;
    @SerializedName("contentSource")
    @Expose
    private String contentSource;
    @SerializedName("commentCount")
    @Expose
    private String commentCount;
    @SerializedName("isLike")
    @Expose
    private String isLike = "false";

    protected MagazineResult(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
        linkeCount = in.readString();
        linkAddress = in.readString();
        publishDate = in.readString();
        contentSource = in.readString();
        commentCount = in.readString();
        isLike = in.readString();
    }

    public static final Creator<MagazineResult> CREATOR = new Creator<MagazineResult>() {
        @Override
        public MagazineResult createFromParcel(Parcel in) {
            return new MagazineResult(in);
        }

        @Override
        public MagazineResult[] newArray(int size) {
            return new MagazineResult[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinkeCount() {
        return linkeCount;
    }

    public void setLinkeCount(String linkeCount) {
        this.linkeCount = linkeCount;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getContentSource() {
        return contentSource;
    }

    public void setContentSource(String contentSource) {
        this.contentSource = contentSource;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(linkeCount);
        dest.writeString(linkAddress);
        dest.writeString(publishDate);
        dest.writeString(contentSource);
        dest.writeString(commentCount);
        dest.writeString(isLike);
    }
}