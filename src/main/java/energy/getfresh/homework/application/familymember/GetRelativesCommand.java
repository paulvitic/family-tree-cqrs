package energy.getfresh.homework.application.familymember;

import lombok.Value;

@Value
public class GetRelativesCommand {
    private final String name;
    private final String relationship;
}
