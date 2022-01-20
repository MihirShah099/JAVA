package com.arc.excelUpload.excelToJson;

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
public class ExcelToJsonService {
    @Autowired
    private ExcelUtilService excelUtil;

    public List<Map<String, Object>> getJson(MultipartFile file) throws Exception {
        try {
            Path tempDir = Files.createTempDirectory("");
            File tempFile = tempDir.resolve(Objects.requireNonNull(file.getOriginalFilename())).toFile();
            file.transferTo(tempFile);
            Workbook workbook = WorkbookFactory.create(tempFile);
            Sheet sheet = workbook.getSheetAt(0);
            Supplier<Stream<Row>> rowStreamSupplier = excelUtil.getRowStreamSupplier(sheet);

            Row headerRow = rowStreamSupplier.get().findFirst().get();
            List<String> excelHeaderCells = excelUtil.getStream(headerRow)
                    .map(i -> i.getStringCellValue().replaceAll("\\t", "")
                            .replaceAll("\\n", "")
                            .replaceAll(" ", "")
                            .replaceAll("`", ""))
                    .collect(Collectors.toList());

            Set<String> distinctHeaderSet = new HashSet<>();
            Set<String> headerRejectedSet = excelHeaderCells
                    .stream()
                    .filter(data -> !distinctHeaderSet.add(data))
                    .collect(Collectors.toSet());
            ApplicationLogger.logger.info("HEADER CELL : " + excelHeaderCells);
            ApplicationLogger.logger.info("REJECTED CELL : " + headerRejectedSet);
            if (null != headerRejectedSet && 0 < headerRejectedSet.size())
                throw new ExcelSameFieldNameException();

            int colCount = excelHeaderCells.size();
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
                                        i -> excelHeaderCells.get(i).replaceAll(" ", "").toLowerCase(),  //  (For making key of Map in lowercase & trim space)
                                        /*headerCells::get,*/       //for making key in Map same as in Excel..
                                        cellList::get));
                    }).collect(Collectors.toList());

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

