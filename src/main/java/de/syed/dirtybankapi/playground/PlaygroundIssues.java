package de.syed.dirtybankapi.playground;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class PlaygroundIssues {

    // 3. Maintainability: Long parameter list
    public void longParamList(int a, int b, int c, int d, int e, int f, int g, int h) {
        // ❌ Maintainability: Overly nested ifs
        if (a > 0) {
            if (b > 0) {
                if (c > 0) {
                    if (d > 0) {
                        System.out.println("Deep nesting detected!");
                    }
                }
            }
        }
    }

    // 4. Reliability: Possible NPE with Optional.get()
    public String riskyOptional(Optional<String> value) {
        return value.get(); // No check!
    }

    // 5. Reliability: Possible divide by zero
    public int divide(int x, int y) {
        return x / y;
    }

    // 6. Security Vulnerability: Hardcoded password
    private static final String PASSWORD = "superSecret123";

    // 7. Security Vulnerability: SQL Injection risk
    public void badSql(String userInput) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM users WHERE name = '" + userInput + "'"; // injection risk
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("User: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 8. Security Hotspot: Logging sensitive data
    public void logSensitiveData(String username, String password) {
        System.out.println("User login attempt: " + username + " / " + password);
    }

    // 9. Security Hotspot: Runtime exec
    public void dangerousExec(String cmd) {
        try {
            Runtime.getRuntime().exec(cmd); // risky
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
