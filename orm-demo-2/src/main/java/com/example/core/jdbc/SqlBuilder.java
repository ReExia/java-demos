package com.example.core.jdbc;

import com.example.core.convertor.EntityToParamtersConvertor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SqlBuilder {

    public static <T> String buildInsert(T entity){
        Map map = EntityToParamtersConvertor.convert(entity);

        String table = String.valueOf(map.get("table"));
        String idField = String.valueOf(map.get("idField"));
        String idValue = String.valueOf(map.get("idValue"));
        List fieldList = (List) map.get("fieldList");
        List valueFieldList = (List) map.get("valueFieldList");

        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO ");
        sb.append(table).append(" ");
        sb.append("value(");

        for (int i = 0 ; i < fieldList.size() ; i++){
            String value = String.valueOf(fieldList.get(i));
            if (!value.equals(idField)){
                sb.append(value);
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);

        sb.append(")");
        sb.append(" VALUES(");
        for (int i = 0 ; i < valueFieldList.size() ; i ++){
            String value = String.valueOf(valueFieldList.get(i));
            if (!value.equals(idValue)){
                sb.append(value);
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        return sb.toString();
    }


}
