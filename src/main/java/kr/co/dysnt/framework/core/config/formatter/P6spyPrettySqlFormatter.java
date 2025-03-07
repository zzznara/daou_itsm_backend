package kr.co.dysnt.framework.core.config.formatter;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P6spyPrettySqlFormatter implements MessageFormattingStrategy {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String TAB = "    ";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        if (!StringUtils.hasText(sql)) {
            return "";
        }

        StringBuilder sb = new StringBuilder()
            .append(DATE_FORMAT.format(new Date()))
            .append(" | ")
            .append(elapsed).append("ms")
            .append(" | ")
            .append(category)
            .append(" | ")
            .append(NEW_LINE);

        String formatSql = sql;
        
        // 파라미터 바인딩
        if (Category.STATEMENT.getName().equals(category)) {
            if (prepared != null && !prepared.equals(sql)) {
                List<String> parameters = extractParameters(prepared);
                if (!parameters.isEmpty()) {
                    formatSql = bindParameters(sql, parameters);
                }
            }
        }

        // SQL 키워드 대문자로 변환 및 줄바꿈 추가
        formatSql = formatSql
            // 기본 키워드
            .replaceAll("(?i)\\bselect\\b", NEW_LINE + "SELECT")
            .replaceAll("(?i)\\bfrom\\b", NEW_LINE + "FROM")
            .replaceAll("(?i)\\bwhere\\b", NEW_LINE + "WHERE")
            .replaceAll("(?i)\\band\\b", NEW_LINE + TAB + "AND")
            .replaceAll("(?i)\\bor\\b", NEW_LINE + TAB + "OR")
            .replaceAll("(?i)\\border\\s+by\\b", NEW_LINE + "ORDER BY")
            .replaceAll("(?i)\\bgroup\\s+by\\b", NEW_LINE + "GROUP BY")
            .replaceAll("(?i)\\bhaving\\b", NEW_LINE + "HAVING")
            // JOIN 관련
            .replaceAll("(?i)\\binner\\s+join\\b", NEW_LINE + "INNER JOIN")
            .replaceAll("(?i)\\bleft\\s+join\\b", NEW_LINE + "LEFT JOIN")
            .replaceAll("(?i)\\bright\\s+join\\b", NEW_LINE + "RIGHT JOIN")
            .replaceAll("(?i)\\bouter\\s+join\\b", NEW_LINE + "OUTER JOIN")
            .replaceAll("(?i)\\bjoin\\b", NEW_LINE + "JOIN")
            .replaceAll("(?i)\\bon\\b", NEW_LINE + TAB + "ON")
            // 서브쿼리 관련
            .replaceAll("(?i)\\bin\\s*\\(", NEW_LINE + TAB + "IN (")
            .replaceAll("(?i)\\bexists\\s*\\(", NEW_LINE + TAB + "EXISTS (")
            .replaceAll("(?i)\\bunion\\b", NEW_LINE + "UNION" + NEW_LINE)
            .replaceAll("(?i)\\bunion\\s+all\\b", NEW_LINE + "UNION ALL" + NEW_LINE)
            // 기타
            .replaceAll("(?i)\\blimit\\b", NEW_LINE + "LIMIT")
            .replaceAll("(?i)\\boffset\\b", NEW_LINE + "OFFSET")
            .replaceAll("(?i)\\bvalues\\b", NEW_LINE + "VALUES")
            .replaceAll("(?i)\\bover\\b", NEW_LINE + TAB + "OVER")
            .replaceAll("(?i)\\bpartition\\s+by\\b", NEW_LINE + TAB + "PARTITION BY");

        // 서브쿼리 들여쓰기 처리
        formatSql = formatSubqueries(formatSql);

        // 컬럼 들여쓰기
        formatSql = indentColumns(formatSql);

        sb.append(formatSql);
        return sb.toString().trim();
    }

    private String indentColumns(String sql) {
        StringBuilder result = new StringBuilder();
        String[] lines = sql.split(NEW_LINE);
        boolean inSelect = false;
        boolean firstColumn = true;

        for (String line : lines) {
            String trimmedLine = line.trim();
            if (trimmedLine.startsWith("SELECT")) {
                inSelect = true;
                firstColumn = true;
                result.append(line).append(NEW_LINE);
            } else if (inSelect && !trimmedLine.startsWith("FROM") && !trimmedLine.isEmpty()) {
                if (firstColumn) {
                    result.append(TAB).append(trimmedLine);
                    firstColumn = false;
                } else {
                    result.append(",").append(NEW_LINE).append(TAB).append(trimmedLine);
                }
            } else {
                if (trimmedLine.startsWith("FROM")) {
                    inSelect = false;
                }
                if (!trimmedLine.isEmpty()) {
                    result.append(NEW_LINE).append(trimmedLine);
                }
            }
        }
        return result.toString();
    }

    private String formatSubqueries(String sql) {
        StringBuilder result = new StringBuilder();
        int depth = 0;
        boolean inString = false;
        char[] chars = sql.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            
            // 문자열 내부 처리
            if (c == '\'') {
                inString = !inString;
                result.append(c);
                continue;
            }
            
            if (!inString) {
                if (c == '(') {
                    depth++;
                    result.append(c).append(NEW_LINE);
                    // 다음 라인 들여쓰기
                    for (int j = 0; j < depth; j++) {
                        result.append(TAB);
                    }
                } else if (c == ')') {
                    depth--;
                    result.append(NEW_LINE);
                    // 현재 라인 들여쓰기
                    for (int j = 0; j < depth; j++) {
                        result.append(TAB);
                    }
                    result.append(c);
                } else {
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private List<String> extractParameters(String prepared) {
        List<String> parameters = new ArrayList<>();
        StringBuilder currentParam = new StringBuilder();
        boolean inQuote = false;
        
        for (char c : prepared.toCharArray()) {
            if (c == '\'') {
                inQuote = !inQuote;
                currentParam.append(c);
            } else if (!inQuote && (c == ' ' || c == ',')) {
                if (currentParam.length() > 0) {
                    addParameter(parameters, currentParam.toString().trim());
                    currentParam = new StringBuilder();
                }
            } else {
                currentParam.append(c);
            }
        }
        
        if (currentParam.length() > 0) {
            addParameter(parameters, currentParam.toString().trim());
        }
        
        return parameters;
    }

    private void addParameter(List<String> parameters, String param) {
        if (param.matches("'.*'")) {
            parameters.add(param);
        } else if (param.equalsIgnoreCase("null")) {
            parameters.add("NULL");
        } else if (param.matches("-?\\d+(\\.\\d+)?")) {
            parameters.add(param);
        } else if (param.equalsIgnoreCase("true") || param.equalsIgnoreCase("false")) {
            parameters.add(param.toUpperCase());
        }
    }

    private String bindParameters(String sql, List<String> parameters) {
        if (parameters.isEmpty()) {
            return sql;
        }

        int parameterIndex = 0;
        StringBuffer result = new StringBuffer();
        Pattern pattern = Pattern.compile("\\?");
        Matcher matcher = pattern.matcher(sql);
        
        while (matcher.find() && parameterIndex < parameters.size()) {
            matcher.appendReplacement(result, Matcher.quoteReplacement(parameters.get(parameterIndex++)));
        }
        matcher.appendTail(result);
        
        return result.toString();
    }
}
