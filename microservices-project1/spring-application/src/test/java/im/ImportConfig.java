package im;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-21
 */
@Configuration
@Import(Student.class)
public class ImportConfig {
}
