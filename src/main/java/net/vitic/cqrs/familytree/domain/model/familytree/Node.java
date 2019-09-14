package net.vitic.cqrs.familytree.domain.model.familytree;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Node {

    @JsonProperty("spouse")
    private Tree spouse;
    @JsonProperty("children")
    private List<Tree> children;

    private Node() {
        this.children = new ArrayList<>();
    }

    Node(Tree spouse) {
        this();
        this.spouse = spouse;
    }

    public void addChild(String name, NodeType type){
        if (children==null){
            this.children = new ArrayList<>();
        }
        children.add(new Tree(name, type));
    }

    Tree spouse(){
        return spouse;
    }

    public List<Tree> children() {
        return this.children;
    }
}
