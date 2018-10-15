package by.bogdan.docs.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Bogdan Shishkin
 * project: restdocs-examples
 * date/time: 10/15/2018 / 11:45 PM
 * email: bahdan.shyshkin@itechart-group.com
 */
@Data
@Builder
public class User {
    private UUID userId;
    private String name;
    private String surname;
    private String description;
}
