package net.vitic.cqrs.familytree.application.familymember;

import lombok.Value;

@Value
public class AddChildCommand {
    final String mothersName;
    final String childsName;
    final String childsGender;
}
