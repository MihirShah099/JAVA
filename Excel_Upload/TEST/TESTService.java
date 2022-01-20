package com.arc.TEST;

import com.arc.core.exceptions.ExcelSameFieldNameException;
import com.arc.core.utillity.log.ApplicationLogger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TESTService {
    @Autowired
    private TESTExcelUtil excelUtil;

    public /*List<DocketDTO>*/List<Map<String, Object>> getJson(MultipartFile file) throws Exception {
        try {
            Path tempDir = Files.createTempDirectory("");
            File tempFile = tempDir.resolve(Objects.requireNonNull(file.getOriginalFilename())).toFile();
            file.transferTo(tempFile);
            Workbook workbook = WorkbookFactory.create(tempFile);
            Sheet sheet = workbook.getSheetAt(0);
            Supplier<Stream<Row>> rowStreamSupplier = excelUtil.getRowStreamSupplier(sheet);/*StreamSupport.stream(sheet.spliterator(), false);*/

            Row headerRow = rowStreamSupplier.get().findFirst().get();
            List<String> headerCells = excelUtil.getStream(headerRow)
                    .map(Cell::getStringCellValue)
                    .collect(Collectors.toList());
            ApplicationLogger.logger.info("HEADER CELL : " + headerCells);

            int colCount = headerCells.size();
            ApplicationLogger.logger.info("COL COUNT : " + colCount);

            List<Map<String, Object>> resultMap = rowStreamSupplier.get()
                    .skip(1L)
                    /*.limit(rowStreamSupplier.get().count() - 1)*/
                    .map(row -> {
                        List<Object> cellList = excelUtil.getStream(row)
                                .map(this::getMappedCell)
                                .collect(Collectors.toList());
                        return excelUtil.cellIteratorSupplier(colCount)
                                .get()
                                .collect(Collectors.toMap(
                                        /*i -> headerCells.get(i).replaceAll(" ", "").toLowerCase(),*/  //  (For making key of Map in lowercase & trim space)
                                        headerCells::get,
                                        cellList::get));
                /*Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
                List<String> cellValues = cellStream
                        .map(cell -> {
                            DataFormatter dataFormatter = new DataFormatter();
                            return dataFormatter.formatCellValue(cell);
                        })*//*Cell::getStringCellValue*//*
                        .collect(Collectors.toList());
                ApplicationLogger.logger.info("CELL VALUE : " + cellValues);*/
                    }).collect(Collectors.toList());
            /*if (null != resultMap && 0 < resultMap.size()) {
                List<PartyDTO> customerList = new ArrayList<>();
                List<DocketDTO> docketList = new ArrayList<>();
                for (Map<String, Object> map : resultMap) {
                    DocketDTO docket = new DocketDTO();
                    PartyDTO customer = new PartyDTO();
                    String customerName = map.containsValue(map.get("Customer")) ? map.get("Customer").toString().replaceAll("Mr. ", "") : "";
                    if (!customerName.equals("")) {
                        String[] name = customerName.split(" ");
                        if (3 == name.length) {
                            customer.setName(name[0]);
                            customer.setMiddleName(name[1]);
                            customer.setLastName(name[2]);
                        } else {
                            customer.setName("");
                            for (String fullName : name)
                                customer.setName(customer.getName() + " " + fullName);
                        }
                    }
                    *//*customer.setName(map.containsValue(map.get("Customer")) ? map.get("Customer").toString() : "");*//*
                    customer.setEmail(map.containsValue(map.get("Booking Email")) ? map.get("Booking Email").toString() : null);
                    customer.setAddress(map.containsValue(map.get("Address 1"))
                            ? map.get("Address 1").toString() + map.get("Address 2") + map.get("Address 3") : null);
                    customer.setPinCode(map.containsValue(map.get("Pin")) ? map.get("Pin").toString() : null);
                    customer.setState(map.containsValue(map.get("State")) ? map.get("State").toString() : null);
                    customer.setMobileNo1(map.containsValue(map.get("Mobile No")) ? map.get("Mobile No").toString() : null);
                    customer.setDistrict(map.containsValue(map.get("District")) ? map.get("District").toString() : null);

                    customerList.add(customer);

                    docket.setInvoice(map.containsValue(map.get("Invoice")) ? map.get("Invoice").toString() : null);
                    docket.setBookingNumber(map.containsValue(map.get("Booking No")) ? map.get("Booking No").toString() : null);
                    docket.setBookingDate(map.containsValue(map.get("Booking Date")) && !map.get("Booking Date").equals("")
                            ? new java.sql.Date(DateUtil.getJavaDate((double) map.get("Booking Date")).getTime()) : null);
                    docket.setDeliveryDate(map.containsValue(map.get("DeliveryDate")) && !map.get("DeliveryDate").equals("")
                            ? new java.sql.Date(DateUtil.getJavaDate((double) map.get("DeliveryDate")).getTime()) : null);
                    docket.setCustomer(customer);

                    docketList.add(docket);

                    *//*ApplicationLogger.logger.info("MAP : " + map);*//*
                    if (map.get("Booking Date") != null && map.get("Booking Date") != "") {
                        ApplicationLogger.logger.info("SQL DATE 1 : " + excelUtil.getSqlDate(map.get("Booking Date")));
                        Date javaDate = DateUtil.getJavaDate((double) map.get("Booking Date"));
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(javaDate);
                        *//*ApplicationLogger.logger.info(" 1 : " + date);*//*
                    }

                    if (map.get("DeliveryDate") != null && map.get("DeliveryDate") != "") {
                        ApplicationLogger.logger.info("SQL DATE 2 : " + excelUtil.getSqlDate(map.get("DeliveryDate")));
                        Date javaDate2 = DateUtil.getJavaDate((double) map.get("DeliveryDate"));
                        String date2 = new SimpleDateFormat("dd/MM/yyyy").format(javaDate2);
                        *//*ApplicationLogger.logger.info(" 2 : " + date2);*//*
                    }
                }
                return docketList;
            }*/
            /*return resultMap;*/
        } catch (Exception e) {
            ApplicationLogger.logger.error("[SERVICE] " + e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    public /*List<AccessoriesDTO>*/List<Map<String, Object>> getJson2(MultipartFile file) throws Exception {
        try {
            Path tempDir = Files.createTempDirectory("");
            File tempFile = tempDir.resolve(Objects.requireNonNull(file.getOriginalFilename())).toFile();
            file.transferTo(tempFile);
            Workbook workbook = WorkbookFactory.create(tempFile);
            Sheet sheet = workbook.getSheetAt(0);
            Supplier<Stream<Row>> rowStreamSupplier = excelUtil.getRowStreamSupplier(sheet);/*StreamSupport.stream(sheet.spliterator(), false);*/

            Row headerRow = rowStreamSupplier.get().findFirst().get();
            List<String> headerCells = excelUtil.getStream(headerRow)
                    .map(Cell::getStringCellValue)
                    /*.distinct()*/
                    .collect(Collectors.toList());
            Set<String> headerDistinctSet = new HashSet<>();
            Set<String> headerRejectedSet = headerCells
                    .stream()
                    .filter(data -> !headerDistinctSet.add(data))
                    .collect(Collectors.toSet());
            List<String> headerCell1 = new ArrayList<>(headerDistinctSet);
            ApplicationLogger.logger.info("HEADER CELL : " + headerCell1);
            ApplicationLogger.logger.info("REJECTED CELL : " + headerRejectedSet);
            if (null != headerRejectedSet && 0 < headerRejectedSet.size()) {
                throw new ExcelSameFieldNameException();
            }

            int colCount = headerCell1.size();
            ApplicationLogger.logger.info("COL COUNT : " + colCount);

            List<Map<String, Object>> resultMap = rowStreamSupplier.get()
                    .skip(1L)
                    .map(row -> {
                        List<Object> cellList = excelUtil.getStream(row)
                                .map(this::getMappedCell)
                                .collect(Collectors.toList());
                        return excelUtil.cellIteratorSupplier(colCount)
                                .get()
                                .collect(Collectors.toMap(
                                        /*i -> headerCells.get(i).replaceAll(" ", "").toLowerCase(),*/  //  (For making key of Map in lowercase & trim space)
                                        headerCells::get,
                                        cellList::get));
                /*Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
                List<String> cellValues = cellStream
                        .map(cell -> {
                            DataFormatter dataFormatter = new DataFormatter();
                            return dataFormatter.formatCellValue(cell);
                        })*//*Cell::getStringCellValue*//*
                        .collect(Collectors.toList());
                ApplicationLogger.logger.info("CELL VALUE : " + cellValues);*/
                    }).collect(Collectors.toList());
            /*ApplicationLogger.logger.info("MAP : " + map);*//*
                    if (map.get("Booking Date") != null && map.get("Booking Date") != "") {
                        ApplicationLogger.logger.info("SQL DATE 1 : " + excelUtil.getSqlDate(map.get("Booking Date")));
                        Date javaDate = DateUtil.getJavaDate((double) map.get("Booking Date"));
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(javaDate);
                        *//*ApplicationLogger.logger.info(" 1 : " + date);*//*
                    }

                    if (map.get("DeliveryDate") != null && map.get("DeliveryDate") != "") {
                        ApplicationLogger.logger.info("SQL DATE 2 : " + excelUtil.getSqlDate(map.get("DeliveryDate")));
                        Date javaDate2 = DateUtil.getJavaDate((double) map.get("DeliveryDate"));
                        String date2 = new SimpleDateFormat("dd/MM/yyyy").format(javaDate2);
                        *//*ApplicationLogger.logger.info(" 2 : " + date2);*//*
                    }
                }

            }*/
            return resultMap;
        } catch (Exception e) {
            ApplicationLogger.logger.error("[SERVICE] " + e.getMessage(), e);
            throw e;
        }
        /*return new ArrayList<>();*/
    }

    private Object getMappedCell(Cell cell) {
        if (cell.getCellTypeEnum().equals(CellType.STRING))
            return cell.getStringCellValue();
        if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
            return cell.getNumericCellValue();
        if (cell.getCellTypeEnum().equals(CellType.BOOLEAN))
            return cell.getBooleanCellValue();
        return "";
    }
}


//  FOR DOUBLE TO UTIL DATE :

    /*Date javaDate = DateUtil.getJavaDate((double) map.get("Booking Date"));
    String date = new SimpleDateFormat("MM/dd/yyyy").format(javaDate);
                ApplicationLogger.logger.info(" 1 : " + date);*/

//  FOR UTIL TO SQL DATE :

/*java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());*/

//  FOR ANY CELL TYPE TO STRING :

    /*DataFormatter dataFormatter = new DataFormatter();
    ApplicationLogger.logger.info("CELL : " + dataFormatter.formatCellValue(cell));
         return dataFormatter.formatCellValue(cell);*/
