package org.reform;

import org.reform.dto.DataMapper;
import org.reform.model.Pet;

public class Driver {
    public static void main(String[] args) {

        Pet p = new Pet("asdf", "sad");
        DataMapper dm = new DataMapper();
        dm.setObject(p);
        dm.save();
        dm.finishSession();
//        p.setName("Bitch");
//        dm.update();
//        dm.delete(3);
//        Pet p2 = (Pet) dm.getById(5);
//        dm.setObject(p2);
//        dm.delete();
//        System.out.println(dm.getId());
//        System.out.println(p2.getName());
    }
}
