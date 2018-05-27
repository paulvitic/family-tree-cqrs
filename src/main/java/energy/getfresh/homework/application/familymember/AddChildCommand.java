package energy.getfresh.homework.application.familymember;

import lombok.Value;

@Value
public class AddChildCommand {
    final String mothersName;
    final String childsName;
    final String childsGender;
}
