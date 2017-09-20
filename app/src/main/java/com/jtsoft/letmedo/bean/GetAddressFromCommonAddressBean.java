package com.jtsoft.letmedo.bean;

/**
 * Created by admin on 2017/8/26.
 * 从地址列表获取地址的接口
 */

public class GetAddressFromCommonAddressBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1503726302970
     * response : {"orderAddress":{"addressId":119,"userId":56289,"provinceId":320000,"provinceName":"江苏","cityId":320100,"cityName":"南京","districtId":320106,"districtName":"鼓楼","detailAddress":"清江路菜场(苏宁环球商贸城南区) ，","longitude":118.730115,"latitude":32.043922,"contactName":"测试","contactPhone":"13915672658","delStatus":0,"updateTime":1503632427000,"createTime":1503632427000}}
     */

    private int code;
    private Object message;
    private long timestamp;
    private ResponseBean response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * orderAddress : {"addressId":119,"userId":56289,"provinceId":320000,"provinceName":"江苏","cityId":320100,"cityName":"南京","districtId":320106,"districtName":"鼓楼","detailAddress":"清江路菜场(苏宁环球商贸城南区) ，","longitude":118.730115,"latitude":32.043922,"contactName":"测试","contactPhone":"13915672658","delStatus":0,"updateTime":1503632427000,"createTime":1503632427000}
         */

        private OrderAddressBean orderAddress;

        public OrderAddressBean getOrderAddress() {
            return orderAddress;
        }

        public void setOrderAddress(OrderAddressBean orderAddress) {
            this.orderAddress = orderAddress;
        }

        public static class OrderAddressBean {
            /**
             * addressId : 119
             * userId : 56289
             * provinceId : 320000
             * provinceName : 江苏
             * cityId : 320100
             * cityName : 南京
             * districtId : 320106
             * districtName : 鼓楼
             * detailAddress : 清江路菜场(苏宁环球商贸城南区) ，
             * longitude : 118.730115
             * latitude : 32.043922
             * contactName : 测试
             * contactPhone : 13915672658
             * delStatus : 0
             * updateTime : 1503632427000
             * createTime : 1503632427000
             */

            private int addressId;
            private int userId;
            private int provinceId;
            private String provinceName;
            private int cityId;
            private String cityName;
            private int districtId;
            private String districtName;
            private String detailAddress;
            private double longitude;
            private double latitude;
            private String contactName;
            private String contactPhone;
            private int delStatus;
            private long updateTime;
            private long createTime;

            public int getAddressId() {
                return addressId;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(int provinceId) {
                this.provinceId = provinceId;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public int getDistrictId() {
                return districtId;
            }

            public void setDistrictId(int districtId) {
                this.districtId = districtId;
            }

            public String getDistrictName() {
                return districtName;
            }

            public void setDistrictName(String districtName) {
                this.districtName = districtName;
            }

            public String getDetailAddress() {
                return detailAddress;
            }

            public void setDetailAddress(String detailAddress) {
                this.detailAddress = detailAddress;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public String getContactPhone() {
                return contactPhone;
            }

            public void setContactPhone(String contactPhone) {
                this.contactPhone = contactPhone;
            }

            public int getDelStatus() {
                return delStatus;
            }

            public void setDelStatus(int delStatus) {
                this.delStatus = delStatus;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }
    }
}
