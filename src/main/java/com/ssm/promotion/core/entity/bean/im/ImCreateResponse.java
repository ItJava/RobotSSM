package com.ssm.promotion.core.entity.bean.im;

/**创建IM 账户返回的resp*/
public class ImCreateResponse {


    /**
     * resp : {"respCode":"000000","client":{"clientNumber":"60000130438995","clientPwd":"9a063706","createDate":"2018-11-03 03:49:47","loginToken":"eyJBbGciOiJIUzI1NiIsIkFjY2lkIjoiNTg4MDE3Njg0NWQ1NGUxMmI3OGIzMmJlMTA3NWE1MDAiLCJBcHBpZCI6ImIwYWJlNzI1NjYyMjQzOTM4NGE1MjdlMTM0YTcyMWY5IiwiVXNlcmlkIjoiMTUyMTc3NjQxMjYifQ==.RWSQr0Hybczuc3ZksCtHgUiRXOAWZDs69YvT/0BlQyw=","userId":"15217764126"}}
     */

    private RespBean resp;

    public RespBean getResp() {
        return resp;
    }

    public void setResp(RespBean resp) {
        this.resp = resp;
    }

    public static class RespBean {
        /**
         * respCode : 000000
         * client : {"clientNumber":"60000130438995","clientPwd":"9a063706","createDate":"2018-11-03 03:49:47","loginToken":"eyJBbGciOiJIUzI1NiIsIkFjY2lkIjoiNTg4MDE3Njg0NWQ1NGUxMmI3OGIzMmJlMTA3NWE1MDAiLCJBcHBpZCI6ImIwYWJlNzI1NjYyMjQzOTM4NGE1MjdlMTM0YTcyMWY5IiwiVXNlcmlkIjoiMTUyMTc3NjQxMjYifQ==.RWSQr0Hybczuc3ZksCtHgUiRXOAWZDs69YvT/0BlQyw=","userId":"15217764126"}
         */

        private String respCode;
        private ClientBean client;

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public ClientBean getClient() {
            return client;
        }

        public void setClient(ClientBean client) {
            this.client = client;
        }

        public static class ClientBean {
            /**
             * clientNumber : 60000130438995
             * clientPwd : 9a063706
             * createDate : 2018-11-03 03:49:47
             * loginToken : eyJBbGciOiJIUzI1NiIsIkFjY2lkIjoiNTg4MDE3Njg0NWQ1NGUxMmI3OGIzMmJlMTA3NWE1MDAiLCJBcHBpZCI6ImIwYWJlNzI1NjYyMjQzOTM4NGE1MjdlMTM0YTcyMWY5IiwiVXNlcmlkIjoiMTUyMTc3NjQxMjYifQ==.RWSQr0Hybczuc3ZksCtHgUiRXOAWZDs69YvT/0BlQyw=
             * userId : 15217764126
             */

            private String clientNumber;
            private String clientPwd;
            private String createDate;
            private String loginToken;
            private String userId;

            public String getClientNumber() {
                return clientNumber;
            }

            public void setClientNumber(String clientNumber) {
                this.clientNumber = clientNumber;
            }

            public String getClientPwd() {
                return clientPwd;
            }

            public void setClientPwd(String clientPwd) {
                this.clientPwd = clientPwd;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getLoginToken() {
                return loginToken;
            }

            public void setLoginToken(String loginToken) {
                this.loginToken = loginToken;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }



}
