package com.wang.copyeasy.utils;

import com.wang.copyeasy.vo.PoiResult;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class PoiUtils {
    public static PoiResult isMergedRegion(Sheet sheet, int row , int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return new PoiResult(true,firstRow+1,lastRow+1,firstColumn+1,lastColumn+1);
                }
            }
        }
        return new PoiResult(false,0,0,0,0);
    }



}
