package com.example.RestApi.P2.utility;

import com.example.RestApi.P2.rest.GenericModelForResponse;

public abstract class LogFramework<T> {

    public final String START = "START";
    public final String END = "END";
    public final String PROCESS_TIME = "PROCESS REQUEST IN (MS)";

    protected abstract String getLocationName();

    public void debug(boolean isValid, T requestModel, GenericModelForResponse response) {
        LogConfig.logger.debug(getLocationName() + " - " + validateCurrentReq(isValid));
        LogConfig.logger.debug(getLocationName() + " - " + " Request Model " + requestModel.toString());
        LogConfig.logger.debug(getLocationName() + " - " + " Response Model " + response.toString());
    }

    public void error(boolean isValid, T requestModel, GenericModelForResponse response) {
        LogConfig.logger.error(getLocationName() + " - " + validateCurrentReq(isValid));
        LogConfig.logger.error(getLocationName() + " - " + " Request Model " + requestModel.toString());
        LogConfig.logger.error(getLocationName() + " - " + " Response Model " + response.toString());
    }

    public void information(String infoMsg) {
        LogConfig.logger.info(getLocationName() + " - " + infoMsg);
    }

    private String validateCurrentReq(boolean isValid) {
        return (isValid) ? "[Valid]" : "[Not Valid]";
    }
}
