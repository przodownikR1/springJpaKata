package pl.java.scalatech.spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Lists;

import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Configuration
public class ProxyDsConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource actualDataSource() {
        return DataSourceBuilder
                .create(ProxyDsConfig.class.getClassLoader())
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:testdb")  // in-memory db
                .username("sa")
                .password("")
                .build();
    }

    @Bean
    public DataSource dataSource(DataSource actualDataSource) {
        SLF4JQueryLoggingListener slf4jQueryLoggingListener = new SLF4JQueryLoggingListener();
        slf4jQueryLoggingListener.setQueryLogEntryCreator(new QueryLogEntryCreator());
        return ProxyDataSourceBuilder
                .create(actualDataSource)
                .name("MyDS")
                .listener(slf4jQueryLoggingListener)
                .build();
    }
    class QueryLogEntryCreator extends DefaultQueryLogEntryCreator {
        private Formatter[] formatters = new Formatter[] { FormatStyle.DDL.getFormatter(), FormatStyle.BASIC.getFormatter() };
        private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        private String format = "'%s'";

        @Override
        protected void writeQueriesEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
          sb.append(System.getProperty("line.separator", "\n"));
          sb.append("Query:[");
          for (QueryInfo queryInfo : queryInfoList) {
            String query = queryInfo.getQuery();
            for (Formatter formatter : formatters) {
              // .replace("\uFEFF", "")
              query = formatter.format(query).replace("?", "%s");
            }

            List<String> params = new ArrayList<>();
            for (Map<String, Object> paramMap : queryInfo.getQueryArgsList()) {
              SortedMap<String, Object> sortedParamMap = new TreeMap<>(new StringAsIntegerComparator());
              sortedParamMap.putAll(paramMap);
              params.addAll(Lists.transform(new ArrayList<>(sortedParamMap.values()), from -> {
              if (from instanceof String) {
                String value = (String) from;
                return value.contains("NULL") ? null : String.format(format, value);
              }
              else if (from instanceof Date) {
                return String.format(format, dateFormat.format(from));
              }
              else if (from instanceof Boolean) {
                return (Boolean) from ? "1" : "0";
              }
              else {
                return String.valueOf(from);
              }
            }));
            }
            sb.append(String.format(query, params.toArray()));
            sb.append(",");
          }
          chompIfEndWith(sb, ',');
          sb.append(System.getProperty("line.separator", "\n"));
          sb.append("]");
        }

        @Override
        protected void writeParamsEntry(StringBuilder sb, ExecutionInfo execInfo, List<QueryInfo> queryInfoList) {
        }
      }
}
