package com.yh.java8.sqlparser;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlParserUtil {

    /**
     * 从SQL语句中获取表名集合
     */
    public static List<String> findTablesFromSql(String sql) throws Exception {
        Statement statement = CCJSqlParserUtil.parse(sql);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);
        return tableList;
    }

    public static Map<String, String> findTable2AliasNameMap(String sql) throws Exception {
        Statement statement = CCJSqlParserUtil.parse(sql);
        if (!(statement instanceof Select)) {
            throw new RuntimeException("Not Select Sql");
        }
        Select select = (Select) statement;
        SelectBody selectBody = select.getSelectBody();
        if (!(selectBody instanceof PlainSelect)) {
            throw new RuntimeException("Not a select sql");
        }
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        return findTable2AliasNameMap(plainSelect);
    }

    public static Map<String, String> findTable2AliasNameMap(PlainSelect plainSelect) throws Exception {
        FromItem fromItem = plainSelect.getFromItem();
        Map<String, String> map = new HashMap<>();
        if (fromItem instanceof Table) {
            Table table = (Table) fromItem;
            Alias alias = table.getAlias();
            if (alias == null) {
                map.put(table.getName(), null);
            } else {
                map.put(table.getName(), alias.getName());
            }
        }
        List<Join> joins = plainSelect.getJoins();
        if (joins == null || joins.size() == 0) {
            return map;
        }
        for (Join join : joins) {
            FromItem rightItem = join.getRightItem();
            if (rightItem instanceof Table) {
                Table t1 = (Table) rightItem;
                map.put(t1.getName(), t1.getAlias().getName());
            }
        }
        return map;
    }

    /**
     * 获取查询的表和列的映射表
     */
    public static Map<String, Collection<String>> findSelectTable2ColumnsMap(String sql) throws Exception {
        Statement statement = CCJSqlParserUtil.parse(sql);
        if (!(statement instanceof Select)) {
            throw new RuntimeException("Not Select Sql");
        }
        Select select = (Select) statement;
        SelectBody selectBody = select.getSelectBody();
        if (!(selectBody instanceof PlainSelect)) {
            throw new RuntimeException("Not a select sql");
        }
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        return findSelectTable2ColumnsMap(plainSelect);
    }

    /**
     * 获取查询的表和列的映射表
     */
    public static Map<String, Collection<String>> findSelectTable2ColumnsMap(PlainSelect plainSelect) throws Exception {
        Map<String, String> table2AliasNameMap = findTable2AliasNameMap(plainSelect);
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        Multimap<String, String> multimap = HashMultimap.create();
        for (SelectItem selectItem : selectItems) {
            SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
            Column column = (Column) selectExpressionItem.getExpression();
            Table table = column.getTable();
            String name = table.getName();
            if (table2AliasNameMap.containsKey(name)) {
                multimap.put(name, column.getColumnName());
                continue;
            }
            if (table2AliasNameMap.containsValue(name)) {
                String tableName = getKeyByVlaue(table2AliasNameMap, name);
                multimap.put(tableName, column.getColumnName());
                continue;
            }
        }
        return multimap.asMap();
    }


    private static String getKeyByVlaue(Map<String, String> map, String value) {
        if (map == null || map.size() == 0) {
            return null;
        }
        if (StringUtils.isBlank(value)) {
            return null;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
