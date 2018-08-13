package com.demo.orm.core.template.mysql;

import com.demo.orm.core.JdbcUtils;
import com.demo.orm.core.annotation.Column;
import com.demo.orm.core.annotation.PK;
import com.demo.orm.core.annotation.Table;
import com.demo.orm.core.constant.SqlConstant;
import com.demo.orm.core.exception.MyOrmException;
import com.demo.orm.core.template.HandlerTemplate;
import com.demo.orm.core.utils.ReflectUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 * @author setsuna
 * mysql操作数据库实现类
 */
public class MysqlTemplate extends HandlerTemplate {

    private final static Logger LOGGER = LoggerFactory.getLogger(MysqlTemplate.class);

    /**
     * 保存实体
     *
     * @param entity
     * @param <T>
     */
    @Override
    public <T> void save(T entity) {

        //存储sql需要传入的参数
        List<Object> params = null;

        //拼接sql语句
        StringBuilder sb = new StringBuilder();

        //需要保存的实体类
        Class<?> clazz = entity.getClass();

        //表名默认为小写
        String tableName = clazz.getSimpleName().toLowerCase();

        Table tableAnnotation = clazz.getDeclaredAnnotation(Table.class);
        if (ObjectUtils.anyNotNull(tableAnnotation)) {
            if (StringUtils.isNotBlank(tableName)) {
                tableName = tableAnnotation.value();
            }
        }
        sb.append("INSERT INTO ").append(tableName).append("(");


        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtils.isNotEmpty(fields)) {
            // 实例化用于保存参数列表的集合
            params = new ArrayList<Object>();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                //开启访问权限
                field.setAccessible(true);
                String filedName = field.getName();
                if (!SqlConstant.SERIAL_VERSION_UID.getStringValue().equals(filedName)) {
                    try {
                        Object o = field.get(entity);

                        String value = String.valueOf(o);
                        //属性有值才追加sql
                        if (StringUtils.isNotBlank(value) && ObjectUtils.anyNotNull(o)) {
                            params.add(o);

                            Column column = field.getDeclaredAnnotation(Column.class);
                            //注解有值使用注解的值进行sql追加
                            if (StringUtils.isNotBlank(column.value())) {
                                sb.append(column.value()).append(",");
                            } else {
                                //注解无值使用属性值进行追加
                                if (StringUtils.isNotBlank(filedName)) {
                                    sb.append(filedName).append(",");
                                }
                            }
                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                        throw new MyOrmException("获取对象字段值异常 : " + e.getMessage());
                    }
                }

            }
            //删除最后一个逗号
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
            sb.append(" ").append("VALUES(");
            for (int j = 1; j <= params.size(); j++) {
                sb.append("?").append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        }

        LOGGER.info("PreparedStatement  => {}", sb.toString());
        LOGGER.info("paramters  => {}", ArrayUtils.toString(params.toArray()));
        JdbcUtils.executeUpdate(sb.toString(), params.toArray());
    }

    /**
     * 删除实体
     *
     * @param entity
     * @param <T>
     * @return
     * @throws MyOrmException
     */
    @Override
    public <T> int delete(T entity) throws MyOrmException {

        //存储参数
        List<Object> params = null;

        //拼接sql语句
        StringBuilder sb = new StringBuilder();

        //需要删除的类
        Class<?> clazz = entity.getClass();

        //操作的数据库表名
        String tableName = clazz.getSimpleName().toLowerCase();
        Table tableAnnitation = clazz.getDeclaredAnnotation(Table.class);
        if (StringUtils.isNotBlank(tableAnnitation.value())) {
            tableName = tableAnnitation.value();
        }

        sb.append("DELETE FROM ").append(tableName).append(" ").append("WHERE ");
        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtils.isNotEmpty(fields)) {
            params = new ArrayList<>();
            try {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    if (!SqlConstant.SERIAL_VERSION_UID.getStringValue().equals(fieldName)) {
                        Object o = field.get(entity);
                        //属性有值追加入sql
                        String value = String.valueOf(o);
                        if (StringUtils.isNotBlank(value) && ObjectUtils.anyNotNull(o)) {
                            params.add(o);
                            Column column = field.getDeclaredAnnotation(Column.class);
                            //注解有值使用注解
                            if (StringUtils.isNotBlank(column.value())) {
                                sb.append(column.value()).append("=?").append(" AND ");
                            } else {
                                //注解无值使用属性
                                if (StringUtils.isNotBlank(fieldName) && !"".equals(fieldName)) {
                                    sb.append(fieldName).append("=?").append(" AND ");
                                }
                            }
                        }
                    }
                }
                if (CollectionUtils.isEmpty(params)) {
                    throw new MyOrmException("未给进行删除操作的对象赋值");
                }
            } catch (Exception e) {
                //e.printStackTrace();
                throw new MyOrmException("通过主键删除对象的时候出现异常,信息为:" + e.getMessage());
            }
        }

        int start = sb.lastIndexOf("?") + 1;
        int end = sb.length();
        sb.delete(start, end);


        String sql = sb.toString().trim();

        LOGGER.info("PreparedStatement  => {}", sql);
        LOGGER.info("paramters  => {}", ArrayUtils.toString(params.toArray()));

        return JdbcUtils.executeUpdate(sql, params.toArray());
    }

    /**
     * 更新实体类,实体类属性值为空的不更新,需要传入id
     *
     * @param entity
     * @param <T>
     * @return
     * @throws MyOrmException
     */
    @Override
    public <T> int update(T entity) throws MyOrmException {

        //类对象
        Class<?> clazz = entity.getClass();

        //主键
        String pkValue = null;
        String pkKey = null;

        //组装sql
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");

        //参数列表
        List<Object> params = null;

        //表名
        String tableName = clazz.getSimpleName();

        Table tableAnnitationName = clazz.getDeclaredAnnotation(Table.class);

        if (StringUtils.isNotBlank(tableAnnitationName.value())) {
            tableName = tableAnnitationName.value();
        }

        sb.append(tableName).append(" ");
        sb.append("SET ");
        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtils.isNotEmpty(fields)) {

            params = new ArrayList<>();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                String fieldName = field.getName();
                if (!SqlConstant.SERIAL_VERSION_UID.getStringValue().equals(fieldName)) {
                    try {
                        Object o = field.get(entity);

                        String strValue = String.valueOf(o);

                        if (StringUtils.isNotBlank(strValue)) {
                            Column column = field.getDeclaredAnnotation(Column.class);
                            PK pkAnnitation = field.getDeclaredAnnotation(PK.class);

                            if (null == pkAnnitation) {
                                //主键不加入sql拼接
                                if (StringUtils.isNotBlank(column.value())) {
                                    sb.append(column.value()).append("=?,");
                                } else {
                                    sb.append(fieldName).append("=?,");
                                }
                                params.add(o);
                            }else {
                                pkValue = strValue;
                                pkKey = pkAnnitation.value();
                            }


                        }

                    } catch (IllegalAccessException e) {
                        //e.printStackTrace();
                        throw new MyOrmException("获取对象字段值异常 : " + e.getMessage());
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" WHERE ").append(pkKey).append("=").append(pkValue);
        }


        LOGGER.info("PreparedStatement  => {}", sb.toString());
        LOGGER.info("paramters  => {}", ArrayUtils.toString(params.toArray()));
        int count = JdbcUtils.executeUpdate(sb.toString(), params.toArray());
        return count;
    }


    /**
     * 返回实体对象列表
     * @param entity
     * @param <T>
     * @return
     * @throws MyOrmException
     */
    @Override
    public <T> List<T> queryForList(T entity) throws MyOrmException {

        StringBuilder sb = new StringBuilder();

        List<Object> params = null;

        Class<?> clazz = entity.getClass();

        String tableName = clazz.getSimpleName();

        Table tableAnnitation = clazz.getDeclaredAnnotation(Table.class);

        if (StringUtils.isNotBlank(tableAnnitation.value())){
            tableName = tableAnnitation.value();
        }

        sb.append("SELECT * FROM ").append(tableName);

        Field[] fields = clazz.getDeclaredFields();
        //取出数据库属性
        Field[] propKeys = new Field[fields.length-1];


        if (ArrayUtils.isNotEmpty(fields)){
            params = new ArrayList<>();
            sb.append(" WHERE ");
            for (int i = 0 ; i < fields.length ; i++){
                Field field = fields[i];
                field.setAccessible(true);
                try {
                    String fieldName = field.getName();

                    if(!SqlConstant.SERIAL_VERSION_UID.getStringValue().equals(fieldName)){


                        Object o = field.get(entity);
                        Column column = field.getDeclaredAnnotation(Column.class);
                        String strValue = String.valueOf(o);

                        if (ObjectUtils.allNotNull(o)){
                            if (StringUtils.isNotBlank(strValue)){
                                if (StringUtils.isNotBlank(column.value())){
                                    sb.append(column.value()).append(" =?").append(" AND ");
                                }else {
                                    sb.append(fieldName).append(" =?").append(" AND ");
                                }
                                params.add(o);
                            }
                        }
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (params.size() > 0){
                int start = sb.lastIndexOf("?") + 1;
                int end = sb.length();
                sb.delete(start, end);
            }else {
                int start = sb.lastIndexOf("W");
                int end = sb.length();
                sb.delete(start, end);
            }
        }
        LOGGER.info("PreparedStatement  => {}", sb.toString().trim());
        LOGGER.info("paramters  => {}", ArrayUtils.toString(params.toArray()));

        List<Map<String, Object>> maps = JdbcUtils.executeQuery(sb.toString().trim(), params.toArray());

        List<T> list = new ArrayList<>();


        for (Map<String, Object> map : maps) {

            T instance = null;
            try {
                instance = (T) clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            for (Field field : fields) {
                try {
                    if (!SqlConstant.SERIAL_VERSION_UID.getStringValue().equals(field.getName())){
                        String fieldMappingName = ReflectUtil.getFieldMappingName(field);
                        Object o = map.get(fieldMappingName);
                        field.set(instance, o);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.add(instance);
        }
        return list;
    }

    @Override
    public <T> List<T> queryAll(T entity) throws MyOrmException {
        StringBuilder sb = new StringBuilder();

        Class<?> clazz = entity.getClass();
        String tableName = clazz.getSimpleName();

        Table tableAnnitation = clazz.getDeclaredAnnotation(Table.class);

        if (StringUtils.isNotBlank(tableAnnitation.value())){
            tableName = tableAnnitation.value();
        }
        sb.append("SELECT * FROM ").append(tableName);

        LOGGER.info("PreparedStatement  => {}", sb.toString().trim());

        List<Map<String, Object>> maps = JdbcUtils.executeQuery(sb.toString());

        List<T> list = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        for (Map<String, Object> map : maps) {

            T instance = null;
            try {
                instance = (T) clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (!SqlConstant.SERIAL_VERSION_UID.getStringValue().equals(field.getName())){
                        String fieldMappingName = ReflectUtil.getFieldMappingName(field);
                        Object o = map.get(fieldMappingName);
                        field.set(instance, o);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.add(instance);
        }
        return list;
    }
}
