package fr.todooz.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagCloud {
    private List<String> tags = new ArrayList<>();

    public void add(String... tags) {
        if (tags == null) {
            return;
        }
        for (String tag : tags) {
            if (canAdd(tag)) {
                this.tags.add(tag);
            }
        }
    }

    public int size() {
        return tags.size();
    }

    public boolean contains(String tag) {
        return tags.contains(tag);
    }

    private boolean canAdd(String tag) {
        return tag != null && !"".equals(tag) && !contains(tag);
    }
}
