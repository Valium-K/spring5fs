package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberDao;

@Configuration
public class AppCtx {
    /*
        커넥션 풀은 커넥션을 생성하고 유지한다.
        커넥션을 요청(getConnection())하면 Active 상태가되고,
        커낵션을 반환하면(close) Idle 상태가 된다.
        여기서 Idle 상태는 커넥션이 끊긴 상태가 아님에 유의한다.
        Idle 상태의 커넥션은 유휴 상태 지속시간이 지나면 DBMS는 해당 커넥션의 연결을 끊는다.

    */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        dataSource.setUsername("spring5");
        dataSource.setPassword("spring5");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(10);
        return dataSource;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
}
