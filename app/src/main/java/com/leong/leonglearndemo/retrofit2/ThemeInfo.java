/*
 * Copyright LeongAndroid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leong.leonglearndemo.retrofit2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ThemeInfo {

    /**
     * region : cn
     * themeLastSyncDate : 1503126024073
     * themeData : [{"DT_RowClass":"","DT_RowId":"6558","author":"<l r=\"default\">ysx<\/l><l r=\"zh_HK\">ysx<\/l><l r=\"zh_TW\">ysx<\/l>","cloudStatus":1,"createdate":"2017-08-19 14:53:10","downloadnum":262,"id":6558,"isCopyright":null,"isMuslim":null,"isrecommend":0,"mediatype":2,"memo":"<l r=\"default\">无<\/l><l r=\"zh_HK\">無<\/l><l r=\"zh_TW\">無<\/l>","name":"<l r=\"default\">Come from star<\/l><l r=\"zh_HK\">Come from star<\/l><l r=\"zh_TW\">Come from star<\/l>","patchmemo":"<l r=\"default\">无<\/l><l r=\"zh_HK\">無<\/l><l r=\"zh_TW\">無<\/l>","picpath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144817.956/preview/1.jpg;http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144817.956/preview/2.jpg;","point":4,"pointUserNum":26,"score":null,"searchkey":"","showName":"Come from star","showSize":"4.57","showVersion":"1.0","size":4795402,"status":1,"template":"","themezippath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144817.956/2017-08-19.144817.956.zip","titlepicpath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144817.956/cover.jpg","type":0,"userId":2,"version":"<l r=\"default\">1.0<\/l><l r=\"zh_HK\">1.0<\/l><l r=\"zh_TW\">1.0<\/l>","versioncode":1},{"DT_RowClass":"","DT_RowId":"6557","author":"<l r=\"default\">ysx<\/l><l r=\"zh_HK\">ysx<\/l>","cloudStatus":1,"createdate":"2017-08-19 14:53:07","downloadnum":40,"id":6557,"isCopyright":null,"isMuslim":null,"isrecommend":0,"mediatype":2,"memo":"<l r=\"default\">无<\/l><l r=\"zh_HK\">無<\/l><l r=\"zh_TW\">無<\/l>","name":"<l r=\"default\">Evening party<\/l><l r=\"zh_HK\">Evening party<\/l><l r=\"zh_TW\">Evening party<\/l>","patchmemo":"<l r=\"default\">无<\/l><l r=\"zh_HK\">無<\/l><l r=\"zh_TW\">無<\/l>","picpath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144730.256/preview/1.jpg;http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144730.256/preview/2.jpg;","point":5,"pointUserNum":4,"score":null,"searchkey":"","showName":"Evening party","showSize":"3.40","showVersion":"1.0","size":3566950,"status":1,"template":"","themezippath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144730.256/2017-08-19.144730.256.zip","titlepicpath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144730.256/cover.jpg","type":0,"userId":2,"version":"<l r=\"default\">1.0<\/l><l r=\"zh_HK\">1.0<\/l><l r=\"zh_TW\">1.0<\/l>","versioncode":1},{"DT_RowClass":"","DT_RowId":"6555","author":"<l r=\"zh_HK\">ysx<\/l><l r=\"zh_TW\">ysx<\/l>","cloudStatus":1,"createdate":"2017-08-19 14:48:49","downloadnum":22,"id":6555,"isCopyright":null,"isMuslim":null,"isrecommend":0,"mediatype":2,"memo":"<l r=\"default\">无<\/l><l r=\"zh_HK\">無<\/l><l r=\"zh_TW\">無<\/l>","name":"<l r=\"default\">舞台<\/l><l r=\"zh_HK\">舞臺<\/l><l r=\"zh_TW\">舞臺<\/l>","patchmemo":"<l r=\"default\">无<\/l><l r=\"zh_HK\">無<\/l><l r=\"zh_TW\">無<\/l>","picpath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144203.122/preview/1.jpg;http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144203.122/preview/2.jpg;","point":1,"pointUserNum":1,"score":null,"searchkey":"","showName":"舞台","showSize":"2.91","showVersion":"1.0","size":3051713,"status":1,"template":"","themezippath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144203.122/2017-08-19.144203.122.zip","titlepicpath":"http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144203.122/cover.jpg","type":0,"userId":2,"version":"<l r=\"default\">1.0<\/l><l r=\"zh_HK\">1.0<\/l><l r=\"zh_TW\">1.0<\/l>","versioncode":1}]
     */

    @SerializedName("region")
    private String region;
    @SerializedName("themeLastSyncDate")
    private long themeLastSyncDate;
    @SerializedName("themeData")
    private List<ThemeDataBean> themeData;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getThemeLastSyncDate() {
        return themeLastSyncDate;
    }

    public void setThemeLastSyncDate(long themeLastSyncDate) {
        this.themeLastSyncDate = themeLastSyncDate;
    }

    public List<ThemeDataBean> getThemeData() {
        return themeData;
    }

    public void setThemeData(List<ThemeDataBean> themeData) {
        this.themeData = themeData;
    }

    public static class ThemeDataBean {
        /**
         * DT_RowClass :
         * DT_RowId : 6558
         * author : <l r="default">ysx</l><l r="zh_HK">ysx</l><l r="zh_TW">ysx</l>
         * cloudStatus : 1
         * createdate : 2017-08-19 14:53:10
         * downloadnum : 262
         * id : 6558
         * isCopyright : null
         * isMuslim : null
         * isrecommend : 0
         * mediatype : 2
         * memo : <l r="default">无</l><l r="zh_HK">無</l><l r="zh_TW">無</l>
         * name : <l r="default">Come from star</l><l r="zh_HK">Come from star</l><l r="zh_TW">Come from star</l>
         * patchmemo : <l r="default">无</l><l r="zh_HK">無</l><l r="zh_TW">無</l>
         * picpath : http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144817.956/preview/1.jpg;http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144817.956/preview/2.jpg;
         * point : 4
         * pointUserNum : 26
         * score : null
         * searchkey :
         * showName : Come from star
         * showSize : 4.57
         * showVersion : 1.0
         * size : 4795402
         * status : 1
         * template :
         * themezippath : http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144817.956/2017-08-19.144817.956.zip
         * titlepicpath : http://hangzhou-ota.oss-cn-hangzhou.aliyuncs.com/theme/2/2017-08-19.144817.956/cover.jpg
         * type : 0
         * userId : 2
         * version : <l r="default">1.0</l><l r="zh_HK">1.0</l><l r="zh_TW">1.0</l>
         * versioncode : 1
         */

        @SerializedName("DT_RowClass")
        private String DTRowClass;
        @SerializedName("DT_RowId")
        private String DTRowId;
        @SerializedName("author")
        private String author;
        @SerializedName("cloudStatus")
        private int cloudStatus;
        @SerializedName("createdate")
        private String createdate;
        @SerializedName("downloadnum")
        private int downloadnum;
        @SerializedName("id")
        private int id;
        @SerializedName("isCopyright")
        private Object isCopyright;
        @SerializedName("isMuslim")
        private Object isMuslim;
        @SerializedName("isrecommend")
        private int isrecommend;
        @SerializedName("mediatype")
        private int mediatype;
        @SerializedName("memo")
        private String memo;
        @SerializedName("name")
        private String name;
        @SerializedName("patchmemo")
        private String patchmemo;
        @SerializedName("picpath")
        private String picpath;
        @SerializedName("point")
        private int point;
        @SerializedName("pointUserNum")
        private int pointUserNum;
        @SerializedName("score")
        private Object score;
        @SerializedName("searchkey")
        private String searchkey;
        @SerializedName("showName")
        private String showName;
        @SerializedName("showSize")
        private String showSize;
        @SerializedName("showVersion")
        private String showVersion;
        @SerializedName("size")
        private int size;
        @SerializedName("status")
        private int status;
        @SerializedName("template")
        private String template;
        @SerializedName("themezippath")
        private String themezippath;
        @SerializedName("titlepicpath")
        private String titlepicpath;
        @SerializedName("type")
        private int type;
        @SerializedName("userId")
        private int userId;
        @SerializedName("version")
        private String version;
        @SerializedName("versioncode")
        private int versioncode;

        public String getDTRowClass() {
            return DTRowClass;
        }

        public void setDTRowClass(String DTRowClass) {
            this.DTRowClass = DTRowClass;
        }

        public String getDTRowId() {
            return DTRowId;
        }

        public void setDTRowId(String DTRowId) {
            this.DTRowId = DTRowId;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getCloudStatus() {
            return cloudStatus;
        }

        public void setCloudStatus(int cloudStatus) {
            this.cloudStatus = cloudStatus;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public int getDownloadnum() {
            return downloadnum;
        }

        public void setDownloadnum(int downloadnum) {
            this.downloadnum = downloadnum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getIsCopyright() {
            return isCopyright;
        }

        public void setIsCopyright(Object isCopyright) {
            this.isCopyright = isCopyright;
        }

        public Object getIsMuslim() {
            return isMuslim;
        }

        public void setIsMuslim(Object isMuslim) {
            this.isMuslim = isMuslim;
        }

        public int getIsrecommend() {
            return isrecommend;
        }

        public void setIsrecommend(int isrecommend) {
            this.isrecommend = isrecommend;
        }

        public int getMediatype() {
            return mediatype;
        }

        public void setMediatype(int mediatype) {
            this.mediatype = mediatype;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatchmemo() {
            return patchmemo;
        }

        public void setPatchmemo(String patchmemo) {
            this.patchmemo = patchmemo;
        }

        public String getPicpath() {
            return picpath;
        }

        public void setPicpath(String picpath) {
            this.picpath = picpath;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getPointUserNum() {
            return pointUserNum;
        }

        public void setPointUserNum(int pointUserNum) {
            this.pointUserNum = pointUserNum;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public String getSearchkey() {
            return searchkey;
        }

        public void setSearchkey(String searchkey) {
            this.searchkey = searchkey;
        }

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public String getShowSize() {
            return showSize;
        }

        public void setShowSize(String showSize) {
            this.showSize = showSize;
        }

        public String getShowVersion() {
            return showVersion;
        }

        public void setShowVersion(String showVersion) {
            this.showVersion = showVersion;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getThemezippath() {
            return themezippath;
        }

        public void setThemezippath(String themezippath) {
            this.themezippath = themezippath;
        }

        public String getTitlepicpath() {
            return titlepicpath;
        }

        public void setTitlepicpath(String titlepicpath) {
            this.titlepicpath = titlepicpath;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(int versioncode) {
            this.versioncode = versioncode;
        }

        @Override
        public String toString() {
            return "ThemeDataBean{" +
                    "DTRowClass='" + DTRowClass + '\'' +
                    ", DTRowId='" + DTRowId + '\'' +
                    ", author='" + author + '\'' +
                    ", cloudStatus=" + cloudStatus +
                    ", createdate='" + createdate + '\'' +
                    ", downloadnum=" + downloadnum +
                    ", id=" + id +
                    ", isCopyright=" + isCopyright +
                    ", isMuslim=" + isMuslim +
                    ", isrecommend=" + isrecommend +
                    ", mediatype=" + mediatype +
                    ", memo='" + memo + '\'' +
                    ", name='" + name + '\'' +
                    ", patchmemo='" + patchmemo + '\'' +
                    ", picpath='" + picpath + '\'' +
                    ", point=" + point +
                    ", pointUserNum=" + pointUserNum +
                    ", score=" + score +
                    ", searchkey='" + searchkey + '\'' +
                    ", showName='" + showName + '\'' +
                    ", showSize='" + showSize + '\'' +
                    ", showVersion='" + showVersion + '\'' +
                    ", size=" + size +
                    ", status=" + status +
                    ", template='" + template + '\'' +
                    ", themezippath='" + themezippath + '\'' +
                    ", titlepicpath='" + titlepicpath + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    ", version='" + version + '\'' +
                    ", versioncode=" + versioncode +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ThemeInfo{" +
                "region='" + region + '\'' +
                ", themeLastSyncDate=" + themeLastSyncDate +
                ", themeData=" + themeData.toString() +
                '}';
    }
}
