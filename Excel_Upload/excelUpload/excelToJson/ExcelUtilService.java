package com.arc.excelUpload.excelToJson;

import com.arc.core.utillity.log.ApplicationLogger;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ExcelUtilService {
    public Supplier<Stream<Row>> getRowStreamSupplier(Iterable<Row> rows) {
        return () -> getStream(rows);
    }

    public <T> Stream<T> getStream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public Supplier<Stream<Integer>> cellIteratorSupplier(int end) {
        return () -> numberStream(end);
    }

    public Stream<Integer> numberStream(int end) {
        return IntStream
                .range(0, end)
                .boxed();
    }

    public Date getSqlDate(Object utilDate) {
        try {
            if (!(utilDate.toString().replaceAll("[^a-z|A-Z]", "").replaceAll(" ", "")).equals(""))
                return null;
            return new Date(DateUtil.getJavaDate(Double.parseDouble(utilDate.toString())).getTime());
        } catch (NumberFormatException e) {
            ApplicationLogger.logger.error("INCORRECT DATE FORMAT..\nREJECTED VALUE : " + utilDate);
        }
        return null;
    }

    public ExcelResponseForException getSqlDateException(Object utilDate) {
        ExcelResponseForException response = new ExcelResponseForException();
        try {
            if (!(utilDate.toString().replaceAll("[^a-z|A-Z]", "").replaceAll(" ", "")).equals("")) {
                response.setSqlDate(null);
                return response;
            }
            Date sqlDate = new Date(DateUtil.getJavaDate(Double.parseDouble(utilDate.toString())).getTime());
            response.setSqlDate(sqlDate);
        } catch (NumberFormatException e) {
            ApplicationLogger.logger.error("INCORRECT DATE FORMAT..\nREJECTED VALUE : " + utilDate);
            response.setIsValid(false);
            response.setSqlDate(null);
            response.setExceptionMessage("\tSet value to empty because " + utilDate + " is not in expected format.\n");
        }
        return response;
    }

    public Double getDouble(String data) {
        try {
            String stringDouble = null != data ? data.replaceAll("[^-0-9.]", "") : "";
            return (!stringDouble.equals("") && !stringDouble.equals("-")) ? Double.parseDouble(stringDouble) : 0.0;
        } catch (NumberFormatException e) {
            ApplicationLogger.logger.error("INCORRECT FORMAT..\nREJECTED VALUE : " + data);
        }
        return 0.0;
    }

    public ExcelResponseForException getDoubleException(String data) {
        ExcelResponseForException response = new ExcelResponseForException();
        try {
            String stringDouble = null != data ? data.replaceAll("[^-0-9.]", "") : "";
            Double doubleValue = (!stringDouble.equals("") && !stringDouble.equals("-")) ? Double.parseDouble(stringDouble) : 0.0;
            response.setDoubleData(doubleValue);
        } catch (NumberFormatException e) {
            ApplicationLogger.logger.error("INCORRECT FORMAT..\nREJECTED VALUE : " + data);
            response.setIsValid(false);
            response.setDoubleData(0.0);
            response.setExceptionMessage("\tSet value to 0.0 because " + data + " is not numeric.\n");
        }
        return response;
    }

    public String getLongString(String data) {
        try {
            String stringDouble = null != data ? data.replaceAll("[^-0-9]", "") : "";
            Double temp = (!stringDouble.equals("") && !stringDouble.equals("-")) ? Double.parseDouble(data) : 0.0;
            Long tempLong = temp.longValue();
            String stringLong = tempLong.toString();
            return (!stringLong.equals("") && !stringLong.equals("-")) ? String.valueOf(Long.parseLong(stringLong)) : null;
        } catch (NumberFormatException e) {
            ApplicationLogger.logger.error("INCORRECT FORMAT..\nREJECTED VALUE : " + data);
        }
        return null;
    }

    public ExcelResponseForException getLongStringException(String data) {
        ExcelResponseForException response = new ExcelResponseForException();
        try {
            String stringDouble = null != data ? data.replaceAll("[^-0-9]", "") : "";
            Double temp = (!stringDouble.equals("") && !stringDouble.equals("-")) ? Double.parseDouble(data) : 0.0;
            Long tempLong = temp.longValue();
            String stringLong = tempLong.toString();
            String resultString = (!stringLong.equals("") && !stringLong.equals("-")) ? String.valueOf(Long.parseLong(stringLong)) : null;
            response.setStringForLong(resultString);
        } catch (NumberFormatException e) {
            ApplicationLogger.logger.error("INCORRECT FORMAT..\nREJECTED VALUE : " + data);
            response.setIsValid(false);
            response.setStringForLong(null);
            response.setExceptionMessage("\tSet value to empty because " + data + " is not in expected format.\n");
        }
        return response;
    }
}
