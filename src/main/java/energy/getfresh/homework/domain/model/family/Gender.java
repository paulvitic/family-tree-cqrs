package energy.getfresh.homework.domain.model.family;

public enum Gender {

    MALE {boolean isMale(){return true;}},
    FEMALE {boolean isFemale(){return true;}};

    boolean isMale(){return false;}
    boolean isFemale(){return false;}
}
