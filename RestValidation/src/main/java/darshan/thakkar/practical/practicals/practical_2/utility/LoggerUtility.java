package darshan.thakkar.practical.practicals.practical_2.utility;

import darshan.thakkar.practical.practicals.practical_2.models.GenericResponseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class LoggerUtility<T> {
//    public final Logger logger = LogManager.getLogger();
    public final String START = " [START] ";
    public final String END = " [END] ";
    public final String REQUEST_PROCESSING_TIME_LOG_STRING = " [REQUEST PROCESSING TIME (ms) ] ";

    protected abstract String getModuleName();

    public void debugLog(boolean isValidRequest, T requestModel, GenericResponseModel response) {
        ApplicationLog.logger.debug(getModuleName() + " -> " + validateRequestLog(isValidRequest));
        ApplicationLog.logger.debug(getModuleName() + " -> [Request Model] -> " + requestModel.toString());
        ApplicationLog.logger.debug(getModuleName() + " -> [Response Model] -> " + response.toString());
    }

    public void errorLog(boolean isValidRequest, T requestModel, GenericResponseModel response) {
        ApplicationLog.logger.error(getModuleName() + " -> " + validateRequestLog(isValidRequest));
        ApplicationLog.logger.error(getModuleName() + " -> [Request Model] -> " + requestModel.toString());
        ApplicationLog.logger.error(getModuleName() + " -> [Response Model] -> " + response.toString());
    }

    public void infoLog(String logMessage) {
        ApplicationLog.logger.info(getModuleName() + " -> " + logMessage);
    }

    private String validateRequestLog(boolean isValidRequest) {
        return (isValidRequest) ? "[Valid Request] " : " [Not Valid Request] ";
    }

}
