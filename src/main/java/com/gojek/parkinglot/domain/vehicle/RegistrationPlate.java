package com.gojek.parkinglot.domain.vehicle;

import java.util.Arrays;
import java.util.List;

public class RegistrationPlate {
    private String stateCode;
    private String regionCode;
    private String series;
    private String registrationNumber;
    private static final String PLATE_NUMBER_PRINT_FORMAT = "%s-%s-%s-%s";

    public RegistrationPlate(Builder builder){
        this.stateCode = builder.getStateCode();
        this.regionCode = builder.getRegionCode();
        this.series = builder.getSeries();
        this.registrationNumber = builder.getRegistrationNumber();
    }

    @Override
    public String toString(){
        return String.format(PLATE_NUMBER_PRINT_FORMAT, stateCode, regionCode, series, registrationNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj !=null)
        {
            RegistrationPlate registrationPlate = (RegistrationPlate) obj;
            return this.toString().equals(registrationPlate.toString());
        }
        return false;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getSeries() {
        return series;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public static RegistrationPlate getRegistrationPlate(String registrationPlateStr) {
        return (new RegistrationPlate.Builder(registrationPlateStr)).build();
    }


    public static class Builder{
        private String stateCode;
        private String regionCode;
        private String series;
        private String registrationNumber;

        public Builder(String registrationPlate){
            List<String> codes = Arrays.asList(registrationPlate.split("-"));
            this.setStateCode(codes.get(0))
                    .setRegionCode(codes.get(1))
                    .setSeries(codes.get(2))
                    .setRegistrationNumber(codes.get(3));
        }

        public RegistrationPlate build(){
            return new RegistrationPlate(this);
        }

        public String getStateCode() {
            return stateCode;
        }

        public Builder setStateCode(String stateCode) {
            this.stateCode = stateCode;
            return this;
        }

        public String getRegionCode() {
            return regionCode;
        }

        public Builder setRegionCode(String regionCode) {
            this.regionCode = regionCode;
            return this;
        }

        public String getSeries() {
            return series;
        }

        public Builder setSeries(String series) {
            this.series = series;
            return this;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public Builder setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
            return this;
        }
    }
}
