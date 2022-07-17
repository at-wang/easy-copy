package com.wang.copyeasy.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoiResult {
    public boolean merged;
    public int startRow;
    public int endRow;
    public int startCol;
    public int endCol;
}
