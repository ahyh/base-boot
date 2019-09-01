package com.yh.sql.parser;

import com.yh.java8.sqlparser.SqlParserUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlParserTest {

    private static final String TWO_TABLES_JOIN_SQL = "select u.user_id,u.dept,p.first_name,p.last_name from zm_user u , zm_userprofile p where u.user_id" +
            "=p.user_id and u.account_id = 'afeafe' and u.user_id in ('aaa','bbb','ccc') and u.enable_enable & 17 > 0;";


    private static final String THREE_TABLES_JOIN_SQL = "select u.user_id,u.dept,p.first_name,p.last_name,s.sns_type,s.email from zm_user u,zm_userprofile p,zm_sns s" +
            " where u.user_id = p.user_id and u.user_id = s.user_id and u.account_id = 'afeafe' and u.user_id in ('aaa','bbb','cccc') and u.enable_webinar & 17 > 0 order by" +
            " u.role desc,u.create_time desc limit 0,15";

    @Test
    public void testSqlParser() throws Exception {
        String sql = "select username,password from user";
        Statement statement = CCJSqlParserUtil.parse(sql);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);
        System.out.println(tableList);
    }

    @Test
    public void testSqlParser2() throws Exception {
        String sql = "select * from zm_user";
        Statement statement = CCJSqlParserUtil.parse(THREE_TABLES_JOIN_SQL);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);

        Select select = (Select) statement;
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        for (SelectItem item : selectItems) {
            if (item instanceof SelectExpressionItem) {
                SelectExpressionItem selectExpressionItem = (SelectExpressionItem) item;
                Alias alias = selectExpressionItem.getAlias();
            }
        }

        Map<String, String> table2ColumnMap = new HashMap<>();
        System.out.println(tableList);
    }


    @Test
    public void testGetTablesFromSql() throws Exception {
        Statement statement = CCJSqlParserUtil.parse(THREE_TABLES_JOIN_SQL);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);
        tableList.forEach(x -> System.out.println(x));
    }

    @Test
    public void testGetTable2AliasMap() throws Exception {
        Map<String, String> table2AliasNameMap = SqlParserUtil.findTable2AliasNameMap(THREE_TABLES_JOIN_SQL);
        System.out.println(table2AliasNameMap);
    }

    @Test
    public void testFindSelectTable2ColumnsMap() throws Exception {
        Map<String, Collection<String>> selectTable2ColumnsMap = SqlParserUtil.findSelectTable2ColumnsMap(THREE_TABLES_JOIN_SQL);

        System.out.println();
    }

}
