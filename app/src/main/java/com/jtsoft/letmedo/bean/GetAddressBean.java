package com.jtsoft.letmedo.bean;

import java.util.List;

/**
 * Created by admin on 2017/8/24.
 */

public class GetAddressBean {

    /**
     * code : 200
     * message : null
     * timestamp : 1503567202716
     * response : {"orderAddressList":[{"addressId":103,"userId":56289,"provinceId":320000,"provinceName":"江苏","cityId":320100,"cityName":"南京","districtId":320114,"districtName":"雨花台","detailAddress":"春江佳园 13栋501","longitude":118.754975,"latitude":31.959832,"contactName":"陈中原","contactPhone":"18319628135","delStatus":0,"updateTime":1503560510000,"createTime":1502692698000},{"addressId":104,"userId":56289,"provinceId":320000,"provinceName":"江苏","cityId":320100,"cityName":"南京","districtId":320105,"districtName":"建邺","detailAddress":"三江学院主校区","longitude":118.753366,"latitude":31.958247,"contactName":"地址添加测试","contactPhone":"18319628135","delStatus":0,"updateTime":1502763963000,"createTime":1502763963000},{"addressId":115,"userId":56289,"provinceId":320000,"provinceName":"江苏","cityId":320100,"cityName":"南京","districtId":320111,"districtName":"浦口","detailAddress":"让我来果源(柳洲东路) ，","longitude":118.736915,"latitude":32.14175,"contactName":"测试","contactPhone":"13915736984","delStatus":0,"updateTime":1503560582000,"createTime":1503560582000}]}
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
        private List<OrderAddressListBean> orderAddressList;

        public List<OrderAddressListBean> getOrderAddressList() {
            return orderAddressList;
        }

        public void setOrderAddressList(List<OrderAddressListBean> orderAddressList) {
            this.orderAddressList = orderAddressList;
        }

        public static class OrderAddressListBean {
            /**
             * addressId : 103
             * userId : 56289
             * provinceId : 320000
             * provinceName : 江苏
             * cityId : 320100
             * cityName : 南京
             * districtId : 320114
             * districtName : 雨花台
             * detailAddress : 春江佳园 13栋501
             * longitude : 118.754975
             * latitude : 31.959832
             * contactName : 陈中原
             * contactPhone : 18319628135
             * delStatus : 0
             * updateTime : 1503560510000
             * createTime : 1502692698000
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
