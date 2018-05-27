package energy.getfresh.homework.domain.model.familytree;

import java.util.Optional;

public enum NodeType {

    man, woman;

    public static Optional<NodeType> get(String gender) {
        switch (gender.toUpperCase()) {
            case "MALE":
                return Optional.of(NodeType.man);
            case "FEMALE":
                return Optional.of(NodeType.woman);
            default:
                return Optional.empty();
        }
    }
}
