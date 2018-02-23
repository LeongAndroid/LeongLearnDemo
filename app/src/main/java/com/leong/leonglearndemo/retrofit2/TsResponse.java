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



public class TsResponse {

    /**
     * kwd : 装修网
     * url : http://wap.sogou.com/web/searchList.jsp?keyword=%E8%A3%85%E4%BF%AE%E7%BD%91&pid=sogou-appi-6d3a2d24eb109ddd&v=5
     */

    private String kwd;
    private String url;

    public String getKwd() {
        return kwd;
    }

    public void setKwd(String kwd) {
        this.kwd = kwd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TsResponse{" +
                "kwd='" + kwd + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
