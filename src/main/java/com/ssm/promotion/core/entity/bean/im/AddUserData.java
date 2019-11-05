package com.ssm.promotion.core.entity.bean.im;

import java.io.Serializable;

public class AddUserData implements Serializable {


    /**
     * message : 成功
     * data : {"phone":"13360558521","userName":"","pushToken":"","headPic":""}
     * code : 0
     */
    private String message;
    private DataBean data;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * phone : 13360558521
         * userName :
         * pushToken :
         * headPic :
         */

        private String phone;
        private String userName;
        private String pushToken;
        private String headPic;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPushToken() {
            return pushToken;
        }

        public void setPushToken(String pushToken) {
            this.pushToken = pushToken;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }
    }
}
